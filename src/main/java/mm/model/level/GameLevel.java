package mm.model.level;

import mm.model.level.riddle.IRiddle;
import mm.model.physics.GameWorld;

public class GameLevel {

    private GameWorld theWorld;

    private IRiddle theRiddle;

    public GameLevel(IRiddle riddle) {
        this.theRiddle = riddle;
        theWorld = new GameWorld();
        if(this.theRiddle != null) {
            this.theRiddle.initialize(theWorld);
        }
        //TODO: Add way to load riddle.
    }

    public GameWorld getGameWorld() {
        return this.theWorld;
    }

    public IRiddle getCurrentRiddle() {
        return this.theRiddle;
    }

}
