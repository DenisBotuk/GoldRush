package goldRush;

import javax.swing.ImageIcon;

public class GrassBlock extends Block{
	private int number;
	
	
	GrassBlock(int x, int y, int number) {
		super(x, y);
		this.number = number;
		isGrass = true;
		setVisibility(true);
		image = new ImageIcon("grass_" + number +".jpg");
	}


}
