import play.*;

public class Global extends GlobalSettings {
    public void onStart(Application app) {
        System.out.println("Inicializando threads...");
        CoreThreads.initialize();
    }
}
