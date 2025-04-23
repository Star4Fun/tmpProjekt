package mm.model.physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class GameWorld {
    public static GameWorld world = new GameWorld();

    public World box2dWorld;

    public GameWorld() {
        box2dWorld = new World(new Vec2(0, -9.807f));
        
    }

    public Body getBox() {
        float w = 32;
        float h = 32;
        PolygonShape s = new PolygonShape();
		s.setAsBox(PhysicMathUtils.pixelToMeter(w/2), PhysicMathUtils.pixelToMeter(h/2));
        BodyDef body = new BodyDef();
        body.type = BodyType.DYNAMIC;
        body.position = new Vec2(5, 5);
        body.active = true;

        FixtureDef fixture = new FixtureDef();
        fixture.shape = s;
        fixture.restitution = 0.3f;
	    fixture.density = 0.333f;
	    fixture.friction = 0.3f;
        Body boxBody = box2dWorld.createBody(body);
        boxBody.createFixture(fixture);
        System.out.println(boxBody);
        return boxBody;
    }


}
