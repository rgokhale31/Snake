

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
 
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
 public void run() {

  // Top-level frame in which game components live
 
  final JFrame frame = new JFrame("TOP LEVEL FRAME");
  frame.setLocation(300, 300);
 
  // instructions
  final JPanel instructions_panel = new JPanel();
  JTextArea textArea = new JTextArea("Instructions:  " + "\n"
    + "Hello User! Welcome to Snake. In this game, you will have to control a snake "
    + "using the arrow keys to eat the food randomly scattered on the board while staying alive. "
    + "The snake (blue square) will constantly be moving in one direction, and so you "
    + "should use the arrow keys to change the direction the snake moves in. "
    + "The goal is to move the snake toward the food (red squares)  which will make the snake grow. "
    + "However, if the snake hits itself while turning or it hits one of the wall,"
    + "the snake dies and the game ends." + "\nControls:"
    + "\n-Left, right, Down, Up arrow keys to control direction"
    + "\n-If you are moving left and you try going right, you die(and vice versa). Same thing goes for "
    + "moving up and down." + "\n-Reset button to restart the game " + "\n" + "\nHave fun!");
  textArea.setFont(new Font("Serif", Font.ITALIC, 12));
  textArea.setLineWrap(true);
  textArea.setWrapStyleWord(true);
  instructions_panel.add(textArea);
  frame.add(instructions_panel, BorderLayout.EAST);
  
  // Status panel
  final JPanel status_panel = new JPanel();
  frame.add(status_panel, BorderLayout.SOUTH);
  final JLabel status = new JLabel("Running...");
  status_panel.add(status);
   
  // Main playing area
  final GameCourt court = new GameCourt(status);
  frame.add(court, BorderLayout.CENTER);

  // store the users score in an archive
  FileReader originalReader = null;
  try {
   originalReader = new FileReader("ArchiveScores.txt");
  } catch (FileNotFoundException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();  
  } 
  try {
   JTextArea text = new JTextArea();
   BufferedReader reader = new BufferedReader(originalReader);
   String newLine = reader.readLine();
   while (newLine != null){
     text.append("\n" + newLine);       
     newLine = reader.readLine(); 
   }      
     
  frame.add(text, BorderLayout.WEST);
  } catch (IOException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
  // Reset button
  final JPanel control_panel = new JPanel();
  frame.add(control_panel, BorderLayout.NORTH);

  
  final JButton reset = new JButton("Reset");
  reset.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    court.reset();

   }
  });
  control_panel.add(reset);
 
  
  // Put the frame on the screen
  frame.pack();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);

  // Start game
  court.reset();
  
 }

 /*
  * Main method run to start and run the game Initializes the GUI elements
  * specified in Game and runs it 
  */
 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Game());
 }
 
}
