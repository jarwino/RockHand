

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Entity {
	
	private double time;
	
	public Ball() {
		
	}
	
	public void update() {
		time++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(0, (int) (time / 20), 100, 100);
	}
}