package mm.render;

import mm.io.SpriteData;

public class Sprite {

    final SpriteData spriteData;
    private float elapsedTime = 0f;
    private int currentFrameIndex = 0;
    private boolean stopped = false;

    public Sprite(SpriteData spriteData) {
        this.spriteData = spriteData;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void reset() {
        this.elapsedTime = 0f;
        this.currentFrameIndex = 0;
        this.stopped = false;
    }    

    public void updateAnimation(float deltaTime) {
        if(stopped) return;
        elapsedTime += deltaTime;
    
        while (elapsedTime > spriteData.frames[currentFrameIndex].duration) {
            elapsedTime -= spriteData.frames[currentFrameIndex].duration;
            currentFrameIndex++;
    
            if (currentFrameIndex >= spriteData.frames.length) {
                if(spriteData.loop) {
                    currentFrameIndex = 0;
                }
                else {
                    currentFrameIndex = (spriteData.frames.length - 1);
                    this.stopped = true;
                }
            }
        }
    }

    public int getCurrentTextureId(float deltaTime) {
        if(!spriteData.staticSprite) {
            this.updateAnimation(deltaTime);
        }
        return spriteData.frames[currentFrameIndex].textureId;
    }

}
