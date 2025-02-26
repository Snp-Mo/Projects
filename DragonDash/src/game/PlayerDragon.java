package game;
 
public class PlayerDragon {
    private int position;
    private static final int FLAP_STRENGTH = 2;
    private static final int GRAVITY = 1;
 
    public PlayerDragon() {
        position = GameBoard.HEIGHT / 2;
    }
 
    public int getPosition() {
        return position;
    }
 
    public void flap() {
        position -= FLAP_STRENGTH;
        if (position < 0) {
            position = 0;
        }
    }
 
    public void applyGravity() {
        position += GRAVITY;
        if (position >= GameBoard.HEIGHT) {
            position = GameBoard.HEIGHT - 1;
        }
    }
}