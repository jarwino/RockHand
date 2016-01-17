import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class analytics {
   public static void main(String[] args) {
   }
   
   public static void postScore (String username, int score, String hand)  throws IOException{
      
      String file = "";
      File varTmpDir = new File(username + "_" + hand + ".txt");
      boolean exists = varTmpDir.exists();
      if (exists) {
         BufferedReader br = new BufferedReader(new FileReader(username + "_" + hand + ".txt"));
         try {
            String line;
            while ((line = br.readLine()) != null) {
               file += line + "\n";
            }
         }
         finally{
            br.close();
         }
      }
      
      String filename = username + "_" + hand + ".txt";
      PrintWriter fileWriter = new PrintWriter(filename, "UTF-8");
      fileWriter.println(file + score);
      fileWriter.close();
   }
   public static ArrayList<Integer> readInScores (String username, String hand) throws IOException {
      ArrayList<Integer> scores = new ArrayList<Integer>();
      BufferedReader br = new BufferedReader(new FileReader(username + "_" + hand + ".txt"));
      try {
         String line;
         while ((line = br.readLine()) != null) {
            scores.add(Integer.parseInt(line));
         }
      }
      finally{
         br.close();
      }
      return scores;
   }
   
   public static String getAnalysis( String username, int score, String hand, int numberHands) throws IOException {
      postScore(username, score, hand);
      String toReturn = "";
      ArrayList<Integer> previous = readInScores(username, hand);
      double average = 0;
      for (int i = 0; i < previous.size() - 1; i++) {
         average += previous.get(i)/(previous.size()-1);
      }
      if (score == 5 * numberHands) {
         toReturn += "Wow! A perfect score! Your hand is in great shape! Keep playing to make sure you're in good shape!";
      }
      else if (previous.size() == 1)
         toReturn += "Great first score of " + score + "! Keep playing, keep on improving, and keep beating arthritis!";
      else if(score >= average) {
         
         toReturn += "Congratulations! Your score of " + score + " is " + (int)(100*((score - average)/average))+ "% above your average score! You have been really been improving. Keep fighting your arthritis and keep playing! You're on the way to a more comfortable life.";
      }
      else {
         toReturn += "Good work! Unfortunately you were below your average score, but everyone has off days. Keep playing to fight off the arthritis. We believe in you!";
      }
      postScore(username, score, hand);
      return toReturn;
   }
}