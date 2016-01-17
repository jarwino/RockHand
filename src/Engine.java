import javax.swing.JFrame;

public class Engine {
	
	private Display display;
	private Scene scene;
	
	
	public Engine() {
		display = new Display(this);
		scene = new Scene(this);
	}
	
	public void init(JFrame frame, int hands) {
		display.init(frame, hands);
		scene.init();
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public Scene getScene() {
		return scene;
	}
}