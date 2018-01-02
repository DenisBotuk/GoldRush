package goldRush;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

public class Display extends JFrame {
	boolean star1;
	boolean star2;
	boolean star3;
	int energy;
	int stepCount;
	int goldCount;
	int goldMax = 4000;
	int goldBagCount;
	int goldBagMax;
	int posX;
	int posY;
	int mapSize = 20;
	int levelCounter = 1;
	String name = "Denis";

	Bag bag;

	JLabel[][] lField;
	
	JTextField loginText;

	JButton bExit;
	JButton bStart;
	JButton bStatistic;
	JButton bMap;
	JButton bMain;
	JButton bSubmit;

	JLabel lStar1;
	JLabel lStar2;
	JLabel lStar3;
	JLabel lBag;
	JLabel lCoin;
	JLabel lGold;
	JLabel lLightning;
	JLabel lEnergy;

	ImageIcon menu = new ImageIcon("menu.png");
	JLabel endOfLevel = new JLabel(new ImageIcon("endOfLevel.jpg"));
	JLabel login = new JLabel(new ImageIcon("login.jpg"));
	JLabel main = new JLabel(new ImageIcon("main.jpg"));
	JLabel statistic = new JLabel(new ImageIcon("statistics.jpg"));
	JLabel lHero = new JLabel();
	ImageIcon[] iCrashR = { new ImageIcon("crash_r_4.png"),
							new ImageIcon("crash_l_4.png"),
							new ImageIcon("crash_down_6.png"),
							new ImageIcon("crash_up_4.png")}; 

	ImageIcon[] crashedBlock = { new ImageIcon("crashed_1.jpg"),
			new ImageIcon("crashed_2.jpg") };
	JPanel pEnd;
	JPanel pLevel;
	JPanel pMain;
	JPanel pLogin;
	JPanel pStatistic;
	ImageIcon image;
	ImageIcon bagImage;

	Field field;

	Goldman hero;

	Display() throws FileNotFoundException, IOException {

		super("Gold Rush by Denis Botuk v1.0");
		loginPanel();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void levelPanel() throws FileNotFoundException, IOException {
		resetLevel();
	
		lStar1 = new JLabel();
		lStar1.setIcon(new ImageIcon("star_1.png"));
		lStar2 = new JLabel();
		lStar2.setIcon(new ImageIcon("star_1.png"));
		lStar3 = new JLabel();
		lStar3.setIcon(new ImageIcon("star_1.png"));
		lCoin = new JLabel();
		lCoin.setIcon(new ImageIcon("coin.png"));
		lGold = new JLabel(" " + goldCount);
		lGold.setSize(120, 120);
		lGold.setForeground(Color.white);
		lGold.setFont(new Font("serif", 0, 50));
		
		field = new Field(levelCounter);
		field.initialize();
		energy = field.getEnergy();
		hero = new Goldman(energy);
		bag = hero.getBag();
		bagImage = bag.getImage();
		lBag = new JLabel();
		lBag.setIcon(bagImage);
		
		lEnergy = new JLabel("   " + energy);
		lEnergy.setSize(120, 120);
		lEnergy.setForeground(Color.white);
		lEnergy.setFont(new Font("serif", 0, 50));
		lLightning = new JLabel();
		lLightning.setIcon(new ImageIcon("lightning.png"));

		pLevel = new JPanel();
		pLevel.setLayout(new BorderLayout());
		setContentPane(pLevel);
		setSize(4000, 700);
		
		lField = new JLabel[mapSize][mapSize];
		JPanel pField = new JPanel();
		pField.setLayout(new GridLayout(mapSize, mapSize));
		JPanel pLeft = new JPanel();

		JLabel lLeft = new JLabel(new ImageIcon("desk.jpg"));
		lLeft.setLayout(new GridLayout(5, 1));
		JPanel pRight = new JPanel();

		JLabel lRight = new JLabel(new ImageIcon("desk.jpg"));
		lRight.setLayout(new GridLayout(5, 1));
		pRight.add(lRight);

		lLeft.add(lCoin);
		lLeft.add(lGold);

		lLeft.add(lStar1);
		lLeft.add(lStar2);
		lLeft.add(lStar3);

		lRight.add(new JLabel());
		lRight.add(lLightning);
		lRight.add(lEnergy);
		lRight.add(lBag);

		pLeft.add(lLeft);

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				lField[i][j] = new JLabel();
				lField[i][j].setIcon(field.getBlock(i, j).getImage());
				pField.add(lField[i][j]);
				lField[i][j].setLayout(new BorderLayout());
			}
		}
		lHero.setIcon(new ImageIcon("hero_2.png"));
		lField[posX][posY].add(lHero);
		pLevel.add(pField, BorderLayout.CENTER);
		pLevel.add(pLeft, BorderLayout.WEST);
		pLevel.add(pRight, BorderLayout.EAST);
		pack();
		setLocationRelativeTo(null);
		keyListener();
		
	}

	void keyListener() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_RIGHT) {
					if (posX < mapSize - 1) {
						if (field.getBlock(posY, posX + 1).isCrashed()) {
							lField[posY][posX].remove(lHero);
							hero.moveRight();
							lHero.setIcon(hero.getImage());
							lField[posY][++posX].add(lHero);
							repaint();
						} else if (!field.getBlock(posY, posX + 1).isStone()) {
							if (field.getBlock(posY, posX + 1).isGold() && bag.isFull()) {
							} else {
								hero.crashBlock(field.getBlock(posY, posX + 1));
								lHero.setIcon(iCrashR[0]);
						
								field.getBlock(posY + 1, posX + 1).changeImage(true);
								lField[posY + 1][posX + 1].setIcon(field.getBlock(posY + 1, posX + 1).getImage());
								repaint();
								field.getBlock(posY - 1, posX + 1).changeImage(true);
								lField[posY - 1][posX + 1].setIcon(field.getBlock(posY - 1, posX + 1).getImage());
								repaint();
								field.getBlock(posY, posX + 2).changeImage(true);
								lField[posY][posX + 2].setIcon(field.getBlock(posY, posX + 2).getImage());
								repaint();
								field.getBlock(posY - 1, posX + 2).changeImage(true);
								lField[posY - 1][posX + 2].setIcon(field.getBlock(posY - 1, posX + 2).getImage());
								repaint();
								field.getBlock(posY + 1, posX + 2).changeImage(true);
								lField[posY + 1][posX + 2].setIcon(field.getBlock(posY + 1, posX + 2).getImage());
								repaint();
								checkBlocks(posY, posX + 1);

							}
						}
						checkOneStep();
					}

				}

				if (key == KeyEvent.VK_DOWN) {
					if (posY < mapSize - 1) {
						if (field.getBlock(posY + 1, posX).isCrashed()) {
							lField[posY][posX].remove(lHero);
							hero.moveDown();
							lHero.setIcon(hero.getImage());
							lField[++posY][posX].add(lHero);
							repaint();
						} else if (!field.getBlock(posY + 1, posX).isStone()) {
							if (field.getBlock(posY + 1, posX).isGold() && bag.isFull()) {
							} else {
								hero.crashBlock(field.getBlock(posY + 1, posX));
								lHero.setIcon(iCrashR[2]);
								field.getBlock(posY + 2, posX).changeImage(true);
								lField[posY + 2][posX].setIcon(field.getBlock(posY + 2, posX).getImage());
								repaint();
								field.getBlock(posY + 2, posX + 1).changeImage(true);
								lField[posY + 2][posX + 1].setIcon(field.getBlock(posY + 2, posX + 1).getImage());
								repaint();
								field.getBlock(posY + 2, posX - 1).changeImage(true);
								lField[posY + 2][posX - 1].setIcon(field.getBlock(posY + 2, posX - 1).getImage());
								repaint();
								field.getBlock(posY + 1, posX + 1).changeImage(true);
								lField[posY + 1][posX + 1].setIcon(field.getBlock(posY + 1, posX + 1).getImage());
								repaint();
								field.getBlock(posY + 1, posX - 1).changeImage(true);
								lField[posY + 1][posX - 1].setIcon(field.getBlock(posY + 1, posX - 1).getImage());
								repaint();
								if (field.getBlock(posY + 1, posX).isCrashed()) {
									field.getBlock(posY + 1, posX).changeCrashedImage();
									field.getBlock(posY + 1, posX).changeImage();
								} 
								lField[posY + 1][posX].setIcon(field.getBlock(posY + 1, posX).getImage());
								repaint();
								if (field.getBlock(posY + 2, posX).isCrashed()) {
									field.getBlock(posY + 2, posX).changeCrashedImage();
									field.getBlock(posY + 2, posX).changeImage();
									lField[posY + 2][posX].setIcon(field.getBlock(posY + 2, posX).getImage());
									repaint();
								}
							}

						}
						checkOneStep();
					}
				}

				if (key == KeyEvent.VK_LEFT) {
					if (posX > 0) {
						if (field.getBlock(posY, posX - 1).isCrashed()) {
							lField[posY][posX].remove(lHero);
							hero.moveLeft();
							lHero.setIcon(hero.getImage());
							lField[posY][--posX].add(lHero);
							repaint();
							if (hero.getX() == 0 && hero.getY() == 0) {
								goldCount += hero.handOverGold();
							}
						} else if (!field.getBlock(posY, posX - 1).isStone()) {
							if (field.getBlock(posY, posX - 1).isGold() && bag.isFull()) {
							} else {
								hero.crashBlock(field.getBlock(posY, posX - 1));
								lHero.setIcon(iCrashR[1]);
								field.getBlock(posY, posX - 2).changeImage(true);
								lField[posY][posX - 2].setIcon(field.getBlock(posY, posX - 2).getImage());
								repaint();
								field.getBlock(posY + 1, posX - 1).changeImage(true);
								lField[posY + 1][posX - 1].setIcon(field.getBlock(posY + 1, posX - 1).getImage());
								repaint();
								field.getBlock(posY - 1, posX - 1).changeImage(true);
								lField[posY - 1][posX - 1].setIcon(field.getBlock(posY - 1, posX - 1).getImage());
								field.getBlock(posY - 1, posX - 2).changeImage(true);
								lField[posY - 1][posX - 2].setIcon(field.getBlock(posY - 1, posX - 2).getImage());
								repaint();
								field.getBlock(posY + 1, posX - 2).changeImage(true);
								lField[posY + 1][posX - 2].setIcon(field.getBlock(posY + 1, posX - 2).getImage());
								repaint();
								checkBlocks(posY, posX - 1);

							}

						}
						checkOneStep();
					}
				}

				if (key == KeyEvent.VK_UP) {
					if (posY > 0) {
						if (field.getBlock(posY - 1, posX).isCrashed()) {
							lField[posY][posX].remove(lHero);
							hero.moveUp();
							lHero.setIcon(hero.getImage());
							lField[--posY][posX].add(lHero);
							repaint();
							if (hero.getX() == 0 && hero.getY() == 0) {
								goldCount += hero.handOverGold();
							}
						} else if (!field.getBlock(posY - 1, posX).isStone()) {
							if (field.getBlock(posY - 1, posX).isGold()
									&& field.getBlock(posY - 1, posX).getDegree() > 1 && !bag.isFull()) {
								hero.crashBlock(field.getBlock(posY - 1, posX));
								lHero.setIcon(iCrashR[3]);

								lField[posY - 1][posX].setIcon(field.getBlock(posY - 1, posX).getImage());
								repaint();
							}
						}
						checkOneStep();
					}

				}

			}
		});

	}
	
	private void endLevelPanel(){
		pEnd = new JPanel();
		int starCount = 0;
		if(star1){
			++starCount;
		}
		if(star2){
			++starCount;
		}
		if(star3){
			++starCount;
		}
		setContentPane(pEnd);
		pEnd.add(endOfLevel);
		JLabel title = new JLabel("Total Gold:");
		endOfLevel.add(title);
		title.setLocation(100, 100);
		title.setSize(350, 50);
		title.setForeground(Color.white);
		title.setFont(new Font("serif", 0, 30));
		JLabel total = new JLabel("" + goldCount);
		endOfLevel.add(total);
		total.setLocation(550, 100);
		total.setSize(350, 50);
		total.setForeground(Color.white);
		total.setFont(new Font("serif", 0, 30));
		JLabel star_1;
		if(star1){
			star_1 = new JLabel(new ImageIcon("star_2.png"));
		} else{
			star_1 = new JLabel(new ImageIcon("star_1.png"));
		}
		JLabel star_2;
		if(star2){
			star_2 = new JLabel(new ImageIcon("star_2.png"));
		} else{
			star_2 = new JLabel(new ImageIcon("star_1.png"));
		}
		JLabel star_3;
		if(star3){
			star_3 = new JLabel(new ImageIcon("star_2.png"));
		} else{
			star_3 = new JLabel(new ImageIcon("star_1.png"));
		}
		endOfLevel.add(star_1);
		star_1.setLocation(300, 300);
		star_1.setSize(120, 120);
		endOfLevel.add(star_2);
		star_2.setLocation(430, 300);
		star_2.setSize(120, 120);
		endOfLevel.add(star_3);
		star_3.setLocation(560, 300);
		star_3.setSize(120, 120);
		
		bMain = new JButton();
		bMain.setIcon(menu);
		endOfLevel.add(bMain);
		bMain.setLocation(715, 390);
		bMain.setSize(70, 70);
		bMain.setOpaque(false);
		bMain.setContentAreaFilled(false);
		bMain.setBorderPainted(false);
		bMain.setLayout(null);
		bMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel();
			}
		});

		pack();
		setLocationRelativeTo(null);

		
	}

	private void statisticsPanel() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		pStatistic = new JPanel();
		setContentPane(pStatistic);
		ImageIcon back = new ImageIcon("back.jpg");
		JLabel title = new JLabel("STATISTICS");
		statistic.add(title);
		title.setLocation(450, 10);
		title.setSize(350, 50);
		title.setForeground(Color.white);
		title.setFont(new Font("serif", 0, 50));
		pStatistic.add(statistic);
		pStatistic.add(statistic);
		bMain = new JButton();
		bMain.setIcon(back);
		statistic.add(bMain);
		bMain.setLocation(0, 585);
		bMain.setSize(115, 115);
		bMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel();
			}
		});
		/*JScrollPane scrollPanel = new JScrollPane(lText);
		pStatistic.add(scrollPanel, BorderLayout.CENTER);*/

		pack();
		setLocationRelativeTo(null);
	}

	private void loginPanel(){
		pLogin = new JPanel();
		setContentPane(pLogin);
		pLogin.add(login);
		loginText = new JTextField(15);
		JLabel title = new JLabel("Please, write down your nickname:");
		login.add(title);
		title.setLocation(400, 10);
		title.setSize(300, 40);
		title.setBackground(Color.yellow);
		title.setOpaque(true);
		title.setFont(new Font("serif", 0, 21));
		login.add(loginText);
		loginText.setLocation(550, 70);
		loginText.setSize(150, 50);
		loginText.setBackground(Color.yellow);
		loginText.setFont(new Font("serif", 0, 25));
		bSubmit = new JButton("Submit");
		login.add(bSubmit);
		bSubmit.setLocation(600, 150);
		bSubmit.setSize(100, 50);
		bSubmit.setBackground(Color.yellow);
		bSubmit.setFont(new Font("serif", 0, 20));
		bSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				name = loginText.getText();
				mainPanel();
			}
		});
		pack();
		setLocationRelativeTo(null);
	}
	private void mainPanel() {
		pMain = new JPanel();
		setContentPane(pMain);
		pMain.add(main);
		bExit = new JButton("Exit");
		main.add(bExit);
		bExit.setLocation(735, 595);
		bExit.setSize(350, 80);
		bExit.setBackground(Color.yellow);
		bExit.setFont(new Font("serif", 0, 50));
		bStatistic = new JButton("Get Statistics");
		main.add(bStatistic);
		bStatistic.setLocation(735, 483);
		bStatistic.setSize(350, 80);
		bStatistic.setBackground(Color.yellow);
		bStatistic.setFont(new Font("serif", 0, 50));
		bStart = new JButton("Play");
		main.add(bStart);
		bStart.setLocation(735, 371);
		bStart.setSize(350, 80);
		bStart.setBackground(Color.yellow);
		bStart.setFont(new Font("serif", 0, 50));
		JLabel title = new JLabel("   Hello, " + name + "!");
		main.add(title);
		title.setLocation(735, 259);
		title.setSize(350, 80);
		title.setBackground(Color.yellow);
		title.setOpaque(true);
		title.setFont(new Font("serif", 0, 50));

		pack();
		setLocationRelativeTo(null);
		initListeners();
	}

	private void initListeners() {
		bStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pMain.setVisible(false);
				try {
					levelPanel();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		bStatistic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					statisticsPanel();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	void checkOneStep() {
		bag.changeImage();
		lBag.setIcon(bag.getImage());
		repaint();
		if (posX == 0 && posY == 0) {
			goldCount += hero.handOverGold();
		}
		lGold.setText(" " + goldCount);
		repaint();
		energy = hero.getEnergy();
		if(energy == 0){
			//levelCounter++;
			endLevelPanel();
		}
		lEnergy.setText("   " + energy);
		repaint();
		if (goldCount > goldMax / 3) {
			star1 = true;
			lStar1.setIcon(new ImageIcon("star_2.png"));
			repaint();
		}
		if (goldCount > 2 * goldMax / 3) {
			star2 = true;
			lStar2.setIcon(new ImageIcon("star_2.png"));
			repaint();
		}
		if (goldCount > goldMax) {
			star3 = true;
			lStar3.setIcon(new ImageIcon("star_2.png"));
			repaint();
		}
	}

	private void checkBlocks(int y, int x) {
		if (field.getBlock(y, x).isCrashed() && field.getBlock(y - 1, x).isCrashed()) {
			field.getBlock(y, x).changeCrashedImage();
			field.getBlock(y, x).changeImage();
		} 
		lField[y][x].setIcon(field.getBlock(y, x).getImage());
		if(field.getBlock(y + 1, x).isCrashed()){
			field.getBlock(y + 1, x).changeCrashedImage();
			field.getBlock(y + 1, x).changeImage();
		}
		lField[y + 1][x].setIcon(field.getBlock(y + 1, x).getImage());
		
	}
	private void resetLevel(){
		star1 = false;
		star2 = false;
		star3 = false;
		goldCount = 0;
		posX = 0;
		posY = 0;
		goldBagCount = 0;
	}

}
