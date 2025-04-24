package mm.render;

/**
 * Represents a simple RGBA color using floating-point values from 0.0 to 1.0.
 * This class is framework-agnostic and intended for use in rendering systems
 * that should remain independent of JavaFX, AWT or other GUI toolkits.
 */
public class SimpleColor {

    /** Red component (0.0 – 1.0) */
    public final float r;

    /** Green component (0.0 – 1.0) */
    public final float g;

    /** Blue component (0.0 – 1.0) */
    public final float b;

    /** Alpha (opacity) component (0.0 – 1.0) */
    public final float a;

    /**
     * Constructs a new color from red, green, blue and alpha components.
     *
     * @param r the red component (0.0 – 1.0)
     * @param g the green component (0.0 – 1.0)
     * @param b the blue component (0.0 – 1.0)
     * @param a the alpha component (0.0 – 1.0)
     */
    public SimpleColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Constructs a new opaque color (alpha = 1.0) from red, green and blue components.
     *
     * @param r the red component (0.0 – 1.0)
     * @param g the green component (0.0 – 1.0)
     * @param b the blue component (0.0 – 1.0)
     */
    public SimpleColor(float r, float g, float b) {
        this(r, g, b, 1f);
    }

    /**
     * Creates a color from standard 8-bit RGB values.
     *
     * @param r the red component (0–255)
     * @param g the green component (0–255)
     * @param b the blue component (0–255)
     * @return a new opaque SimpleColor
     */
    public static SimpleColor fromRGB(int r, int g, int b) {
        return new SimpleColor(r / 255f, g / 255f, b / 255f, 1f);
    }

    /**
     * Creates a color from standard 8-bit ARGB values.
     *
     * @param a the alpha component (0–255)
     * @param r the red component (0–255)
     * @param g the green component (0–255)
     * @param b the blue component (0–255)
     * @return a new SimpleColor with specified opacity
     */
    public static SimpleColor fromARGB(int a, int r, int g, int b) {
        return new SimpleColor(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    /**
     * Returns a human-readable representation of this color.
     *
     * @return the string "SimpleColor(r=..., g=..., b=..., a=...)"
     */
    @Override
    public String toString() {
        return String.format("SimpleColor(r=%.2f, g=%.2f, b=%.2f, a=%.2f)", r, g, b, a);
    }

    // Predefined standard colors (based on java.awt.Color equivalents)

    /** Fully transparent (black with alpha = 0) */
    public static final SimpleColor TRANSPARENT = new SimpleColor(0f, 0f, 0f, 0f);

    /** Opaque black */
    public static final SimpleColor BLACK = fromRGB(0, 0, 0);

    /** Opaque white */
    public static final SimpleColor WHITE = fromRGB(255, 255, 255);

    /** Opaque red */
    public static final SimpleColor RED = fromRGB(255, 0, 0);

    /** Opaque green */
    public static final SimpleColor GREEN = fromRGB(0, 255, 0);

    /** Opaque blue */
    public static final SimpleColor BLUE = fromRGB(0, 0, 255);

    /** Opaque yellow */
    public static final SimpleColor YELLOW = fromRGB(255, 255, 0);

    /** Opaque cyan */
    public static final SimpleColor CYAN = fromRGB(0, 255, 255);

    /** Opaque magenta */
    public static final SimpleColor MAGENTA = fromRGB(255, 0, 255);

    /** Opaque orange */
    public static final SimpleColor ORANGE = fromRGB(255, 200, 0);

    /** Opaque pink */
    public static final SimpleColor PINK = fromRGB(255, 175, 175);

    /** Light gray */
    public static final SimpleColor LIGHT_GRAY = fromRGB(192, 192, 192);

    /** Medium gray */
    public static final SimpleColor GRAY = fromRGB(128, 128, 128);

    /** Dark gray */
    public static final SimpleColor DARK_GRAY = fromRGB(64, 64, 64);
}
