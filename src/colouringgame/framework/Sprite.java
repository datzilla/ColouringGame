package colouringgame.framework;

public class Sprite {
	private String pimagepath;
	private int pstartx;
	private int pstarty;
	private long duration;
	
	
	public Sprite (String inImagePath, long inDuration) {
		this.pimagepath = inImagePath;
		this.duration = inDuration;
	}
	
	public Sprite (String inImagePath, int inpstartx, int inpstarty) {
		this.pimagepath = inImagePath;
		this.pstartx = inpstartx;
		this.pstarty = inpstarty;
	}
	
	public Sprite (String inImagePath, int inpstartx, int inpstarty, boolean inVisibility) {
		this.pimagepath = inImagePath;
		this.pstartx = inpstartx;
		this.pstarty = inpstarty;
	}
	
	public String getImagePath () {
		return this.pimagepath;
	}
	
	public long getDuration () {
		return this.duration;
	}
	
	public int getStartX () {
		return this.pstartx;
	}
	
	public void setStartX (int inStartX) {
		this.pstartx = inStartX;
	}
	
	public int getStartY () {
		return this.pstarty;
	}
	
	public void setStartY (int inStartY) {
		this.pstarty = inStartY;
	}
}
