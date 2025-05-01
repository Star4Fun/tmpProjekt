package mm.render;

import mm.io.TextureLoader;

public enum Textures {
    BALL(0, "ball.png"),
    HEAVY_BALL(1, "heavy_ball.png"),
    LIGHT_BALL(2, "light_ball.png"),
    THREE(3, "three.gif")
    ;


    private final int id;
    private final String path;

    Object textureInstance;

    private static TextureLoader<?> textureLoader = null;

    Textures(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public <T> T getTexture(Class<T> expectedType) {
        if(textureInstance == null) {
            throw new IllegalStateException("Texture not loaded yet for: " + name());
        }
        if(expectedType.isInstance(textureInstance)) {
            return expectedType.cast(textureInstance);
        }
        else {
            throw new IllegalStateException("Type does not match texture type!");
        }
    }

    public static <T> void setTextureLoader(TextureLoader<T> parTextureLoader) {
        textureLoader = parTextureLoader;
    }

    public int id() {
        return this.id;
    }

    public <T> void loadTexture(TextureLoader<T> loader) {
        if(textureInstance == null) {
            this.textureInstance = loader.load("assets/textures/"+path);
        }
    }

    public void loadTexture() {
        if(textureLoader == null) {
            throw new IllegalStateException("You need to set a textureloader first!");
        }
        if(textureInstance == null) {
            this.textureInstance = textureLoader.load("assets/textures/"+path);
        }
    }

    public static Textures getForId(int textureId) {
        for(Textures t: Textures.values()) {
            if(t.id == textureId) {
                if(t.textureInstance == null) {
                    if(textureLoader != null) {
                        t.loadTexture();
                    }
                }
                return t;
            }
        }
        throw new IllegalArgumentException("Cannot find texture with id: " + textureId);
    }

}
