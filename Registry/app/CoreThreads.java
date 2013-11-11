import br.pucrio.inf.lac.registry.RegistryCoreServer;

public class CoreThreads {
	private static class StartRegistryCoreServer implements Runnable {
		public void run() {
			new RegistryCoreServer();
		}
	}

	public static void initialize() {
		StartRegistryCoreServer coreserver = new StartRegistryCoreServer();
		Thread coreserver_thread = new Thread(coreserver);
		System.out.print("Iniciando thread do RegistryCoreServer... ");
		coreserver_thread.start();
		System.out.println("Pronto.");
	}
}
