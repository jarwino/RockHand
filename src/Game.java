import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.leapmotion.leap.Controller;

@SuppressWarnings("serial")
public class Game extends JPanel  {

	JFrame frame;
	JPanel titlePanel;
	JPanel instructionPanel;
	JPanel endPanel;
	JPanel helperPanel;
	ListenerImpl listener;
	Controller controller;
	
	


	public void run() {
		
		listener = new ListenerImpl();
		controller = new Controller();

		frame = new JFrame("Rock Hand");
		frame.setLocation(300, 300);
		frame.setPreferredSize(new Dimension(1200, 750));

		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());

		instructionPanel = new JPanel();
		instructionPanel.setLayout(new BorderLayout());
		
		helperPanel = new JPanel();
		helperPanel.setLayout(new BorderLayout());

		endPanel = new JPanel(new BorderLayout());
		
		
		
		

		JLabel instructions = new JLabel();
		instructions.setIcon(new ImageIcon("instructions.png"));
		
		JLabel helperPanel = new JLabel();
		helperPanel.setIcon(new ImageIcon("wait_for_hand.png"));

		JButton playButton = new JButton();
		playButton.setIcon(new ImageIcon("title_screen.png"));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGame();
			}
		});

		JButton endButton = new JButton();
		endButton.setIcon(new ImageIcon("end_screen.png"));
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartGame();
			}
		});

		instructionPanel.add(instructions);
		titlePanel.add(playButton);
		endPanel.add(endButton);
		frame.add(titlePanel);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void playGame() {
		System.out.println("PLAYING GAME");
		frame.remove(titlePanel);
		frame.validate();
		frame.invalidate();
		frame.repaint();
		frame.add(instructionPanel);
		frame.validate();
		frame.invalidate();
		frame.repaint();
		Timer t = new Timer(1000 * 5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishInstructions();
			}
		});
		t.setRepeats(false);
		t.start();
	}

	public void finishGame(int score) {
		System.out.println("here");
		// operate on score
		// frame.remove(emmettgame);
		
		JLabel endLabel = new JLabel(new ImageIcon("end_screen.png"));
		endLabel.setLayout(new GridBagLayout());
		JLabel message = new JLabel("hi");
		
		//message.setLocation(300, 300);
		endLabel.add(message);

		// endPanel.add(message, BorderLayout.CENTER);
		
		frame.add(endLabel);
		frame.validate();
		frame.invalidate();
		frame.repaint();
	}


	public void finishInstructions() {
		System.out.println("FINISHING INSTRUCTIONS");
		frame.remove(instructionPanel);
		// frame.add(actualGame);
		frame.validate();
		frame.invalidate();
		frame.repaint();

		helperScreen();
	}
	
	public void helperScreen() {
		System.out.println("HELPER SCREEN");
	
		frame.add(helperPanel);
		
		controller.addListener(listener);
		
		//while(Backend.isValidHeight(frame))
		
		frame.validate();
		frame.invalidate();
		frame.repaint();

		fakeGame();
	}

	public void fakeGame() {

		Timer t = new Timer(1000 * 5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishGame(98);
			}
		});
		t.setRepeats(false);
		t.start();
	}

	public void restartGame() {
		System.out.println("RESTARTING GAME");
		frame.remove(endPanel);
		frame.invalidate();
		frame.validate();
		frame.repaint();
		frame.add(titlePanel);
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		//SwingUtilities.invokeLater(new Game());
	}
}