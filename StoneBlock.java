package goldRush;

import javax.swing.ImageIcon;

public class StoneBlock extends Block{

	StoneBlock(int x, int y, int degree){
		super(x,y);
		this.degree = degree;
		changeImage();
		isStone = true;
	}
	
	@Override
	public void changeImage(){
		image = new ImageIcon("stone.jpg");
	}
	
	@Override
	public void changeImage(boolean visibility) {
		this.visibility = visibility;
		if(!isVisible()){
			image = unvisibleImage;
		} else{
			changeImage();
		}
	}
}
