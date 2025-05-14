package mm.model.level.riddle;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import mm.io.SpriteData;
import mm.model.physics.*;
import mm.render.Sprite;
import mm.render.Sprites;

/*
1. Schwerkrafträtsel: ein bestimmtes Objekt muss fallen -------------------- GravityRiddle
        z.B. eine Kiste, welche von einer Planke gestoßen wird
        z.B. ein Ball der von einer Kante rollt
*/

//TODO: Rausfinden, resolve Issues: Method übergeben

public class GravityRiddle implements  IRiddle {
    private GameObjectCompound fallingBall;
    private GameWorld gameWorld;

    // Bildschirmkoordinaten in Pixeln
    private final float START_X = 200f;
    private final float START_Y = 500f;
    private final float RADIUS = 20f;

    // Y-Schwelle in Pixeln (wird später in Meter umgerechnet)
    private final float SOLVE_Y_PIXELS = 100f;

    private boolean isSolvedState = false;
    private float solvedTimer = 0f;
    private static final float RESET_DELAY = 3f; // Sekunden

    @Override
    public void initialize(GameWorld welt) {
        this.gameWorld = welt;

        PhysicInformation ballPhysik = new PhysicInformation(
            org.jbox2d.dynamics.BodyType.DYNAMIC,
            0.3f,
            1.0f,
            0.1f
        );

        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 0, 0, 1920, 50);
        this.fallingBall = welt.createCircleGameObject(PhysicsProfiles.BALL, Sprites.BALL.getSpriteData(), START_X, START_Y, (5*0.2f)*PhysicMathUtils.ratio);
        welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.PLANK.getSpriteData(), 100, 250, 200f, 50f, -45f);
        welt.createCircleGameObject(PhysicsProfiles.GEAR, Sprites.GEAR.getSpriteData(), 300, 200, (5*0.1f)*PhysicMathUtils.ratio);

    }

    @Override
    public void update(float zeitDelta) {
        // Solved-Status überprüfen
        if (!isSolvedState && isSolved()) {
            isSolvedState = true;
            solvedTimer = RESET_DELAY;
            System.out.println("Puzzle solved! Resetting in " + RESET_DELAY + " seconds...");
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
        float yMeter = fallingBall.object.getBody().getPosition().y;
        float solveY = PhysicMathUtils.pixelToMeter(SOLVE_Y_PIXELS);
        return yMeter < solveY;
    }

    @Override
    public void reset() {
        fallingBall.object.getBody().setTransform(new Vec2(
                PhysicMathUtils.pixelToMeter(START_X),
                PhysicMathUtils.pixelToMeter(START_Y)), 0f);
        fallingBall.object.getBody().setLinearVelocity(new Vec2(0f, 0f));
        fallingBall.object.getBody().setAngularVelocity(0f);
    }

    @Override
    public Sprite getBackground() {
        return Sprites.INDUSTRIAL_BACKGROUND.getSprite();
    }
}

