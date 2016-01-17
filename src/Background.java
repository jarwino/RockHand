

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Background extends Entity {

	private Image image;
	
	public Background(){
		
		ImageIcon ii = new ImageIcon("background.png");
		image = ii.getImage();
		
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
}
