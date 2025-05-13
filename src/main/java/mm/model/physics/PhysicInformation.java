package mm.model.physics;

import org.jbox2d.dynamics.BodyType;

public class PhysicInformation {

    public final float restitution;
    public final float density;
    public final float friction;
    public final BodyType type;

    // Optional dimensions
    public final float width;
    public final float height;
    public final float radius;


    // Constructor for rectangular shapes
    public PhysicInformation(BodyType type, float friction, float density, float restitution, float width, float height) {
        this.type = type;
        this.friction = friction;
        this.density = density;
        this.restitution = restitution;
        this.width = width;
        this.height = height;
        this.radius = 0f; // Default value for radius
    }

    // Constructor for circular shapes
    public PhysicInformation(BodyType type, float friction, float density, float restitution, float radius) {
        this.type = type;
        this.friction = friction;
        this.density = density;
        this.restitution = restitution;
        this.width = 0f; // Default value for width
        this.height = 0f; // Default value for height
        this.radius = radius;
    }

    // Constructor for default shapes
    public PhysicInformation(BodyType type, float friction, float density, float restitution) {
        this.type = type;
        this.friction = friction;
        this.density = density;
        this.restitution = restitution;
        this.width = 0f; // Default value for width
        this.height = 0f; // Default value for height
        this.radius = 0f; // Default value for radius
    }

    public static final PhysicInformation STATIC_DEFAULT =
    new PhysicInformation(BodyType.STATIC, 0.5f, 0f, 0.1f);

}
