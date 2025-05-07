package mm.model.physics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import mm.io.SpriteData;
import mm.render.Sprite;

public class GameWorld {

    public World box2dWorld;

    volatile ArrayList<GameObjectCompound> entityData = new ArrayList<GameObjectCompound>();

    private boolean isReady = false;

    // public 

    public GameWorld() {
        box2dWorld = new World(new Vec2(0, -9.807f));
        isReady = true;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public EntityDataBufferSafe createBoxBuffer() {
        return new EntityDataBufferSafe(new RectangleEntityData(), new RectangleEntityData());
    }

    public EntityDataBufferSafe createCircleBuffer() {
        return new EntityDataBufferSafe(new CircleEntityData(), new CircleEntityData());
    }

    public GameObjectCompound createRectangleGameObject(PhysicInformation pi, SpriteData s, float x, float y, float w, float h) {
        Body b = createBox(pi, x, y, w, h);
        EntityDataBufferSafe buffer = createBoxBuffer();
        GameObjectCompound ggc = new GameObjectCompound(new GameObject(new Sprite(s), b, w, h), 
        buffer);
        ggc.buffer.updateAndSwap(ed -> {
            //TODO put function central
            if (ggc.object.getShapeType() == GameObjectType.RECTANGLE) {
                RectangleEntityData red = (RectangleEntityData) ed;
                red.setEntityBounds(
                    PhysicMathUtils.getPositionInPixel(ggc.object.getBody()),
                    ggc.object.getWidth(),
                    ggc.object.getHeight()
                );
                red.setAngle((float) Math.toDegrees(-ggc.object.getBody().getAngle()));
            }
            else if (ggc.object.getShapeType() == GameObjectType.CIRCLE) {
                CircleEntityData red = (CircleEntityData) ed;
                red.setEntityBounds(
                    PhysicMathUtils.getPositionInPixel(ggc.object.getBody()),
                    ggc.object.getRadius()
                );
                red.setAngle((float) Math.toDegrees(-ggc.object.getBody().getAngle()));
            }
        });
        this.entityData.add(ggc);
        return ggc;
    }

    public GameObjectCompound createCircleGameObject(PhysicInformation pi, SpriteData s, float x, float y, float r) {
        Body b = createCircle(pi, x, y, r);
        EntityDataBufferSafe buffer =  createCircleBuffer();
        GameObjectCompound ggc = new GameObjectCompound(new GameObject(new Sprite(s), b, r), 
        buffer);
        buffer.updateAndSwap(ed -> {
            if (ggc.object.getShapeType() == GameObjectType.RECTANGLE) {
                RectangleEntityData red = (RectangleEntityData) ed;
                red.setEntityBounds(
                    PhysicMathUtils.getPositionInPixel(ggc.object.getBody()),
                    ggc.object.getWidth(),
                    ggc.object.getHeight()
                );
                red.setAngle((float) Math.toDegrees(-ggc.object.getBody().getAngle()));
            }
            else if (ggc.object.getShapeType() == GameObjectType.CIRCLE) {
                CircleEntityData red = (CircleEntityData) ed;
                red.setEntityBounds(
                    PhysicMathUtils.getPositionInPixel(ggc.object.getBody()),
                    ggc.object.getRadius()
                );
                red.setAngle((float) Math.toDegrees(-ggc.object.getBody().getAngle()));
            }
        });
        this.entityData.add(ggc);
        return ggc;
    }

    /**
     * Creates a circular physics body at the given screen position with the specified radius and physical properties.
     *
     * @param pi         the physical properties of the body (e.g., type, friction, etc.)
     * @param centerX    the X coordinate of the center (in pixels)
     * @param centerY    the Y coordinate of the center (in pixels)
     * @param radius     the radius of the circle (in pixels)
     * @return the created Box2D Body instance
     */
    public Body createCircle(PhysicInformation pi, float cx, float cy, float radius) {
        CircleShape s = new CircleShape();
        s.setRadius(PhysicMathUtils.pixelToMeter(radius));
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = pi.type;
        bodyDef.position = new Vec2(PhysicMathUtils.pixelToMeter(cx), PhysicMathUtils.pixelToMeter(cy));
        bodyDef.active = true;

        FixtureDef fixture = new FixtureDef();
        fixture.shape = s;
        fixture.restitution = pi.restitution;
	    fixture.density = pi.density;
	    fixture.friction = pi.friction;
        Body body = box2dWorld.createBody(bodyDef);
        body.createFixture(fixture);
        return body;
    }

    public Body createBox(PhysicInformation pi, float x, float y, float w, float h) {
        PolygonShape s = new PolygonShape();
		s.setAsBox(PhysicMathUtils.pixelToMeter(w/2), PhysicMathUtils.pixelToMeter(h/2));
        BodyDef body = new BodyDef();
        body.type = pi.type;
        body.position = new Vec2(PhysicMathUtils.pixelToMeter(x+w/2), PhysicMathUtils.pixelToMeter(y+h/2));
        body.active = true;

        FixtureDef fixture = new FixtureDef();
        fixture.shape = s;
        fixture.restitution = pi.restitution;
	    fixture.density = pi.density;
	    fixture.friction = pi.friction;
        Body boxBody = box2dWorld.createBody(body);
        boxBody.createFixture(fixture);
        return boxBody;
    }

    public List<GameObjectCompound> getEntityData() {
        return Collections.unmodifiableList(entityData);
    }


}
