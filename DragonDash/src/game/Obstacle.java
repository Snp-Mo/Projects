package game;
 
public class Obstacle {
    private int position;
 
    public Obstacle() {
        position = GameBoard.WIDTH - 1;
    }
 
    public int getPosition() {
        return position;
    }
 
    public void move() {
        position--;
    }
}