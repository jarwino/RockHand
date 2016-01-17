

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Display extends JPanel implements Runnable {
	
	private Engine engine;
	
	private JFrame frame;
	public Thread thread;

	public Display(Engine engine) {
	
	
		
		this.engine = engine;
		
		//frame = new JFrame();
		thread = new Thread(this);
	}
	
	public void init(JFrame f, int hands) {
		this.frame = f;
		//frame.setSize(1200, 750);
		//frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = this;
		frame.add(p);
		
		
		Timer t = new Timer((int) 6500 * hands, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(p);
			}
		});
		t.setRepeats(false);
		t.start();
		//RockHand.getFrame()
		
		thread.start();
	}
	
	public void paintComponent(Graphics g) {
		
		for(int i = 0; i < engine.getScene().getEntities().size(); i++)
			engine.getScene().getEntities().get(i).render(g);
	}

	public void run() {
		
		while(true) {
			
			frame.repaint();
			frame.revalidate();
			
			try {
				Thread.sleep(1);
			}
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}