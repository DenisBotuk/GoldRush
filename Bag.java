package goldRush;

import javax.swing.ImageIcon;

public class Bag {
	private ImageIcon[] images = {new ImageIcon("bag_1.png"), 
									new ImageIcon("bag_2.png"), 
									new ImageIcon("bag_3.png"), 
									new ImageIcon("bag_4.png"),};
	private ImageIcon image;
	private static final int MAX_SIZE = 2000;
	private int goldAmount;
	Bag(){
		goldAmount = 0;
		image = images[0];
	}
	public int resetBag(){
		int temp = goldAmount;
		goldAmount = 0;
		return temp;
	}                       
	public boolean isFull(){
		return goldAmount > MAX_SIZE;
	}
	public void addGold(int gold){
		
		goldAmount += gold;
		changeImage();
	}
	public int getGoldAmount(){
		return goldAmount;
	}
	public void changeImage(){
		if(goldAmount == 0){
			image = images[0];
		} else if(goldAmount < MAX_SIZE/2 && goldAmount > 0){
			image = images[1];
		} else if(goldAmount < MAX_SIZE && goldAmount > MAX_SIZE/2){
			image = images[2];
		} else if(goldAmount >= MAX_SIZE){
			image = images[3];
		}
	}
	public ImageIcon getImage(){
		return image;
	}
}
