package mm.render;

import org.jbox2d.common.Vec2;

import mm.model.physics.IEntityData;

public interface IRenderer {

    /**
     * Draws a filled rectangle at the specified position with the given width, height, and rotation.
     *
     * @param x      the x-coordinate of the top-left corner of the rectangle
     * @param y      the y-coordinate of the top-left corner of the rectangle
     * @param w      the width of the rectangle
     * @param h      the height of the rectangle
     * @param angle  the rotation angle in degrees (clockwise)
     */
    public void drawRectangle(float x, float y, float w, float h, float angle);

    /**
     * Draws a filled rectangle at the specified position with the given width, height, and rotation.
     *
     * @param position  the top-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param angle     the rotation angle in degrees (clockwise)
     */
    public void drawRectangle(Vec2 position, float width, float height, float angle);

    /**
     * Draws a filled, non-rotated rectangle at the specified position.
     *
     * @param x  the x-coordinate of the top-left corner of the rectangle
     * @param y  the y-coordinate of the top-left corner of the rectangle
     * @param w  the width of the rectangle
     * @param h  the height of the rectangle
     */
    public void drawRectangle(float x, float y, float w, float h);


    /**
     * Draws a filled, non-rotated rectangle at the specified position.
     *
     * @param position  the top-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public void drawRectangle(Vec2 position, float width, float height);

    /**
     * Draws the hitbox (bounding box) of the given entity as a visual debug aid.
     * The hitbox is aligned to the top-left corner of the entity's screen position.
     *
     * @param data  the entity data from which the screen position and size are derived
     */
    public void drawEntityHitbox(IEntityData data);

    /**
     * Draws a filled circle centered at the given coordinates.
     *
     * @param x       the x-coordinate of the circle center
     * @param y       the y-coordinate of the circle center
     * @param radius  the radius of the circle
     */
    public void drawCircle(float x, float y, float radius);

    /**
     * Draws a filled circle centered at the given position.
     *
     * @param center  the center position of the circle
     * @param radius  the radius of the circle
     */
    public void drawCircle(Vec2 center, float radius);

    /**
     * Draws a rectangle at the specified position with the given width, height, and rotation.
     * 
     * @param x             the x-coordinate of the top-left corner
     * @param y             the y-coordinate of the top-left corner
     * @param w             the width of the rectangle
     * @param h             the height of the rectangle
     * @param angle         the rotation angle in degrees (clockwise)
     * @param shouldFill    whether to apply the fill color
     * @param shouldStroke  whether to apply the stroke color
     */
    void drawRectangle(float x, float y, float w, float h, float angle, boolean shouldFill, boolean shouldStroke);

    /**
     * Draws a rectangle at the specified position with the given width, height, and rotation.
     *
     * @param position      the top-left corner of the rectangle
     * @param width         the width of the rectangle
     * @param height        the height of the rectangle
     * @param angle         the rotation angle in degrees (clockwise)
     * @param shouldFill    whether to apply the fill color
     * @param shouldStroke  whether to apply the stroke color
     */
    void drawRectangle(Vec2 position, float width, float height, float angle, boolean shouldFill, boolean shouldStroke);

    /**
     * Draws a non-rotated rectangle at the specified position.
     *
     * @param x             the x-coordinate of the top-left corner
     * @param y             the y-coordinate of the top-left corner
     * @param w             the width of the rectangle
     * @param h             the height of the rectangle
     * @param shouldFill    whether to apply the fill color
     * @param shouldStroke  whether to apply the stroke color
     */
    void drawRectangle(float x, float y, float w, float h, boolean shouldFill, boolean shouldStroke);

    /**
     * Draws a non-rotated rectangle at the specified position.
     *
     * @param position      the top-left corner of the rectangle
     * @param width         the width of the rectangle
     * @param height        the height of the rectangle
     * @param shouldFill    whether to apply the fill color
     * @param shouldStroke  whether to apply the stroke color
     */
    void drawRectangle(Vec2 position, float width, float height, boolean shouldFill, boolean shouldStroke);

    /**
     * Draws a filled circle centered at the given coordinates.
     *
     * @param x             the x-coordinate of the circle center
     * @param y             the y-coordinate of the circle center
     * @param radius        the radius of the circle
     * @param shouldFill    whether to apply the fill color
     * @param shouldStroke  whether to apply the stroke color
     */
    void drawCircle(float x, float y, float radius, boolean shouldFill, boolean shouldStroke);

    /**
     * Draws a filled circle centered at the given position.
     *
     * @param center        the center position of the circle
     * @param radius        the radius of the circle
     * @param shouldFill    whether to apply the fill color
     * @param shouldStroke  whether to apply the stroke color
     */
    void drawCircle(Vec2 center, float radius, boolean shouldFill, boolean shouldStroke);

    /**
     * Sets the current font used for text rendering.
     *
     * If {@code fontFamily} is {@code null}, the system default font will be used.
     *
     * @param fontFamily the name of the font family to use, or {@code null} to use the system default font
     * @param size       the font size in points
     */
    void setFont(String fontFamily, int size);

    /**
     * Draws a text string at the specified position.
     *
     * The coordinates refer to the baseline starting point of the text.
     * The currently set font and fill color will be used.
     *
     * @param text the text to draw
     * @param x    the x-coordinate of the baseline start
     * @param y    the y-coordinate of the baseline
     */
    void drawText(String text, float x, float y);

    /**
     * Returns the height of the currently active font, in pixels.
     *
     * This value corresponds to the total height required to render a single line of text,
     * including ascenders and descenders.
     *
     * @return the height of the current font in pixels
     */
    float getFontHeight();

    void drawTexture(int textureId, float x, float y, float w, float h, float angle);

    /**
     * Saves the current rendering state (such as fill and stroke color).
     * 
     * This method allows you to temporarily change the rendering style
     * (e.g., fill color, stroke color) and later restore it using {@link #popState()}.
     * 
     * Multiple calls to {@code pushState()} can be nested and will be matched with subsequent {@code popState()} calls.
     */
    public void pushState();

    /**
     * Restores the most recently saved rendering state.
     *
     * This undoes the effect of the last {@link #pushState()} call and restores
     * the previous fill, stroke, and other renderer-specific settings.
     * 
     * If called without a matching {@code pushState()}, the behavior is implementation-defined
     * (typically a no-op or error depending on the implementation).
     */
    public void popState();

    /**
     * Sets the current fill color for subsequent draw operations.
     * 
     * If set to {@code null}, fill operations will be disabled until a new color is set.
     * 
     * @param fill the fill color to use (nullable)
     */
    void setFill(SimpleColor fill);

    /**
     * Sets the current stroke (outline) color for subsequent draw operations.
     * 
     * If set to {@code null}, stroke operations will be disabled until a new color is set.
     * 
     * @param stroke the stroke color to use (nullable)
     */
    void setStroke(SimpleColor stroke);

}
