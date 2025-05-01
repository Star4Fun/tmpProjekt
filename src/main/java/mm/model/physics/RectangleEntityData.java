package mm.model.physics;

import org.jbox2d.common.Vec2;

public class RectangleEntityData implements IEntityData {

    Vec2 centerVec2;
    float angle;
    float w, h;

    /**
     * 
     * @param angle in degrees
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    /**
     * @return the angle in degree
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * 
     * @param centerPos in pixel!
     * @param w in pixel
     * @param h in pixel
     */
    public void setEntityBounds(Vec2 centerPos, float w, float h) {
        this.centerVec2 = centerPos;
        this.w = w;
        this.h = h;
    }

    @Override
    public Vec2 getScreenPosition(float screenWidth, float screenHeight) {
        if(this.centerVec2 != null) {
            return new Vec2(this.centerVec2.x-w/2, screenHeight-this.centerVec2.y-h/2);
        }
        log.warn("Entity buffer has no position data!");
        return new Vec2(0, 0);
    }

    @Override
    public Vec2 getEntityCenter() {
        return this.centerVec2;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

}
