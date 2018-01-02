package goldRush;

import javax.swing.ImageIcon;

public class RockBlock extends Block{
	private String[] images = { "clay.jpg",
								"rock_1.jpg",
								"rock_2.jpg",
								"rock_3.jpg"};
	
	RockBlock(int x, int y, int degree){
		super(x,y);
		this.degree = degree;
		changeImage();
	}
	@Override
	public void changeImage() {
		if(degree == 0){
			image = new ImageIcon(crashedImage);
		} else{
			image = new ImageIcon(images[degree]);
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
