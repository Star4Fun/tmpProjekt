package mm.model.physics;

import org.jbox2d.dynamics.BodyType;

public class PhysicInformation {

    public final float restitution;
    public final float density;
    public final float friction;
    public final BodyType type;

    public PhysicInformation(BodyType type, float friction, float density, float restitution) {
        this.type = type;
        this.friction = friction;
        this.density = density;
        this.restitution = restitution;
    }

    public static final PhysicInformation STATIC_DEFAULT =
    new PhysicInformation(BodyType.STATIC, 0.5f, 0f, 0.1f);

}
