

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score extends Entity{

	private int currScore;
	
	public Score(){
		currScore = 0;
	}
	
	public void update(){
		
	}
	
	public int getScore(){
		return currScore;
	}
	
	public void incScore(){
		currScore += 5;
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("CenturyGothic", Font.PLAIN, 45));
		g.drawString(((Integer)currScore).toString(), 1110, 63);
	}
}
