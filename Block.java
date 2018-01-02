package goldRush;

import javax.swing.ImageIcon;

public abstract class Block {
	protected int x;
	protected int y;
	protected boolean visibility;
	protected int degree;
	protected ImageIcon image;
	protected boolean isStone;
	protected boolean isGold;
	protected boolean isGrass;
	ImageIcon unvisibleImage = new ImageIcon("unvisible.jpg");
	String crashedImage;

	Block(int x, int y) {
		this.x = x;
		this.y = y;
		crashedImage = "crashed_1.jpg";
		setVisibility(false);
	}

	public void changeImage() {
	}

	public void changeImage(boolean visibility) {
	}

	public int decreaseDegree(){
		--degree;
		changeImage();
		return 0;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public boolean isVisible() {
		return visibility;
	}

	public boolean isCrashed() {
		return degree == 0;
	}

	public boolean isStone() {
		return isStone;
	}

	public boolean isGold() {
		return isGold;
	}
	public boolean isGrass() {
		return isGrass;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void changeCrashedImage() {
		this.crashedImage = "crashed_2.jpg";
	}

	public int getDegree() {
		return degree;
	}
}
