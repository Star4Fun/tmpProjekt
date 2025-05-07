package mm;

import mm.model.level.GameLevel;
import mm.model.physics.GameUpdateLoop;
import mm.model.physics.UpdateLoop;
import mm.render.MadMachineLauncher;

/**
 * The common starting point of the GUI.
 */
public class Main {

    private static UpdateLoop theUpdateLoop;

    /**
     * The external entry point of the application.
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        System.out.println("Starting.");
        theUpdateLoop = new GameUpdateLoop();
        Thread t = new Thread(theUpdateLoop);
        t.setName("Update thread");
        t.setDaemon(true);
        t.start();
        MadMachineLauncher.main(args);
        System.out.println("Exiting...");
    }

    public static boolean firstUpdateDone() {
        return theUpdateLoop.firstRunDone();
    }

    public static boolean renderEngineReady() {
        return MadMachineLauncher.isReady();
    }

    public static void setGameLevel(GameLevel level) {
        theUpdateLoop.setGameLevel(level);
        MadMachineLauncher.getRenderLoop().setGameLevel(level);
    }

    
}
