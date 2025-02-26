package game;
 
import java.util.Scanner;
 
public class ConsoleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameBoard gameBoard = new GameBoard();
 
        System.out.println("Welcome to Dragon Dash!");
        System.out.println("Press 'w' to flap and avoid obstacles.");
        System.out.println("Press 'f' to throw fireballs to destroy enemy dragons.");
        System.out.println("Press 'q' to quit the game.");
 
        while (!gameBoard.isGameOver()) {
            gameBoard.render();
            char input = scanner.next().charAt(0);
            if (input == 'w') {
                gameBoard.playerDragonFlap();
            } else if (input == 'f') {
                gameBoard.playerDragonThrowFireball();
            } else if (input == 'q') {
                System.out.println("Game over! Your final score is: " + gameBoard.getScore());
                break;
            }
            gameBoard.update();
        }
 
        scanner.close();
    }
}