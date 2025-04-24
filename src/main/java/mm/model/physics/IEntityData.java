package mm.model.physics;

import org.jbox2d.common.Vec2;

public interface IEntityData {

    /**
     * @return the angle of the entity in degree!
     */
    public float getAngle();

    /**
     * 
     * @return Top left corner of the entity
     */
    public Vec2 getScreenPosition();

    /**
     * 
     * @return The center position of the entity
     */
    public Vec2 getEntityCenter();

}
