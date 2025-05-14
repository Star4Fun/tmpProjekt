package mm.model.physics;

import org.jbox2d.dynamics.BodyType;

public class PhysicProfile {
    public static final PhysicInformation STATIC_DEFAULT = new PhysicInformation(
            BodyType.STATIC, 0.0f, 0.0f, 0.6f
    );

    public static final PhysicInformation DOMINO = new PhysicInformation(
            BodyType.DYNAMIC, 0.4f, 1.0f, 0.1f, 200f, 50f
    );

    public static final PhysicInformation BALL = new PhysicInformation(
            BodyType.DYNAMIC, 0.5f, 0.4f, 0.75f, (5*0.2f)*PhysicMathUtils.ratio
    );

    public static final PhysicInformation LIGHT_BALL = new PhysicInformation(
            BodyType.DYNAMIC, 0.4f, 0.2f, 0.8f, (5*0.067f)*PhysicMathUtils.ratio
    );

    public static final PhysicInformation HEAVY_BALL = new PhysicInformation(
            BodyType.DYNAMIC, 0.3f, 4.0f, 0.1f, (5*0.14f)*PhysicMathUtils.ratio
    );

    public static final PhysicInformation BOX = new PhysicInformation(
            BodyType.DYNAMIC, 0.0f, 0.2f, 0.01f, 256f, 256f
    );

    public static final PhysicInformation PLANK = new PhysicInformation(
            BodyType.STATIC, 0.0f, 0.0f, 0.1f, 200f, 50f
    );

    public static final PhysicInformation GEAR = new PhysicInformation(
            BodyType.STATIC, 0.0f, 0.0f, 0.1f, (5*0.1f)*PhysicMathUtils.ratio
    );

}
