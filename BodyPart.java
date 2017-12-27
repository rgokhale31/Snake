import java.awt.*;
         
/**
 * A basic class for the body parts of a snake 
 *   
 */                      
public class BodyPart extends GameObj {
         
 //FIELDS
 public static final int SIZE = 13; 

 //constructor      
 public BodyPart(int init_x, int init_y, int init_vel_x, int init_vel_y) {
  super(init_x, init_y, init_vel_x, init_vel_y, SIZE, SIZE); 
  }
  
    //draw the body part 
 @Override
 public void draw(Graphics g) {
  g.setColor(Color.BLACK);
  g.drawRect(pos_x, pos_y, width, height);
  g.setColor(Color.BLUE); 
  g.fillRect(pos_x, pos_y, width, height);
 }
 }

