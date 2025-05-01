package mm.model.physics;

import org.jbox2d.common.Vec2;

public class CircleEntityData implements IEntityData {

    private float angle;
    private float radius;

    private Vec2 centerVec2;

    /**
     * Sets the screen-space position and radius of the entity.
     *
     * @param centerPos the center position in pixels
     * @param r         the radius in pixels
     */
    public void setEntityBounds(Vec2 centerPos, float r) {
        this.centerVec2 = centerPos;
        this.radius = r;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public float getAngle() {
        return this.angle;
    }

    @Override
    /**
     * @return returns the center of the circle.
     */
    public Vec2 getScreenPosition(float screenWidth, float screenHeight) {
        return new Vec2(this.centerVec2.x, screenHeight-this.centerVec2.y);
    }

    @Override
    public Vec2 getEntityCenter() {
        return this.centerVec2;
    }
    
}
