package mm;

import mm.render.JavaFXGui;

/**
 * The common starting point of the GUI.
 */
public class Main {

    /**
     * The external entry point of the application.
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        System.out.println("Starting.");
        JavaFXGui.main(args);
        System.out.println("Exiting...");
    }

    
}
