

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Hand extends Entity{
	
	private Image image;
	private int type;
	
	/* fist = 1
	 * ring = 2
	 * spread = 3
	 * thumbs_up = 4
	 * 1_bend = 5
	 * 2_bend = 6
	 * 3_bend = 7
	 * 4_bend = 8*/
	
	private int time;
	private int x;
	private int y;
	private Score score;
	private ListenerImpl l;
	private boolean hasScored = false;
	
	public Hand(String gesture, int index, Score score, ListenerImpl l){
		this.y = -400 * index;
		this.l = l;
		this.score = score;
		if(gesture.equals("fist.png")){
			x = 230;
			type = 1;
			ImageIcon ii = new ImageIcon("fist.png");
			image = ii.getImage();
		}
		else if(gesture.equals("ring.png")){
			x = 60;
			type = 2;
			ImageIcon ii = new ImageIcon("ring.png");
			image = ii.getImage();
		}
		else if(gesture.equals("spread.png")){
			x = 400;
			type = 3;
			ImageIcon ii = new ImageIcon("spread.png");
			image = ii.getImage();
		}
		else if(gesture.equals("thumbs_up.png")){
			x = 570;
			type = 4;
			ImageIcon ii = new ImageIcon("thumbs_up.png");
			image = ii.getImage();
		}
		else if(gesture.equals("1_bend.png")){
			x = 745;
			type = 5;
			ImageIcon ii = new ImageIcon("1_bend.png");
			image = ii.getImage();
		}
		else if(gesture.equals("2_bend.png")){
			x = 745;
			type = 6;
			ImageIcon ii = new ImageIcon("2_bend.png");
			image = ii.getImage();
		}
		else if(gesture.equals("3_bend.png")){
			x = 745;
			type = 7;
			ImageIcon ii = new ImageIcon("3_bend.png");
			image = ii.getImage();
		}
		else if(gesture.equals("4_bend.png")){
			x = 745;
			type = 8;
			ImageIcon ii = new ImageIcon("4_bend.png");
			image = ii.getImage();
		}
		else{
			x = 400;
			type = 3;
			ImageIcon ii = new ImageIcon("spread.png");
			image = ii.getImage();
		}
	}
	
	public void update(){
		time++;
	}
	
	public int getTime(){
		return time;
	}
	
	public void render(Graphics g){
		if(this.time % 20 == 0){
		if(this.time > ((-y * 10) + 4120) && this.time < ((-y * 10) + 6120) && !hasScored){
			//System.out.println(type);
			switch(type) {

			case 1:
				if(Backend.isFist(l.getFrame())) {score.incScore(); hasScored = true;}
				break;

			case 2:
				if(Backend.isOShape(l.getFrame())) {score.incScore(); hasScored = true;}
				break;

			case 3:
				if(Backend.isSpread(l.getFrame())) {score.incScore(); hasScored = true;}
				break;

			case 4:
				if(Backend.isThumbsUp(l.getFrame())) {score.incScore(); hasScored = true;}
				break;

			case 5:
				if(Backend.getFingerBend(l.getFrame()) == 4) {score.incScore(); hasScored = true;}
				break;
				
			case 6:
				if(Backend.getFingerBend(l.getFrame()) == 3) {score.incScore(); hasScored = true;}
				break;
				
			case 7:
				if(Backend.getFingerBend(l.getFrame()) == 2) {score.incScore(); hasScored = true;}
				break;
				
			case 8:
				if(Backend.getFingerBend(l.getFrame()) == 1) {score.incScore(); hasScored = true;}
				break;
			}		
		} 
		
		
	}	
		if(this.time < ((-y * 10) + 14120)){
			g.drawImage(image, x, (int) (y+(time / 10)), null);
		}
	}

}
