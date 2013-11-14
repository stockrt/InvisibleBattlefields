import br.pucrio.inf.lac.registry.RegistryCoreServer;
import br.pucrio.inf.lac.invisiblebattlefields.InvisibleBattlefieldsCoreServer;

public class CoreThreads {
	private static class StartRegistryCoreServer implements Runnable {
		public void run() {
			new RegistryCoreServer();
		}
	}

	private static class StartInvisibleBattlefieldsCoreServer implements
			Runnable {
		public void run() {
			new InvisibleBattlefieldsCoreServer();
		}
	}

	public static void initialize() {
		switch (System.getProperty("coreServer")) {
		case "registry":
			StartRegistryCoreServer registry_coreserver = new StartRegistryCoreServer();
			Thread registry_coreserver_thread = new Thread(registry_coreserver);
			System.out.print("Iniciando thread do RegistryCoreServer... ");
			registry_coreserver_thread.start();
			System.out.println("Pronto.");
			break;
		case "ibf":
			StartInvisibleBattlefieldsCoreServer ibf_coreserver = new StartInvisibleBattlefieldsCoreServer();
			Thread ibf_coreserver_thread = new Thread(ibf_coreserver);
			System.out
					.print("Iniciando thread do InvisibleBattlefieldsCoreServer... ");
			ibf_coreserver_thread.start();
			System.out.println("Pronto.");
			break;
		default:
			System.out
					.print("Nenhum CoreServer foi especificado, nenhuma thread adicional será instanciada... ");
			System.out.println("Pronto.");
			break;
		}
	}
}
