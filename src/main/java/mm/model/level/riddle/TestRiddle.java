package mm.model.level.riddle;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import mm.model.physics.GameObjectCompound;
import mm.model.physics.GameWorld;
import mm.model.physics.PhysicInformation;
import mm.model.physics.PhysicMathUtils;
import mm.render.Sprite;
import mm.render.Sprites;

public class TestRiddle implements IRiddle {

    GameObjectCompound box;

    @Override
    public void initialize(GameWorld welt) {
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 0, 0, 1920, 50);
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 0, 50, 50, 1080);
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 1870, 50, 50, 1080);
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 0, 1080, 1920, 50);
        
        box = welt.createRectangleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.03f, 0.5f, 0.3f), Sprites.BOX.getSpriteData(), 300, 1080-200, 256, 256);
        welt.createCircleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.3f, 4.0f,
             0.1f), Sprites.HEAVY_BALL.getSpriteData(), 300, 1080-200, (5*0.14f)*PhysicMathUtils.ratio);
        welt.createCircleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.5f, 0.4f,
            0.75f), Sprites.BALL.getSpriteData(), 300, 1080-200, (5*0.2f)*PhysicMathUtils.ratio);
        for(int i = 0; i < 400; i++) {
        welt.createCircleGameObject(
            new PhysicInformation(BodyType.DYNAMIC, 0.4f, 0.2f,
                0.8f), Sprites.LIGHT_BALL.getSpriteData(), 300, 1080-200, (5*0.067f)*PhysicMathUtils.ratio);
        }
    }

    @Override
    public void update(float zeitDelta) {
        this.box.object.getBody().applyForce(new Vec2(0, 50.0f), this.box.object.getBody().getLocalCenter());
        this.box.object.getBody().applyAngularImpulse(this.box.object.getBody().getAngularVelocity());
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public void reset() {
    }

    @Override
    public Sprite getBackground() {
        return Sprites.INDUSTRIAL_BACKGROUND.getSprite();
    }

}
