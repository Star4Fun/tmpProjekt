package mm.render;

import mm.io.SpriteData;
import mm.io.SpriteLoader;

public enum Sprites {
    COUNTER("counter"),
    BALL("ball"),
    LIGHT_BALL("ball_light"),
    HEAVY_BALL("ball_heavy"),
    DOMINO("domino"),
    PLANK("plank"),
    BOX("box"),
    GEAR("gear"),
    INDUSTRIAL_BACKGROUND("industrial_background")
;

    String spriteName;

    boolean loaded = false;

    SpriteData spriteData = null;

    private Sprites(String spriteName) {
        this.spriteName = spriteName;
    }

    public SpriteData getSpriteData() {
        if(!this.loaded) {
            this.spriteData = SpriteLoader.loadSprite(spriteName);
            this.loaded = true;
        }
        return spriteData;
    }

    public Sprite getSprite() {
        if(!this.loaded) {
            this.spriteData = SpriteLoader.loadSprite(spriteName);
            this.loaded = true;
        }
        return new Sprite(spriteData);
    }

}
