package mm.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Gui extends Application {

    private String baseText = "   Say 'Hello World!'   "; // padded for smooth loop
    private int offset = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        // registered a lambda function, which is called when the button is clicked
        btn.setOnAction(event -> {System.out.println("Hello World!");});

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        // Animation loop: scroll text
        AnimationTimer scrollingTextTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            // handle is abstract, and has to be implemented in order for an AnimationTimer to be instantiated! (You could create a new class that inherits to keep it clean).
            @Override
            public void handle(long now) {
                // a so called "spin-wait", that checks if 150ms have passed.
                if (now - lastUpdate >= 150_000_000) {
                    String scrolled = baseText.substring(offset) + baseText.substring(0, offset);
                    btn.setText(scrolled);
                    offset = (offset + 1) % baseText.length();
                    lastUpdate = now;
                }
            }
        };
        scrollingTextTimer.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
