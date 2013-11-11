/*
 * Spoof a MAC address with LD_PRELOAD
 *
 * If environment variable $MAC_ADDRESS is set in the form "01:02:03:04:05:06"
 * then use that value, otherwise use the hardcoded 'mac_address' in this file.
 *
 * Bugs: This currently spoofs MAC addresses for *all* interfaces.
 * It would be better to watch calls to socket() for the interfaces
 * you want and then only spoof ioctl calls to that file descriptor.
 */
#include <dlfcn.h>
#include <stdlib.h>
#include <stdio.h>

#define SIOCGIFHWADDR 0x8927

static unsigned char mac_address[6] = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06};

int
ioctl(int d, int request, unsigned char *argp)
{
	static void *handle = NULL;
	static int (*orig_ioctl)(int, int, char*);
	int ret;
	char *p;

	// If this var isn't set, use the hardcoded address above
	p=getenv("MAC_ADDRESS");

	if (handle == NULL) {
		char *error;
		handle = dlopen("/lib/libc.so.6", RTLD_LAZY);
		if (!handle) {
			fputs(dlerror(), stderr);
			exit(EXIT_FAILURE);
		}
		orig_ioctl = dlsym(handle, "ioctl");
		if ((error = dlerror()) != NULL) {
			fprintf(stderr, "%s\n", error);
			exit(EXIT_FAILURE);
		}
	}

	ret = orig_ioctl(d, request, argp);

	if (request == SIOCGIFHWADDR) {
		unsigned char *ptr = argp + 18;
		int i;
		for (i=0; i < 6; i++) {
			if (!p) {
				ptr[i] = mac_address[i];
				continue;
			}
			int val = 0;
			if (p[0]>='0' && p[0]<='9') val |= (p[0]-'0')<<4;
			else if (p[0]>='a' && p[0]<='f') val |= (p[0]-'a'+10)<<4;
			else if (p[0]>='A' && p[0]<='F') val |= (p[0]-'A'+10)<<4;
			else break;
			if (p[1]>='0' && p[1]<='9') val |= p[1]-'0';
			else if (p[1]>='a' && p[1]<='f') val |= p[1]-'a'+10;
			else if (p[1]>='A' && p[1]<='F') val |= p[1]-'A'+10;
			else break;
			if (p[2]!=':' && p[2]!='\0') break;
			ptr[i] = val;
			p+=3;
		}
	}

	return ret;
}
