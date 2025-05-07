import mm.model.level.riddle.IRiddle;
import mm.model.physics.*;
import mm.model.physics.PhysicMathUtils;
import mm.io.*;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.common.Vec2;

public class CollisionRiddle implements IRiddle {

    private Body ball;
    private Body target;
    private GameWorld gameWorld;

    private boolean solved = false;

    @Override
    public void initialize(GameWorld welt) {
        this.gameWorld = welt;

        // Contact Listener hinzufügen
        welt.box2dWorld.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body a = contact.getFixtureA().getBody();
                Body b = contact.getFixtureB().getBody();

                if ((a == ball && b == target) || (a == target && b == ball)) {
                    solved = true;
                }
            }

            @Override public void endContact(Contact contact) {}
            @Override public void preSolve(Contact contact, Manifold oldManifold) {}
            @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
        });

        SpriteData dummy = new SpriteData();

        PhysicInformation ballInfo = new PhysicInformation(BodyType.DYNAMIC, 0.4f, 1.0f, 0.2f);
        PhysicInformation targetInfo = new PhysicInformation(BodyType.STATIC, 0.4f, 0f, 0.1f);

        float radius = 20f;
        float startX = 100f;
        float startY = 200f;

        // Ziel weiter rechts
        float targetX = 400f;
        float targetY = 100f;
        float targetSize = 40f;

        welt.createCircleGameObject(ballInfo, dummy, startX, startY, radius);
        welt.createRectangleGameObject(targetInfo, dummy, targetX, targetY, targetSize, targetSize);

        var data = welt.getEntityData();
        ball = data.get(data.size() - 2).getGameObject().getBody();
        target = data.get(data.size() - 1).getGameObject().getBody();
    }

    @Override
    public void update(float zeitDelta) {
        // gameWorld.box2dWorld.step(zeitDelta, 6, 2);
    }

    @Override
    public boolean isSolved() {
        return solved;
    }

    @Override
    public void reset() {
        solved = false;
        ball.setTransform(
                new Vec2(
                        PhysicMathUtils.pixelToMeter(100f),
                        PhysicMathUtils.pixelToMeter(200f)
                ),
                0f
        );
        ball.setLinearVelocity(new Vec2(0f, 0f));
        ball.setAngularVelocity(0f);
    }
}

