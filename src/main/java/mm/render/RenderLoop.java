package mm.render;

import javafx.scene.canvas.Canvas;
import mm.model.level.GameLevel;

public interface RenderLoop {

    public void setGameLevel(GameLevel gameLevel);

    public void init(Canvas canvas, IRenderer renderer);

    public void resize(int oldWidth, int oldHeight, int newWidth, int newHeight);

    /**
     * 
     * @param delta the delta time in seconds
     */
    public void render(IRenderer renderer, float delta);
}
