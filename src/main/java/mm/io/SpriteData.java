package mm.io;

public class SpriteData {

    public boolean staticSprite;
    public boolean loop;
    public Frame[] frames;

    public static class Frame {
        public int textureId;
        public float duration;
    }

}
