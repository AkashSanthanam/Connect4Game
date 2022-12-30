public class Runner {
    public static void main(String[] args) {

        Board board = new Board();
        ConnectFour game = new ConnectFour(board);
        game.setPlayer1(new HumanPlayer('G',board,"Alice"));
        game.setPlayer2(new AIPlayer('P', board,"Bob"));
        game.playGame();
    }
}