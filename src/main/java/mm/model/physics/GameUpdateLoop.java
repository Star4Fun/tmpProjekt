package mm.model.physics;

import mm.model.level.riddle.CollisionRiddle;
import mm.model.level.riddle.DominoRiddle;
import org.jbox2d.dynamics.BodyType;

import mm.Main;
import mm.model.level.GameLevel;
import mm.model.level.riddle.GravityRiddle;
import mm.model.level.riddle.TestRiddle;
import mm.render.Sprites;

public class GameUpdateLoop extends UpdateLoop {

    @Override
    public void init() {
        Main.setGameLevel(new GameLevel(new CollisionRiddle()));  //TestRiddle()));
    }

    @Override
    public void update(float deltaTime) {
        theGameLevel.getGameWorld().box2dWorld.step(1/200f, 10, 60);
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
