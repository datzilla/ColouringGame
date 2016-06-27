package colouringgame.framework;
import java.util.ArrayList;

// Animation, we are aiming for 60 frames in a second
// We assume that every loop cycle in StartingClass is 1 millisecond, 
// we want 60 frames per second so that the monitor can update properly.
// we are sleeping by 17 millisecond in the StartingClass to get 60 frames
// So our Animation class treats the time count as 1 every 17 milliseconds, which equal to 60 milliseconds/frames per second
// the unit interval is in frame not time. The variable names as referred as in Time.


public class Animation {
	private ArrayList <Sprite> frames;
	private int currentFrame;
	private long lapsedTime;
	private long frameTime;
	private long totalDuration;

	public Animation(ArrayList <Sprite> inAllSprites) {
		frames = inAllSprites;
		
		synchronized (this) {
			lapsedTime = 0;
			currentFrame = 0;
			frameTime = 0;
		}
		
		for (Sprite f : frames) {
			totalDuration += f.getDuration();
		}
	}

	public synchronized void update() {
		lapsedTime += 1;
		frameTime +=1;
		
		if (frames.size() > 1) {
			//switch to the next frame when the frame time is equals or above
			if (frameTime >= frames.get(currentFrame).getDuration()) {
				frameTime = 0;
				currentFrame++;
			}
			
			// if the lapsed time is greater than the totalDuration
			// restart the frame to the beginning
			if (lapsedTime >= totalDuration) {
				lapsedTime = 0;
				frameTime = 0;
				currentFrame = 0;
			}
		}
	}
	
	public void setTotalDuation (long inTotalDuration) {
		this.totalDuration = inTotalDuration;
	}

	public synchronized Sprite getSprite (){
		return frames.get(currentFrame);
		
	}
}
