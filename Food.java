
import java.awt.*;
           
/**
 * A basic class for the food for which the snake can eat 
 *  
 */  
public class Food extends GameObj {
    
	public final static int SIZE = 13;  
	   
      
	 /**
	 * Note that, because we don't need to do anything special when constructing
	 * a food, we simply use the superclass constructor called with the
	 * correct parameters
	 */ 
	public Food() {
		super((int) (2 + Math.random() * 285), (int) (2 + Math.random() * 285), 0, 0, SIZE, SIZE);
	}
	public Food(int init_x, int init_y, int init_vel_x, int init_vel_y) {
		super(init_x, init_y, init_vel_x, init_vel_y, SIZE, SIZE);
	}  

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(pos_x, pos_y, width, height);
	}

}