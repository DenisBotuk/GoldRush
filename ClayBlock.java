package goldRush;

import javax.swing.ImageIcon;

public class ClayBlock extends Block{
	private String icon = "clay.jpg";
	
	ClayBlock(int x, int y, int degree){
		super(x,y);
		this.degree = degree;
		image = new ImageIcon(icon);
	}
	@Override
	public void changeImage(){
		if(degree == 0){
			image = new ImageIcon(crashedImage);
		} else{
			image = new ImageIcon(icon);
		}
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
