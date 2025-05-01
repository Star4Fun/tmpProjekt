package mm.model.physics;
/**
 * Provides a thread-safe double buffer mechanism for entity data updates and rendering.
 * 
 * <p>This class allows one thread (typically the update loop) to write data to the back buffer,
 * while another thread (typically the render loop) reads from the front buffer without locking issues.</p>
 * 
 * <p>After each update, the buffers can be swapped to expose the latest data to the renderer.</p>
 */
public class EntityDataBufferSafe {

    private IEntityData front;
    private IEntityData back;

    /**
     * Constructs a new double-buffered entity data container.
     *
     * @param initialFront the initial front buffer (visible to render logic)
     * @param initialBack  the initial back buffer (used by game logic for writing)
     */
    public EntityDataBufferSafe(IEntityData initialFront, IEntityData initialBack) {
        this.front = initialFront;
        this.back = initialBack;
    }

    /**
     * Returns the current visible entity data for reading (typically used by the renderer).
     *
     * @return a stable reference to the front buffer
     */
    public IEntityData getVisible() {
        synchronized (this) {
            return front;
        }
    }

    /**
     * Returns the current writable entity data for updating (typically used by the update loop).
     *
     * @return a reference to the back buffer for writing new values
     */
    public IEntityData getWritable() {
        synchronized (this) {
            return back;
        }
    }

    /**
     * Performs an update on the back buffer and then swaps front and back buffers.
     *
     * <p>The provided update logic should apply all desired changes to the back buffer.
     * After the update, the buffer swap makes the updated data visible for the next render cycle.</p>
     *
     * @param updateLogic a function that modifies the back buffer
     */
    public void updateAndSwap(java.util.function.Consumer<IEntityData> updateLogic) {
        synchronized (this) {
            updateLogic.accept(back);
            swapInternal();
        }
    }

    /**
     * Internally swaps the front and back buffers.
     */
    private void swapInternal() {
        IEntityData tmp = front;
        front = back;
        back = tmp;
    }
}
