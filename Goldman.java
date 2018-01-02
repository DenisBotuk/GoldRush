package goldRush;

import javax.swing.ImageIcon;

public class Goldman {
	private int x;
	private int y;
	private int energy;
	private Bag bag;
	private int counterSteps;
	private ImageIcon[] images = {new ImageIcon("hero_2.png"), 
									new ImageIcon("hero_1.png"),
									new ImageIcon("u1.png"),
									new ImageIcon("u2.png")};
	private ImageIcon image;
	
	Goldman(int energy){
		bag = new Bag();
		x = 0;
		y = 0;
		this.energy = energy;
		image = images[0];
	}
	public void moveUp(){
		y--;
		counterSteps++;
		if(counterSteps == 5){
			energy--;
			counterSteps = 0;
		}
		image = images[2];
	}
	public void moveDown(){
		y++;
		counterSteps++;
		if(counterSteps == 5){
			energy--;
			counterSteps = 0;
		}
		image = images[3];
	}
	public void moveRight(){
		x++;
		counterSteps++;
		if(counterSteps == 5){
			energy--;
			counterSteps = 0;
		}
		image = images[0];
	}
	public void moveLeft(){
		x--;
		counterSteps++;
		if(counterSteps == 5){
			energy--;
			counterSteps = 0;
		}
		image = images[1];
	}
	public void crashBlock(Block block){
		bag.addGold(block.decreaseDegree());
		energy--;
	}
	public int handOverGold(){
		int gold = bag.getGoldAmount();
		bag.resetBag();
		return gold;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getEnergy(){
		return energy;
	}
	public ImageIcon getImage(){
		return image;
	}
	public void setImage(ImageIcon image){
		this.image = image;
	}
	public Bag getBag(){
		return bag;
	}
}
