all:
	#gcc -fPIC -rdynamic -g -c -Wall ioctl_preload.c
	#gcc -fPIC -shared -Wl,-soname,libmacspoof.so.1 -o libmacspoof.so.1.0.1 ioctl_preload.c -lc -ldl
	gcc -fPIC -shared -Wl,-soname,libmacspoof.so.1 -o libmacspoof.so.1.0.1 ioctl_preload.64b.c -lc -ldl

clean:
	rm -f ioctl_preload.o libmacspoof.so.1.0.1
