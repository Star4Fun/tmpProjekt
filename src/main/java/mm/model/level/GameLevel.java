package mm.model.level;

import mm.model.level.riddle.IRiddle;
import mm.model.physics.GameWorld;

public class GameLevel {

    private GameWorld theWorld;

    private IRiddle theRiddle;

    public GameLevel() {
        theWorld = new GameWorld();
        //TODO: Add way to load riddle.
    }

    public GameWorld getGameWorld() {
        return this.theWorld;
    }

    public IRiddle getCurrentRiddle() {
        return this.theRiddle;
    }

}
