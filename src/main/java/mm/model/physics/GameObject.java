package mm.model.physics;

import org.jbox2d.dynamics.Body;

import mm.render.Sprite;

public class GameObject {

    private final Sprite theSprite;
    private final Body theBody;

    private final float width, height, radius;

    private final GameObjectType shapeType;

    public GameObject(Sprite sprite, Body body, float width, float height) {
        this.theSprite = sprite;
        this.theBody = body;
        this.shapeType = GameObjectType.RECTANGLE;
        this.width = width;
        this.height = height;
        this.radius = -1;
    }


    public GameObject(Sprite sprite, Body body, float radius) {
        this.theSprite = sprite;
        this.theBody = body;
        this.shapeType = GameObjectType.CIRCLE;
        this.width = -1;
        this.height = -1;
        this.radius = radius;
    }

    public float getRadius() {
        if(shapeType != GameObjectType.CIRCLE) {
            throw new IllegalStateException("Object is not a circle type.");
        }
        return radius;
    }

    public float getWidth() {
        if(shapeType != GameObjectType.RECTANGLE) {
            throw new IllegalStateException("Object is not a circle type.");
        }
        return width;
    }

    public float getHeight() {
        if(shapeType != GameObjectType.RECTANGLE) {
            throw new IllegalStateException("Object is not a circle type.");
        }
        return height;
    }

    public GameObjectType getShapeType() {
        return shapeType;
    }

    public Body getBody() {
        return theBody;
    }

    public Sprite getSprite() {
        return theSprite;
    }

}
