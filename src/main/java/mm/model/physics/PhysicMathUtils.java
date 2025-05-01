package mm.model.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class PhysicMathUtils {

	//Games ratio (meter to pixel)
	public static int ratio = 64;

	/**
	 * 
	 * @param vec0 the target
	 * @param vec1 the starting point
	 * @return the angle between the vectors in radians
	 **/
	public static float getAngleBetweenTwoVectors(Vec2 vec0, Vec2 vec1){
		return (float)Math.atan2(vec0.y-vec1.y, vec0.x-vec1.x);
	}
	
	public static float pixelToMeter(float pixel){
		return pixel / ratio;
	}
	
	public static float meterToPixel(float meter){
		return meter * ratio;
	}
	
	public static float distanceBetweenTwoVectors(Vec2 vec0, Vec2 vec1){
		Vec2 v = new Vec2();
		v.x = vec0.x - vec1.x;
		v.y = vec0.y - vec1.y;
		return (float) Math.sqrt(v.x * v.x + v.y * v.y);
	}
	
	public static Vec2 getPositionInPixel(Body b){
		return new Vec2(meterToPixel(b.getPosition().x), meterToPixel(b.getPosition().y));
	}
}
