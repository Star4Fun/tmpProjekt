package mm.model.level.riddle;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.CircleShape;
import mm.model.physics.GameWorld;
import mm.model.physics.GameUpdateLoop;

/*
1. Schwerkrafträtsel: ein bestimmtes Objekt muss fallen -------------------- GravityRiddle
        z.B. eine Kiste, welche von einer Planke gestoßen wird
        z.B. ein Ball der von einer Kante rollt
*/

//TODO: Rausfinden, resolve Issues: Method übergeben

public class GravityRiddle implements  IRiddle {
    private Body ball;
    private GameWorld gameWorld;
    private final float START_Y = 10.0f;
    private final float SOLVE_Y = -5.0f;  // Wenn der Ball unter diese Höhe fällt → Rätsel gelöst

    @Override
//    TODO: Mit unseren Objekten implementieren.
    public void initialize(GameWorld welt) {
        this.gameWorld = welt;
        World world = welt.getBox2DWorld();

        // Ball erzeugen
        BodyDef ballDef = new BodyDef();
        ballDef.type = BodyType.DYNAMIC;
        ballDef.position.set(0f, START_Y);
        ball = world.createBody(ballDef);

        CircleShape shape = new CircleShape();
        shape.m_radius = 0.5f;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.1f; // etwas Bounciness, falls gewünscht

        ball.createFixture(fixtureDef);
    }

    @Override
//    TODO: Kontrollieren, ob GameUpdateLoop.update() funktioniert oder ob das falsch ist.
    public void update(float zeitDelta) {
        GameUpdateLoop gameUpdateLoop = new GameUpdateLoop();
        gameUpdateLoop.update(zeitDelta);
    }

    @Override
    public boolean isSolved() {
        return ball.getPosition().y < SOLVE_Y;
    }

    @Override
    public void reset() {
        ball.setTransform(new Vec2(0f, START_Y), 0f);
        ball.setLinearVelocity(new Vec2(0f, 0f));
        ball.setAngularVelocity(0f);
    }
}

