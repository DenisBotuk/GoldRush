package goldRush;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Field {
	private Block[][] blocks;
	private int energy;
	private String line;
	private int level;
	
	public Block getBlock(int x, int y){
		return blocks[x][y];
	}
	public int getEnergy(){
		return energy;
	}
	Field(int level){
		this.level = level;
	}
	
	void initialize() throws FileNotFoundException, IOException{
		try (
			    InputStream fis = new FileInputStream("map_" + level + ".txt");
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			) {
				line = br.readLine();
				energy = Integer.parseInt(line.trim());
				blocks = new Block[20][20];
				line = br.readLine();
					for(int i = 0; i < 20; i++){
						int str = Integer.parseInt(line.split(" ")[i]);
	        			blocks[0][i] = new GrassBlock(0, i, str);
					}
			        for(int i = 1; i < 20; i++){
			        	line = br.readLine();
			        	for(int j = 0; j < 20; j++){
			        		String str = line.split(" ")[j];
			        		if(str.split(",")[0].equals("s")){
			        			int degree = Integer.parseInt(str.split(",")[1]);
			        			blocks[i][j] = new StoneBlock(i, j, degree);
			        			if(i != 1)
			        				blocks[i][j].changeImage(false);
			        		} else if(str.split(",")[0].equals("r")){
			        			int degree = Integer.parseInt(str.split(",")[1]);
			        			blocks[i][j] = new RockBlock(i, j, degree);
			        			if(i != 1)
			        				blocks[i][j].changeImage(false);
			        		} else if(str.split(",")[0].equals("c")){
			        			int degree = Integer.parseInt(str.split(",")[1]);
			        			blocks[i][j] = new ClayBlock(i, j, degree);
			        			if(i != 1)
			        				blocks[i][j].changeImage(false);
			        		} else if(str.split(",")[0].equals("g")){
			        			int degree = Integer.parseInt(str.split(",")[1]);
			        			blocks[i][j] = new GoldBlock(i, j, degree);
			        			if(i != 1)
			        				blocks[i][j].changeImage(false);
			        		}
			        	}
			        }
			}
	}
}
