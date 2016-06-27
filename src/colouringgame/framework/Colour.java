package colouringgame.framework;

public class Colour {
	protected static Colour currentcolour;
	protected String colourname;
	protected String webcolour;
	protected long rgbcolour;
	
	public Colour (String inColourname) {
		this.colourname = inColourname;
	}
	
	public String getName () {
		return this.colourname;
	}
	
	public void setHexCode (String inHexCode) {
		this.webcolour = inHexCode;
	}
	
	public void setRGBCode (long inRGBCode) {
		this.rgbcolour = inRGBCode;
	}
	
	public long getRGBCode () {
		return this.rgbcolour;
	}
	
	public String getHexCode () {
		return this.webcolour;
	}
	
	public static void setCurrentColour (Colour inColour) {
		currentcolour = inColour;
	}
	
	public static Colour getCurrentColour () {
		return currentcolour;
	}
}
