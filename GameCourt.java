
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

 // the state of the game logic
 private Food food; // the food, which is randomly placed in the game
 private Snake snake; // the snake, which travels in the board
 public boolean playing = true; // whether the game is running
 private JLabel status; // Current status text (i.e. Running...)
 public int bestScore = 0; // highest scores in the game
 
 // Game constants
 public static final int COURT_WIDTH = 300;
 public static final int COURT_HEIGHT = 300;
 public static final int SNAKE_VELOCITY = BodyPart.SIZE + 1;
 // Update interval for timer, in milliseconds
 public static final int INTERVAL = 90;  
 public boolean gameEnds;
 
 public GameCourt(JLabel status) {
  // creates border around the court area, JComponent method
  setBorder(BorderFactory.createLineBorder(Color.BLACK));

  // The timer is an object which triggers an action periodically
  // with the given INTERVAL. One registers an ActionListener with
  // this timer, whose actionPerformed() method will be called
  // each time the timer triggers. 
  Timer timer = new Timer(INTERVAL, new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    tick();
   }
  });
  timer.start(); 
    
  // Enable keyboard focus on the court area.
  // When this component has the keyboard focus, key
  // events will be handled by its key listener.
  setFocusable(true);

  // This key listener allows the snake to move as long
  // as an arrow key is pressed, by changing the snake's
  // velocity accordingly. (The tick method below actually
  // moves the square.)  
  addKeyListener(new KeyAdapter() {
   public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
     snake.goingDown = false;
     snake.goingUp = false;
     snake.goingLeft = true;
     snake.goingRight = false;

     snake.getBodyParts().getFirst().v_x = -SNAKE_VELOCITY;
     snake.getBodyParts().getFirst().v_y = 0;
   
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
     snake.goingDown = false;
     snake.goingUp = false;
     snake.goingLeft = false;
     snake.goingRight = true;
  
     snake.getBodyParts().getFirst().v_x = SNAKE_VELOCITY;
     snake.getBodyParts().getFirst().v_y = 0;

    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
     snake.goingDown = true;
     snake.goingUp = false;
     snake.goingLeft = false;
     snake.goingRight = false;

     snake.getBodyParts().getFirst().v_x = 0;
     snake.getBodyParts().getFirst().v_y = SNAKE_VELOCITY;

    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
     snake.goingDown = false;
     snake.goingUp = true;
     snake.goingLeft = false;
     snake.goingRight = false;

     snake.getBodyParts().getFirst().v_x = 0;
     snake.getBodyParts().getFirst().v_y = -SNAKE_VELOCITY;

    }
   }
  });

  this.status = status;
 }
       
 /**
  * (Re-)set the game to its initial state.
  */
 public void reset() {
  snake = new Snake();
  food = new Food();
  playing = true;
  status.setText("Running...");
       
  // Make sure that this component has the keyboard focus
  requestFocusInWindow();

 } 

 /**
  * This method is called every time the timer defined in the constructor
  * triggers.
  */
 void tick() {
  if (playing) {
   // advance the snake in their
   // current direction.
      
   snake.moveSnake(snake.getBodyParts().size() - 1);
   repaint();
   //System.out.println("here " + snake.bodyParts.getFirst().pos_x);
   // check for the game end conditions
   // snake goes out of bounds or if snake hits itself
   if (snake.hitsSelf() || snake.hitsWall()) {
    playing = false;
   
    String userName = JOptionPane.showInputDialog("Enter your name:");
   
    FileReader scores = null;
    try {   
     scores = new FileReader("ArchiveScores.txt");
    } catch (FileNotFoundException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    } 
    FileWriter scoresWrite = null;
    try {
     scoresWrite = new FileWriter("ArchiveScores.txt", true);
    } catch (IOException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
    BufferedReader br = new BufferedReader(scores);
    BufferedWriter wr = new BufferedWriter(scoresWrite);
    try { 
     wr.write(userName + ": " + getScore());
     wr.newLine();  
    } catch (IOException e1) { 
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }  
    try {
     wr.write(getScore());
    } catch (IOException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
    try {
         
     wr.newLine();
     wr.flush();
     wr.close();
    } catch (IOException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }     
    
    
    

    status.setText("Game over. You're score is " + (snake.getBodyParts().size() - 1));
       }
   }

   // grows the snake if it hits the food
   if (snake.getSnakeHead().intersects(food)) {
    snake.grow();
    food = new Food();
   }
   // update the display
   repaint();
  }
  
    

 @Override
 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  for (BodyPart b : snake.getBodyParts()) {
   b.draw(g);
  }
  food.draw(g);
 }

 @Override
 public Dimension getPreferredSize() {
  return new Dimension(COURT_WIDTH, COURT_HEIGHT);
 } 
 
 public int getScore() { 
  return snake.getBodyParts().size();
 }
 
 public boolean getPlaying() {
  return playing; 
 }
 
 
  
    

}
