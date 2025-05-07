package mm.model.level.riddle;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import mm.model.physics.GameWorld;
import mm.model.physics.*;

/*
1. Schwerkrafträtsel: ein bestimmtes Objekt muss fallen -------------------- GravityRiddle
        z.B. eine Kiste, welche von einer Planke gestoßen wird
        z.B. ein Ball der von einer Kante rollt
*/

//TODO: Rausfinden, resolve Issues: Method übergeben

public class GravityRiddle implements  IRiddle {
    private Body fallingBall;
    private GameWorld gameWorld;

    // Bildschirmkoordinaten in Pixeln
    private final float START_X = 200f;
    private final float START_Y = 100f;
    private final float RADIUS = 20f;

    // Y-Schwelle in Pixeln (wird später in Meter umgerechnet)
    private final float SOLVE_Y_PIXELS = 500f;

    @Override
    public void initialize(GameWorld welt) {
        this.gameWorld = welt;

        PhysicInformation ballPhysik = new PhysicInformation();
        ballPhysik.type = org.jbox2d.dynamics.BodyType.DYNAMIC;
        ballPhysik.density = 1.0f;
        ballPhysik.friction = 0.3f;
        ballPhysik.restitution = 0.1f;

        // Körper erzeugen und Sprite binden
        SpriteData dummySprite = new SpriteData(); // Du kannst das durch ein echtes Sprite ersetzen
        welt.createCircleGameObject(ballPhysik, dummySprite, START_X, START_Y, RADIUS);

        // Den zuletzt hinzugefügten Körper merken
        var last = welt.getEntityData().get(welt.getEntityData().size() - 1);
        this.fallingBall = last.getGameObject().getBody();
    }

    @Override
    public void update(float zeitDelta) {
        // gameWorld.box2dWorld.step(zeitDelta, 6, 2);
    }

    @Override
    public boolean isSolved() {
        float yMeter = fallingBall.getPosition().y;
        float solveY = PhysicMathUtils.pixelToMeter(SOLVE_Y_PIXELS);
        return yMeter < solveY;
    }

    @Override
    public void reset() {
        fallingBall.setTransform(new Vec2(
                PhysicMathUtils.pixelToMeter(START_X),
                PhysicMathUtils.pixelToMeter(START_Y)), 0f);
        fallingBall.setLinearVelocity(new Vec2(0f, 0f));
        fallingBall.setAngularVelocity(0f);
    }
}

