package mm.render;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;

import javafx.scene.canvas.Canvas;
import mm.model.level.GameLevel;
import mm.model.physics.CircleEntityData;
import mm.model.physics.GameObjectCompound;
import mm.model.physics.GameObjectType;
import mm.model.physics.RectangleEntityData;

public class CrazyMachines implements RenderLoop {

    float w = 128, h = w;
    float rw, rh;
    float angle = 0;
    Sprite counter;

    private GameLevel theGameLevel;

    @Override
    public void init(Canvas canvas, IRenderer renderer) {
        this.rw = (float)canvas.getWidth();
        this.rh = (float)canvas.getHeight();
        renderer.setStroke(SimpleColor.BLACK);
        counter = Sprites.COUNTER.getSprite();
    }

    @Override
    public void render(IRenderer renderer, float delta) {
        // GameWorld.world.box2dWorld.step(1/60f, 10, 10);
        // float centerX = (float)(b.getPosition().x * PhysicMathUtils.ratio), x = (float)(centerX - w / 2);
        // float centerY = (float)(rh - (b.getPosition().y * PhysicMathUtils.ratio)), y = (float)(centerY - h / 2);

        // renderer.pushState();
        // renderer.setFill(SimpleColor.BLACK);
        // // renderer.drawRectangle(x, y, w, h, (float)Math.toDegrees(-b.getAngle()));
        // // renderer.drawCircle(centerX, centerY, w);
        // renderer.popState();

        // renderer.drawTexture(Textures.BALL.id(), 10, 10, 320, 320, angle%360);
        // renderer.drawTexture(Textures.BALL.id(), 10, 10, 320, 320, angle%360);
        // renderer.drawTexture(counter.getCurrentTextureId(delta), 500f, 500f, 320f, 320f, 0f);
        angle+=10;
        List<GameObjectCompound> dataSnapshot = new ArrayList<>(theGameLevel.getGameWorld().getEntityData());
        for(GameObjectCompound ggc: dataSnapshot) {
            if(ggc.object.getShapeType() == GameObjectType.RECTANGLE) {
                RectangleEntityData red = (RectangleEntityData) ggc.buffer.getVisible();
                Vec2 pos = red.getScreenPosition(rw, rh);
                renderer.drawTexture(ggc.object.getSprite().getCurrentTextureId(delta), pos.x, pos.y, red.getWidth(), red.getHeight(), red.getAngle());
                renderer.drawRectangle(pos.x, pos.y, red.getWidth(), red.getHeight(), red.getAngle(), false, true);
            }
            else if(ggc.object.getShapeType() == GameObjectType.CIRCLE) {
                CircleEntityData ced = (CircleEntityData) ggc.buffer.getVisible();
                Vec2 pos = ced.getScreenPosition(rw, rh);
                renderer.drawTexture(ggc.object.getSprite().getCurrentTextureId(delta), pos.x-ced.getRadius(), pos.y-ced.getRadius(), ced.getRadius()*2, ced.getRadius()*2, ced.getAngle());
                renderer.drawCircle(pos, ced.getRadius(), false, true);
            }
        }
    }

    @Override
    public void resize(int oldWidth, int oldHeight, int newWidth, int newHeight) {
        this.rh = newHeight;
        this.rw = newWidth;
    }

    @Override
    public void setGameLevel(GameLevel gameLevel) {
        this.theGameLevel = gameLevel;
    }

}
