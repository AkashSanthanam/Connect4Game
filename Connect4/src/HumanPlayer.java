import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    @Override
    public void makeMove(Board board) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(this.name + ", please input your move:");
        int x;
        boolean check = false;

        while(true){

            if (check){
                System.out.println("Out of bounds! Please enter a number between 1-7");
                board.printBoard();
            }

            x = scanner.nextInt();


            if(x >= 1 && x <= 7 ) break;
            else check = true;

        }

        while(!board.updateBoard(x-1,this.symbol)){
            System.out.println("Column is full. Please enter a different number!");
            board.printBoard();
            x = scanner.nextInt();
        }


    }
}
