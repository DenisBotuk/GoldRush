package goldRush;

import javax.swing.ImageIcon;

public class GoldBlock extends Block {
	private int gold;
	private String[] images = { crashedImage,
								"clay.jpg",
								"gold_1.jpg",
								"gold_2.jpg",
								"gold_3.jpg",
								"gold_4.jpg",
								"gold_5.jpg",
								"gold_6.jpg"};

	GoldBlock(int x, int y, int degree) {
		super(x,y);
		isGold = true;
		this.degree = degree;
		setGold();
		changeImage();
	}
	private void setGold(){
		switch(degree){
		case 1:
			gold = 0;
			break;
		case 2:
			gold = 50;
			break;
		case 3:
			gold = 100;
			break;
		case 4:
			gold = 200;
			break;
		case 5:
			gold = 400;
			break;
		case 6:
			gold = 800;
			break;
		case 7:
			gold = 1600;
			break;
		}
	}

	public int getGold() {
		return gold;
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

	public int decreaseDegree(){
		--degree;
		setGold();
		changeImage();
		return gold;
	}

}
