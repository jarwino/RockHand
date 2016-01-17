import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;

public class Backend {

	public static String fingerToString(int finger) {
		switch (finger) {
		case 0:
			return "thumb";
		case 1:
			return "index";
		case 2:
			return "middle";
		case 3:
			return "ring";
		case 4:
			return "pinky";
		}
		return "invalid finger";
	}

	public static boolean isOneHand(Frame frame) {
		int numberOfHands = frame.hands().count();

		if (numberOfHands < 1) {
			// System.out.println("less than 1 hand");
			return false;
		} else if (numberOfHands > 1) {
			// System.out.println("more than 1 hand");
			return false;
		} else {
			return true;
		}
	}

	public static int getExtendedFingers(Hand hand) {
		int f = 0;
		for (int i = 0; i < hand.fingers().count(); i++) {
			if (hand.fingers().get(i).isExtended()) {
				f++;
			}
		}
		return f;
	}

	public static boolean listContainsFinger(FingerList fingerList, Finger.Type type) {
		for (int i = 0; i < fingerList.count(); i++) {
			if (fingerList.get(i).type() == type) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFist(Frame frame) {
		if (!isOneHand(frame)) {
			return false;
		} else {
			Hand hand = frame.hands().get(0);
			if (getExtendedFingers(hand) == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean isValidHeight(Frame frame) {
		return isOneHand(frame)
				&& frame.hands().get(0).stabilizedPalmPosition().getY() > 180;
	}

	public static boolean isSpread(Frame frame) {
		if (!isOneHand(frame)) {
			return false;
		} else {
			Hand hand = frame.hands().get(0);
			if (getExtendedFingers(hand) == 5) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean isFingerBend(Frame frame) {
		if (!isOneHand(frame)) {
			return false;
		} else {
			Hand hand = frame.hands().get(0);
			if (getExtendedFingers(hand) == 4) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean isThumbsUp(Frame frame) {
		if (!isOneHand(frame)) {
			return false;
		} else {
			Hand hand = frame.hands().get(0);
			if (getExtendedFingers(hand) == 1
					&& listContainsFinger(hand.fingers().extended(), Finger.Type.TYPE_THUMB)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean isOShape(Frame frame) {
		if (!isOneHand(frame)) {
			return false;
		} else {
			Hand hand = frame.hands().get(0);
			if (getExtendedFingers(hand) == 3
					&& !listContainsFinger(hand.fingers().extended(), Finger.Type.TYPE_THUMB)
					&& !listContainsFinger(hand.fingers().extended(), Finger.Type.TYPE_PINKY)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static int getFingerBend(Frame frame) {
		if (!(isFingerBend(frame))) {
			// System.out.println("please extend exactly 1 finger");
			return -1;
		} else {
			Hand hand = frame.hands().get(0);
			FingerList extendedFingerList = hand.fingers().extended();

			if (!(listContainsFinger(extendedFingerList, Finger.Type.TYPE_THUMB))) {
				//System.out.println("THUMB FINGER");
				return 0;
			} else if (!(listContainsFinger(extendedFingerList, Finger.Type.TYPE_INDEX))) {
				//System.out.println("INDEX FINGER");
				return 1;
			} else if (!(listContainsFinger(extendedFingerList, Finger.Type.TYPE_MIDDLE))) {
				//System.out.println("MIDDLE FINGER");
				return 2;
			} else if (!(listContainsFinger(extendedFingerList, Finger.Type.TYPE_RING))) {
				//System.out.println("RING FINGER");
				return 3;
			} else if (!(listContainsFinger(extendedFingerList, Finger.Type.TYPE_PINKY))) {
				//System.out.println("PINKY FINGER");
				return 4;
			} else {
				return -1;
			}
		}

	}

	
}
