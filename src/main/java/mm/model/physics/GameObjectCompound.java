package mm.model.physics;

public class GameObjectCompound {
    public final GameObject object;
    public final EntityDataBufferSafe buffer;

    public GameObjectCompound(GameObject object, EntityDataBufferSafe buffer) {
        this.object = object;
        this.buffer = buffer;
    }
}
