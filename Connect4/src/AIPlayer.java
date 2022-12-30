import java.util.Random;


public class AIPlayer extends Player{


    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    @Override
    public void makeMove(Board board) {

    int col = board.AIBlockCheck(this.symbol);
//    System.out.println(col);
    if(col != -1){
        board.updateBoard(col, this.symbol);
    }else{
        col = board.AIWinCheck(this.symbol);
//        System.out.println(col);
        if(col == -1){
            Random rn = new Random();
            col = rn.nextInt(7);
            while(!board.updateBoard(col,this.symbol)){
                System.out.println("Column is full");
                col = rn.nextInt(7);
            }
        }else{
            board.updateBoard(col, this.symbol);
        }
    }





    }
}
