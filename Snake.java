
import java.awt.*;
import java.util.LinkedList;

/**
 * A basic class as for the snake and its separate body parts
 * 
 */

public class Snake { 
 public static int SIZE = 0;
 public static int COURT_WIDTH = 300;    
 public static int COURT_HEIGHT = 300;
 public boolean goingUp = false;
 public boolean goingDown = false;
 public boolean goingLeft = false;
 public boolean goingRight = true;
 public LinkedList<BodyPart> bodyParts;
  
 // constructor for snake
 public Snake() {
  bodyParts = new LinkedList<BodyPart>();
  BodyPart body = new BodyPart(150, 150, 0, 0);
  bodyParts.add(body);
  SIZE++;
 }          

 // snake will eat the food and get bigger
 public void grow() {
  BodyPart last = bodyParts.getLast();
  if (goingUp) {
   BodyPart newBodyPart = new BodyPart(last.pos_x, last.pos_y + BodyPart.SIZE, last.v_x, last.v_y);
   bodyParts.add(newBodyPart);
   
  } else if (goingDown) {
   BodyPart newBodyPart = new BodyPart(last.pos_x, last.pos_y - BodyPart.SIZE, last.v_x, last.v_y);
   bodyParts.add(newBodyPart);
   
  } else if (goingLeft) {
   BodyPart newBodyPart = new BodyPart(last.pos_x + BodyPart.SIZE, last.pos_y, last.v_x, last.v_y);
   bodyParts.add(newBodyPart);
   
  } else if (goingRight) {
   BodyPart newBodyPart = new BodyPart(last.pos_x - BodyPart.SIZE, last.pos_y, last.v_x, last.v_y);
   bodyParts.add(newBodyPart);
  }
  SIZE++; 
 }   

 // get the head of the snake
 public BodyPart getSnakeHead() {
  return bodyParts.getFirst();
 }

 // get the tail of the snake
 public BodyPart getSnakeTail() {
  return bodyParts.getLast();
 } 
       
 // get the linkedList of body parts
 public LinkedList<BodyPart> getBodyParts() {
  return bodyParts;
 }
         
 // moves the snake in a specific direction (but recursively does it)
 public void moveSnake(int snakeSize) {
 
  if (snakeSize == 0) {
   bodyParts.getFirst().move();
  } else {
   bodyParts.get(snakeSize).pos_x = bodyParts.get(snakeSize - 1).pos_x;
   bodyParts.get(snakeSize).pos_y = bodyParts.get(snakeSize - 1).pos_y;
   moveSnake(snakeSize - 1);
  }
 }
   
 /*
  * check if the snake head hits either one of the four walls while moving
  */
 public boolean hitsWall() {
  return (bodyParts.getFirst().pos_x >= bodyParts.getFirst().max_x || bodyParts.getFirst().pos_x <= 0
    || bodyParts.getFirst().pos_y >= bodyParts.getFirst().max_y  || bodyParts.getFirst().pos_y <= 0);
 } 

 // check if the snake head hits its own body
 public boolean hitsSelf() {
  BodyPart b = bodyParts.getFirst();
  for (int i = 1; i < bodyParts.size(); i++) {
   if (b.intersects(bodyParts.get(i))) {
    return true;
   }

  }
  return false;
 }

}
