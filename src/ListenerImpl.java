import com.leapmotion.leap.*;

public class ListenerImpl extends Listener {
	
	Frame frame;
	
	public Frame getFrame() {
		return frame;
	}
	
	public void onConnect(Controller controller) {
        System.out.println("Connected");
        frame = controller.frame();
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onFrame(Controller controller) {
        //System.out.println("Frame available");
        
         frame = controller.frame();
        
        
        
        /*if (Backend.isFist(frame)) {
        	System.out.println("YOU MADE A FIST!");
        }
        
        if (Backend.isFingerBend(frame)) {
        	System.out.println(Backend.getFingerBend(frame));
        }
        
        if (Backend.isSpread(frame)) {
        	System.out.println("YOU MADE A SPREAD");
        }
        
        if (Backend.isThumbsUp(frame)) {
        	System.out.println("YOU MADE A THUMBS UP");
        }
        
        if (Backend.isOShape(frame)) {
        	System.out.println("YOU MADE AN O SHAPE");
        }*/
        
        //System.out.println(Backend.isValidHeight(frame));
       
        
        /*System.out.println("Frame id: " + frame.id()
        + ", timestamp: " + frame.timestamp()
        + ", hands: " + frame.hands().count()
        + ", fingers: " + frame.fingers().count()
        + ", tools: " + frame.tools().count()
        + ", gestures " + frame.gestures().count());*/
        
        
        
        
    }
}
