package mm.model.level.riddle;

import org.jbox2d.common.Vec2;

import mm.io.SpriteData;
import mm.model.physics.*;
import mm.render.Sprite;
import mm.render.Sprites;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class CollisionRiddle implements IRiddle {
    private GameObjectCompound collisionCompound;
    private Body ball;
    private Body target;
    private GameWorld gameWorld;

    private boolean solved = false;
    private boolean isSolvedState = false;
    private float solvedTimer = 0f;
    private static final float RESET_DELAY = 3000f; // Sekunden
    private float secondsCount = 0f;



    @Override
    public void initialize(GameWorld welt) {
        this.gameWorld = welt;
        // Weltboden
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 0, 0, 1920, 10);

        float startX = 100f;  // Abstand zur Wand
        float startY = 1200f;  // Höhe in Pixeln
        float radius = (5*0.2f)*PhysicMathUtils.ratio;

        // Ziel weiter rechts
        float targetX = 600f;
        float targetY = 100f;
        float targetSize = 100f;

        // Erstelle die Objekte
        GameObjectCompound BallCompound = welt.createCircleGameObject(PhysicProfile.BALL, Sprites.BALL.getSpriteData(), startX, startY-100, radius);
        Body ball = BallCompound.object.getBody();
        GameObjectCompound HeavyBallCompound = welt.createCircleGameObject(PhysicProfile.HEAVY_BALL, Sprites.HEAVY_BALL.getSpriteData(), startX, startY, radius);
        Body heavyBall = HeavyBallCompound.object.getBody();

        // Erstelle das Zielobjekt
        GameObjectCompound BoxCompund = welt.createRectangleGameObject(PhysicProfile.BOX, Sprites.BOX.getSpriteData(), targetX, targetY, targetSize, targetSize);
        Body target = BoxCompund.object.getBody();
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 50, 150, 50f, 200f, 45f);


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



//     var data = welt.getEntityData();
//     ball = data.get(data.size() - 2).getGameObject().getBody();
//     target = data.get(data.size() - 1).getGameObject().getBody();
    }

    @Override
    public void update(float zeitDelta) {
        // gameWorld.box2dWorld.step(zeitDelta, 6, 2);
        if (!isSolvedState && isSolved()) {
            isSolvedState = true;
            solvedTimer = RESET_DELAY;
            secondsCount = RESET_DELAY/1000f;
            System.out.println("Puzzle solved! Resetting in " + secondsCount + " seconds...");
        }

        if (isSolvedState) {
            solvedTimer -= zeitDelta;
            if (solvedTimer <= 0f) {
                reset();
                isSolvedState = false;
                System.out.println("Puzzle has been reset.");
            }
        }
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

    @Override
    public Sprite getBackground() {
        return Sprites.INDUSTRIAL_BACKGROUND.getSprite();}
}

