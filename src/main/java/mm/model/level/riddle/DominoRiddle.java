package mm.model.level.riddle;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import mm.io.SpriteData;
import mm.model.physics.*;
import mm.render.Sprite;
import mm.render.Sprites;


public class DominoRiddle implements IRiddle {
    private GameObjectCompound dominoCompound;
    private GameWorld gameWorld;

    // Bildschirmkoordinaten in Pixeln


    private static final int DOMINO_COUNT = 5;
    private static final float DOMINO_WIDTH = 50f;   // Pixel
    private static final float DOMINO_HEIGHT = 200f;  // Pixel
    private static final float DOMINO_SPACING = 50f; // Pixel Abstand zwischen Dominos
    private final float START_X = 1200f;
    private final float START_Y = 1100f;

     private final Body[] dominos = new Body[DOMINO_COUNT];
//     private GameWorld gameWorld;

     @Override
     public void initialize(GameWorld welt) {
         this.gameWorld = welt;

         welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 0, 0, 1920, 50);
         welt.createCircleGameObject(new PhysicInformation(BodyType.DYNAMIC, 0.5f, 0.4f,
                         0.75f), Sprites.BALL.getSpriteData(), 300, 1080-200, (5*0.2f)*PhysicMathUtils.ratio);
         welt.createRectangleGameObject(PhysicInformation.STATIC_DEFAULT, Sprites.DOMINO.getSpriteData(), 200, 150, DOMINO_WIDTH, DOMINO_HEIGHT, 45f);

         PhysicInformation dominoPhysik = new PhysicInformation(
                 org.jbox2d.dynamics.BodyType.DYNAMIC,
                 0.4f,
                 1.0f,
                 0.1f
         );

         SpriteData dominoSprite = Sprites.DOMINO.getSpriteData();  //new SpriteData();

         for (int i = 0; i < DOMINO_COUNT; i++) {
             float x = START_X + i * (DOMINO_SPACING + DOMINO_WIDTH);
             float y = START_Y;

//             welt.createRectangleGameObject(dominoPhysik, dominoSprite, x, y, DOMINO_WIDTH, DOMINO_HEIGHT);
//             var body = welt.getEntityData().get(welt.getEntityData().size() - 1).getGameObject().getBody();
//             dominos[i] = body;
             this.dominoCompound = welt.createRectangleGameObject(dominoPhysik, dominoSprite, x, y, DOMINO_WIDTH, DOMINO_HEIGHT);
         }
     }

     @Override
     public void update(float zeitDelta) {
         // gameWorld.box2dWorld.step(zeitDelta, 6, 2);
     }

     @Override
     public boolean isSolved() {
         Body last = dominos[DOMINO_COUNT - 1];
         return Math.abs(last.getAngle()) > 0.3; // etwa 17° Neigung
     }

     @Override
     public void reset() {
         for (int i = 0; i < DOMINO_COUNT; i++) {
             float x = START_X + i * (DOMINO_SPACING + DOMINO_WIDTH);
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

    @Override
    public Sprite getBackground() {return Sprites.INDUSTRIAL_BACKGROUND.getSprite();}
}
