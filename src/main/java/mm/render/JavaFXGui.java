package mm.render;

import org.jbox2d.dynamics.Body;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mm.model.physics.GameWorld;
import mm.model.physics.PhysicMathUtils;

public class JavaFXGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Crazy Machines");
        primaryStage.setResizable(false);
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
        Canvas canvas = new Canvas(root.getWidth(), root.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Body b = GameWorld.world.getBox();
        GameWorld.world.addGround((float)primaryStage.getWidth(), (float)primaryStage.getHeight());
        root.getChildren().add(canvas);
        float w = 128, h = w;

        IRenderer renderer = new JavaFXRenderer(gc);
        renderer.setFont(null, 34);

        AnimationTimer scrollingTextTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            // handle is abstract, and has to be implemented in order for an AnimationTimer to be instantiated! (You could create a new class that inherits to keep it clean).
            @Override
            public void handle(long now) {
                // a so called "spin-wait", that checks if 15ms have passed.
                if (now - lastUpdate >= 35_000_000) {
                    gc.clearRect(0, 0, root.getWidth(), root.getHeight());
                    GameWorld.world.box2dWorld.step(1/30f, 10, 10);
                    float centerX = (float)(b.getPosition().x * PhysicMathUtils.ratio), x = (float)(centerX - w / 2);
                    float centerY = (float)(root.getHeight() - (b.getPosition().y * PhysicMathUtils.ratio)), y = (float)(centerY - h / 2);

                    renderer.pushState();
                    renderer.setFill(SimpleColor.BLACK);
                    renderer.drawRectangle(x, y, w, h, (float)Math.toDegrees(-b.getAngle()));
                    renderer.drawCircle(centerX, centerY, w);
                    renderer.popState();

                    // renderer.drawRectangle(5, 5, 100, 100);

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
