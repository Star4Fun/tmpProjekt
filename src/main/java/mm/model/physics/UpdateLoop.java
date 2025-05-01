package mm.model.physics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mm.Main;
import mm.model.level.GameLevel;

public abstract class UpdateLoop implements Runnable {

    public static final int ticksPerS = 20;

    protected volatile GameLevel theGameLevel;

    private boolean shouldRun = true;

    private boolean pause = false;

    public abstract void init();

    public abstract void update(float deltaTime);

    protected static final Logger log = LoggerFactory.getLogger(UpdateLoop.class);

    public boolean isRunning() {
        return this.shouldRun;
    }

    @Override
    public void run() {
        float targetDelay = 1000f/((float)ticksPerS);
        long lastUpdate = System.currentTimeMillis();
        while(!Main.renderEngineReady()) {
            try {
                Thread.sleep(250);
            } catch(InterruptedException e) {
                break;
            }
        }
        this.init();
        try {
            while(shouldRun) {
                while(pause || (this.theGameLevel == null)) {
                    Thread.sleep(100);
                }
                long now = System.currentTimeMillis();
                this.update(now - lastUpdate);
                lastUpdate = now;
                Thread.sleep(Math.round(targetDelay));
            }
        } catch(InterruptedException e) {
            log.error("Error in updateloop", e);
        }
    }

    public void setGameLevel(GameLevel gameLevel) {
        this.theGameLevel = gameLevel;
    }

}
