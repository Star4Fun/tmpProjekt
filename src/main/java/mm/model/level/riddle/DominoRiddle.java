import mm.model.level.riddle.IRiddle;
import mm.model.physics.*;
import mm.io.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class DominoRiddle implements IRiddle {

    private static final int DOMINO_COUNT = 5;
    private static final float DOMINO_WIDTH = 10f;   // Pixel
    private static final float DOMINO_HEIGHT = 50f;  // Pixel
    private static final float DOMINO_SPACING = 30f; // Pixel Abstand zwischen Dominos
    private static final float START_X = 100f;
    private static final float START_Y = 50f;

    private final Body[] dominos = new Body[DOMINO_COUNT];
    private GameWorld gameWorld;

    @Override
    public void initialize(GameWorld welt) {
        this.gameWorld = welt;

        PhysicInformation dominoPhys = new PhysicInformation(
                BodyType.DYNAMIC,
                0.4f,    // friction
                1.0f,    // density
                0.1f     // restitution
        );

        SpriteData dummySprite = new SpriteData();

        for (int i = 0; i < DOMINO_COUNT; i++) {
            float x = START_X + i * DOMINO_SPACING;
            float y = START_Y;

            welt.createRectangleGameObject(dominoPhys, dummySprite, x, y, DOMINO_WIDTH, DOMINO_HEIGHT);
            var body = welt.getEntityData().get(welt.getEntityData().size() - 1).getGameObject().getBody();
            dominos[i] = body;
        }
    }

    @Override
    public void update(float zeitDelta) {
        gameWorld.box2dWorld.step(zeitDelta, 6, 2);
    }

    @Override
    public boolean isSolved() {
        Body last = dominos[DOMINO_COUNT - 1];
        return Math.abs(last.getAngle()) > 0.3; // etwa 17° Neigung
    }

    @Override
    public void reset() {
        for (int i = 0; i < DOMINO_COUNT; i++) {
            float x = START_X + i * DOMINO_SPACING;
            float y = START_Y;

            Body b = dominos[i];
            b.setTransform(
                    new Vec2(
                            PhysicMathUtils.pixelToMeter(x + DOMINO_WIDTH / 2),
                            PhysicMathUtils.pixelToMeter(y + DOMINO_HEIGHT / 2)
                    ),
                    0f
            );
            b.setLinearVelocity(new Vec2(0f, 0f));
            b.setAngularVelocity(0f);
        }
    }
}
