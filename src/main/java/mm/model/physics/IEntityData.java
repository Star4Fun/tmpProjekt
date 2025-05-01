package mm.model.physics;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IEntityData {

    static final Logger log = LoggerFactory.getLogger(IEntityData.class);

    /**
     * @return the angle of the entity in degree!
     */
    public float getAngle();

    /**
     * 
     * @return Top left corner of the entity
     */
    public Vec2 getScreenPosition(float screenWidth, float screenHeight);

    /**
     * 
     * @return The center position of the entity
     */
    public Vec2 getEntityCenter();
}
