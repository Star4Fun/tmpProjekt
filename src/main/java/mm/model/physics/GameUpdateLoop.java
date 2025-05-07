package mm.model.physics;

import org.jbox2d.dynamics.BodyType;

import mm.Main;
import mm.model.level.GameLevel;
import mm.render.Sprites;

public class GameUpdateLoop extends UpdateLoop {

    @Override
    public void init() {
        Main.setGameLevel(new GameLevel());
        theGameLevel.getGameWorld().createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.COUNTER.getSpriteData(), 0, 0, 1920, 50);
        theGameLevel.getGameWorld().createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.COUNTER.getSpriteData(), 0, 50, 50, 1080);
        theGameLevel.getGameWorld().createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.COUNTER.getSpriteData(), 1870, 50, 50, 1080);
        
        theGameLevel.getGameWorld().createRectangleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.03f, 0.5f, 0.3f), Sprites.COUNTER.getSpriteData(), 300, 1080-200, 64, 64);
        theGameLevel.getGameWorld().createCircleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.3f, 4.0f,
             0.1f), Sprites.HEAVY_BALL.getSpriteData(), 300, 1080-200, (5*0.24f)*PhysicMathUtils.ratio);
        theGameLevel.getGameWorld().createCircleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.5f, 0.4f,
            0.9f), Sprites.BALL.getSpriteData(), 300, 1080-200, (5*0.2f)*PhysicMathUtils.ratio);
        theGameLevel.getGameWorld().createCircleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.4f, 0.2f,
                0.8f), Sprites.LIGHT_BALL.getSpriteData(), 300, 1080-200, (5*0.067f)*PhysicMathUtils.ratio);
        
    }

    @Override
    public void update(float deltaTime) {
        theGameLevel.getGameWorld().box2dWorld.step(1/30f, 10, 60);
        for(GameObjectCompound ggc: theGameLevel.getGameWorld().entityData) {
            ggc.buffer.updateAndSwap(ed -> {
                if (ggc.object.getShapeType() == GameObjectType.RECTANGLE) {
                    RectangleEntityData red = (RectangleEntityData) ed;
                    red.setEntityBounds(
                        PhysicMathUtils.getPositionInPixel(ggc.object.getBody()),
                        ggc.object.getWidth(),
                        ggc.object.getHeight()
                    );
                    red.setAngle((float) Math.toDegrees(-ggc.object.getBody().getAngle()));
                }
                else if (ggc.object.getShapeType() == GameObjectType.CIRCLE) {
                    CircleEntityData red = (CircleEntityData) ed;
                    red.setEntityBounds(
                        PhysicMathUtils.getPositionInPixel(ggc.object.getBody()),
                        ggc.object.getRadius()
                    );
                    red.setAngle((float) Math.toDegrees(-ggc.object.getBody().getAngle()));
                }
            });
        }
        if(theGameLevel.getCurrentRiddle() != null) {
            theGameLevel.getCurrentRiddle().update(deltaTime);
        }
    }

}
