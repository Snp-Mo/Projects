package game;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
public class GameBoard {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 10;
 
    private PlayerDragon playerDragon;
    private List<Obstacle> obstacles;
    private boolean gameOver;
    private int score;
    private Random random;
 
    public GameBoard() {
        playerDragon = new PlayerDragon();
        obstacles = new ArrayList<>();
        gameOver = false;
        score = 0;
        random = new Random();
    }
 
    public PlayerDragon getPlayerDragon() {
        return playerDragon;
    }
 
    public boolean isGameOver() {
        return gameOver;
    }
 
    public int getScore() {
        return score;
    }
 
    public void update() {
        playerDragon.applyGravity();
        moveObstacles();
        checkCollisions();
        score++;
        if (random.nextInt(10) < 2) { // Adjust obstacle generation rate
            obstacles.add(new Building());
            if (random.nextInt(3) == 0) {
                obstacles.add(new ObstacleDragon());
            }
        }
    }
 
    private void moveObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).move();
            if (obstacles.get(i).getPosition() < 0) {
                obstacles.remove(i);
                i--;
            }
        }
    }
 
    private void checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getPosition() == 1 && (obstacle instanceof Building || obstacle instanceof ObstacleDragon) && playerDragon.getPosition() >= HEIGHT - 2) {
                gameOver = true;
            }
        }
    }
 
    public void playerDragonFlap() {
        playerDragon.flap();
    }
 
    public void playerDragonThrowFireball() {
        obstacles.removeIf(obstacle -> obstacle instanceof ObstacleDragon && obstacle.getPosition() == 1);
    }
 
    public void render() {
        StringBuilder[] board = new StringBuilder[HEIGHT];
 
        // Initialize the board with empty spaces
        for (int i = 0; i < HEIGHT; i++) {
            board[i] = new StringBuilder(" ".repeat(WIDTH));
        }
 
        // Draw the player-controlled dragon
        board[playerDragon.getPosition()].replace(1, 2, "🐲"); // Unicode escape sequence for 🐲
 
        // Draw obstacles
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Building) {
                board[HEIGHT - 2].setCharAt(obstacle.getPosition(), '|');
                board[HEIGHT - 1].setCharAt(obstacle.getPosition(), '|');
            } else if (obstacle instanceof ObstacleDragon) {
                board[HEIGHT - 3].replace(obstacle.getPosition(), obstacle.getPosition() + 1, "🐲"); // Unicode character for dragon 🐲
            }
        }
 
        // Print the board
        for (int i = 0; i < HEIGHT; i++) {
            System.out.println(board[i]);
        }
 
        System.out.println("Score: " + score);
    }
}