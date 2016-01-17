

import java.util.ArrayList;

public class Scene implements Runnable{
	
	private Engine engine;
	
	private Thread thread;
	private int frameInterval;
	
	private ArrayList<Entity> entities;
	
	public Scene(Engine engine) {
		
		this.engine = engine;
	
		thread = new Thread(this);
		frameInterval = 1;
		
		entities = new ArrayList<Entity>();
	}
	
	public void init() {
		thread.start();
	}
	
	public void addEntity(Entity entity) {
		
		entity.setEngine(engine);
		
		entities.add(entity);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void updateScene() {
		
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).update();	
	}
	
	public void run() {
		
		while(true) {
			
			updateScene();
			
			try {
				Thread.sleep(frameInterval);
			}
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setFrameInterval(int frameInterval) {
		
	}
}