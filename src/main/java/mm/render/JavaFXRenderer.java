package mm.render;

import java.util.Stack;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mm.model.physics.IEntityData;

public class JavaFXRenderer implements IRenderer {

    private static final Logger log = LoggerFactory.getLogger(JavaFXRenderer.class);

    private final GraphicsContext ctx;

    private final Stack<RenderStyle> styleStack = new Stack<>();
    private final RenderStyle defaultStyle = new RenderStyle(Color.BLACK, Color.YELLOW);
    private RenderStyle currentStyle = defaultStyle; // default

    private float fontHeight = 1.0f;

    public JavaFXRenderer(GraphicsContext gc) {
        this.ctx = gc;
        this.ctx.setFill(currentStyle.fill);
        this.ctx.setStroke(currentStyle.stroke);
        this.setFont(null, 20);
    }

    @Override
    public void drawRectangle(float x, float y, float w, float h, float angle) {
        this.drawRectangle(x, y, w, h, angle, true, true);
    }

    @Override
    public void drawRectangle(float x, float y, float w, float h) {
        this.drawRectangle(x, y, w, h, true, true);
    }

    @Override
    public void drawEntityHitbox(IEntityData data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawEntityHitbox'");
    }
    @Override
    public void drawRectangle(Vec2 position, float width, float height, float angle) {
        drawRectangle(position.x, position.y, width, height, angle);
    }

    @Override
    public void drawRectangle(Vec2 position, float width, float height) {
        drawRectangle(position.x, position.y, width, height);
    }

    @Override
    public void drawRectangle(float x, float y, float w, float h, float angle, boolean shouldFill,
            boolean shouldStroke) {
        if(!shouldFill && !shouldStroke) {
            log.warn("We are not rendering anything: shouldStroke and shouldFill are false");
            return;
        }
        this.ctx.save();
        this.ctx.translate(x+w/2, y+h/2);
        this.ctx.rotate(angle);
        if(shouldFill) {
            this.ctx.fillRect(-w/2, -h/2, w, h);
        }
        if(shouldStroke) {
            this.ctx.strokeRect(-w/2, -h/2, w, h);
        }
            
        this.ctx.restore();
    }

    @Override
    public void drawRectangle(Vec2 position, float w, float h, float angle, boolean shouldFill,
            boolean shouldStroke) {
        this.drawRectangle(position.x, position.y, w, h, angle, shouldFill,
        shouldStroke);
    }

    @Override
    public void drawRectangle(float x, float y, float w, float h, boolean shouldFill, boolean shouldStroke) {
        if(!shouldFill && !shouldStroke) {
            log.warn("We are not rendering anything: shouldStroke and shouldFill are false");
            return;
        }
        if(shouldFill) {
            this.ctx.fillRect(x, y, w, h);
        }
        if(shouldStroke) {
            this.ctx.strokeRect(x, y, w, h);
        }
    }

    @Override
    public void drawRectangle(Vec2 position, float width, float height, boolean shouldFill, boolean shouldStroke) {
        this.drawRectangle(position.x, position.y, width, height, shouldFill, shouldStroke);
    }

    @Override
    public void drawCircle(float x, float y, float radius) {
        drawCircle(x, y, radius, true, true);
    }

    @Override
    public void drawCircle(Vec2 center, float radius) {
        drawCircle(center.x, center.y, radius);
    }

    @Override
    public void drawCircle(float x, float y, float radius, boolean shouldFill, boolean shouldStroke) {
        if(!shouldFill && !shouldStroke) {
            log.warn("We are not rendering anything: shouldStroke and shouldFill are false");
            return;
        }
        if(shouldFill) {
            this.ctx.fillOval(x-radius/2, y-radius/2, radius, radius);
        }
        if(shouldStroke) {
            this.ctx.strokeOval(x-radius/2, y-radius/2, radius, radius);
        }
    }

    @Override
    public void drawCircle(Vec2 center, float radius, boolean shouldFill, boolean shouldStroke) {
        drawCircle(center.x, center.y, radius, shouldFill, shouldStroke);
    }

    @Override
    public void setFill(SimpleColor fill) {
        if (fill == null) {
            log.debug("Setting fill to transparent!");
            fill = SimpleColor.TRANSPARENT;
        }
        currentStyle.fill = toFX(fill);
        this.ctx.setFill(currentStyle.fill);
    }

    @Override
    public void setStroke(SimpleColor stroke) {
        if (stroke == null) {
            log.debug("Setting stroke to transparent!");
            stroke = SimpleColor.TRANSPARENT;
        }
        currentStyle.stroke = toFX(stroke);
        this.ctx.setStroke(currentStyle.stroke);
    }

    @Override
    public void pushState() {
        styleStack.push(currentStyle.copy());
    }

    @Override
    public void popState() {
        if (!styleStack.isEmpty()) {
            currentStyle = styleStack.pop();
            this.ctx.setFill(currentStyle.fill);
            this.ctx.setStroke(currentStyle.stroke);        
        }
        else {
            log.warn("Stack is empty!");
        }
    }

    @Override
    public void drawText(String text, float x, float y) {
        
        this.ctx.fillText(text, x, y+fontHeight);
    }

    @Override
    public float getFontHeight() {
        return this.fontHeight;
    }

    public void setFont(String fontFamiliy, int size) {
        Font newFont;
        if(fontFamiliy == null) {
            newFont = Font.font(fontFamiliy, size);
        }
        else {
            newFont = Font.font(size);
        }
        Text text = new Text("Ay"); // typical height
        text.setFont(newFont);

        fontHeight = (float)text.getLayoutBounds().getHeight();
        ctx.setFont(newFont);
    }

    private Color toFX(SimpleColor c) {
        return new Color(c.r, c.g, c.b, c.a);
    }

    static class RenderStyle {
        Paint fill, stroke;

        public RenderStyle(Paint fill, Paint stroke) {
            this.fill = fill;
            this.stroke = stroke;
        }

        public RenderStyle copy() {
            return new RenderStyle(this.fill, this.stroke);
        }
    }

}
