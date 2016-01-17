import com.leapmotion.leap.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class RockHand implements Runnable {

	JFrame frame;
	JPanel titlePanel;
	JPanel instructionPanel;
	JPanel endPanel;
	JPanel helperPanel;
	ListenerImpl listener;
	Controller controller;
	String hand;
	String username;
	JButton endLabel;
	int hands = 8;

	public JFrame getFrame() {
		return frame;
	}

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

		JLabel helper = new JLabel();
		helper.setIcon(new ImageIcon("wait_for_hand.png"));

		JButton playButton = new JButton();
		playButton.setIcon(new ImageIcon("title_screen.png"));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGame();
			}
		});

		/*
		 * JButton endButton = new JButton(); endButton.setIcon(new
		 * ImageIcon("end_screen.png")); endButton.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * System.out.println("THE BUTTON CLICKED"); restartGame(); } });
		 */

		instructionPanel.add(instructions);
		helperPanel.add(helper);
		titlePanel.add(playButton);
		// endPanel.add(endButton);
		frame.add(titlePanel);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void playGame() {
		System.out.println("PLAYING GAME");

		String[] options = { "left", "right" };

		username = JOptionPane.showInputDialog("Enter your username:");
		int handNumber = JOptionPane.showOptionDialog(null, "Choose which hand you want to exercise", "Choose hand",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		hand = (handNumber == 0 ? "left" : "right");
		System.out.println(hand);

		frame.remove(titlePanel);
		frame.validate();
		frame.invalidate();
		frame.repaint();

		frame.add(instructionPanel);
		frame.validate();
		frame.invalidate();
		frame.repaint();
		
		Timer t = new Timer(1000 * 7, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishInstructions();
			}
		});
		
		t.setRepeats(false);
		t.start();
	}

	public void finishGame(int score, int numberHands) {
		String messageString = "";
		try {
			messageString = analytics.getAnalysis(username, score, hand, numberHands);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("here");
		// operate on score
		// frame.remove(emmettgame);

		endLabel = new JButton(new ImageIcon("end_screen.png"));
		endLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("THE BUTTON CLICKED");
				restartGame();
			}
		});
		endLabel.setLayout(new GridBagLayout());

		String[] mArray = messageString.split(" ");
		String htmlString = "<html>";
		for (int i = 0; i < mArray.length; i++) {
			if (i % 7 == 0) {
				htmlString += "<br>";
			}
			htmlString += mArray[i] + " ";
		}
		htmlString += "</html>";

		JLabel message = new JLabel(htmlString);
		message.setFont(new Font("helvetica", Font.PLAIN, 26));
		message.setBounds(261, 234, 650, 320);

		// message.setLocation(300, 300);
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
		frame.validate();
		frame.invalidate();
		frame.repaint();

		Timer t = new Timer(1000 * 1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addListener(listener);

				while (!Backend.isValidHeight(listener.getFrame())) {
					//System.out.println("NOT GOOD");
				}

				controller.removeListener(listener);

				frame.remove(helperPanel);
				frame.validate();
				frame.invalidate();
				frame.repaint();

				fakeGame();
			}
		});
		t.setRepeats(false);
		t.start();

	}

	public void fakeGame() {

		Score score = new Score();

		Engine engine = new Engine();
		
		

		engine.getScene().setFrameInterval(10);
		engine.getScene().addEntity(new Background());
		
		controller.addListener(listener);

		for (int i = 0; i < hands; i++) {
			int option = ((int) (5 * Math.random()) + 1);

			
			
			switch (option) {

			case 1:
				engine.getScene().addEntity(new Hand("fist.png", i, score, listener));
				break;

			case 2:
				engine.getScene().addEntity(new Hand("ring.png", i, score, listener));
				break;

			case 3:
				engine.getScene().addEntity(new Hand("spread.png", i, score, listener));
				break;

			case 4:
				engine.getScene().addEntity(new Hand("thumbs_up.png", i, score, listener));
				break;

			case 5:

				int choice = ((int) (4 * Math.random()) + 1);

				switch (choice) {

				case 1:
					engine.getScene().addEntity(new Hand("1_bend.png", i, score, listener));
					break;

				case 2:
					engine.getScene().addEntity(new Hand("2_bend.png", i, score, listener));
					break;

				case 3:
					engine.getScene().addEntity(new Hand("3_bend.png", i, score, listener));
					break;

				case 4:
					engine.getScene().addEntity(new Hand("4_bend.png", i, score, listener));
					break;
				default:
					engine.getScene().addEntity(new Hand("1_bend.png", i, score, listener));
					break;
				}
				break;

			default:
				engine.getScene().addEntity(new Hand("ring.png", i, score, listener));
				break;
			}
			
		}
		//controller.removeListener(listener);


		engine.getScene().addEntity(score);

		engine.init(frame, hands);
		
		frame.validate();
		frame.invalidate();
		frame.repaint();

		Timer t = new Timer((int) 5500 * hands, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeListener(listener);
				System.out.println("called timer");
				finishGame(score.getScore(), hands);
			}
		});
		t.setRepeats(false);
		t.start();

		
		//finishGame(100);
			
	}

	public void restartGame() {
		System.out.println("RESTARTING GAME");
		frame.remove(endLabel);
		frame.add(titlePanel);
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RockHand());
	}
}
