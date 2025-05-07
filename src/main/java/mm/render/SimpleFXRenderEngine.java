package mm.render;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mm.io.JavaFXTextureLoader;

public abstract class SimpleFXRenderEngine extends Application {

    public static final int TARGET_FPS = 65;
    public static final double NS_TO_S = 1_000_000_000d;
    public static final double NS_TO_MS = 1_000_000d;

    private static SimpleFXRenderEngine instance;

    private RenderLoop renderLoop;
    private IRenderer renderer;

    private boolean showFPS = true;

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        renderLoop = this.createRenderLoop();
        primaryStage.setTitle(this.getProgramName());
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, getInitialWidth(), getInitialHeight()));
        primaryStage.show();
        Canvas canvas = new Canvas(getInitialWidth(), getInitialHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        renderer = new JavaFXRenderer(gc);
        renderer.setFont(null, 34);

        JavaFXTextureLoader textureLoader = new JavaFXTextureLoader();
        Textures.setTextureLoader(textureLoader);

        /**
         * Add a listener to keep track of resolution
         */
        ChangeListener<Number> cl = new ChangeListener<Number>() {

            private SimpleFXRenderEngine app;

            public ChangeListener<Number> setApplicationInstance(SimpleFXRenderEngine app) {
                this.app = app;
                return this;
            }

            @Override 
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                if(observableValue.equals(primaryStage.widthProperty())) {
                    canvas.setWidth(newVal.doubleValue());
                    this.app.resize(oldVal.intValue(), (int)primaryStage.getHeight(), newVal.intValue(), (int)primaryStage.getHeight());
                }
                else if(observableValue.equals(primaryStage.heightProperty())) {
                    canvas.setHeight(newVal.doubleValue());
                    this.app.resize((int)primaryStage.getWidth(), oldVal.intValue(), (int)primaryStage.getWidth(), newVal.intValue());
                }
            }
        }.setApplicationInstance(this);
        primaryStage.widthProperty().addListener(cl);
        primaryStage.heightProperty().addListener(cl);
        /**
         * 
         */

        renderLoop.init(canvas, renderer);

        AnimationTimer scrollingTextTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            private final long frameDuration = (long)(NS_TO_S / TARGET_FPS);

            private SimpleFXRenderEngine app;

            public AnimationTimer setApplicationInstance(SimpleFXRenderEngine app) {
                this.app = app;
                return this;
            }

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                long elapsed = now - lastUpdate;
                float animationUpdateTime = elapsed/(float)NS_TO_S;

                if (elapsed >= frameDuration) {
                    gc.clearRect(0, 0, root.getWidth(), root.getHeight());
                    
                    if(renderLoop != null) {
                        renderLoop.render(renderer, animationUpdateTime);
                    }
                    
                    if(app.showFPS()) {
                        renderer.drawText("Fps: " + Math.round(NS_TO_S/((double)(now-lastUpdate))), 0, 0);
                    }

                    lastUpdate = now;
                }
            }
        }.setApplicationInstance(this);
        scrollingTextTimer.start();
    }

    public void resize(int oldWidth, int oldHeight, int newWidth, int newHeight) {
        renderLoop.resize(oldWidth, oldHeight, newWidth, newHeight);
    }

    public void toggleDisplayFPS() {
        this.showFPS = !this.showFPS;
    }

    public boolean showFPS() {
        return showFPS;
    }

    public static boolean isReady() {
        if(instance == null) {
            return false;
        }
        return true;
    }

    public static RenderLoop getRenderLoop() {
        if(instance == null) {
            throw new IllegalStateException("JavaFx has not been started yet.");
        }
        return instance.renderLoop;
    }

    protected abstract RenderLoop createRenderLoop();

    protected abstract String getProgramName();

    protected abstract int getInitialWidth();

    protected abstract int getInitialHeight();

}
