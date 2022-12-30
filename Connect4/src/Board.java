import java.security.spec.RSAOtherPrimeInfo;

public class Board {

    private final int NUM_OF_COLUMNS = 7;
    private final int NUM_OF_ROW = 6;
    private final char[][] board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
    private int row,col = 0;
    public char symbol;


    public Board() {
        //TODO

        for(int row = 0; row < NUM_OF_ROW; row++){
            for(int col = 0; col < NUM_OF_COLUMNS; col++){
//                if(row == 5) this.board[row][col] = '_';
                this.board[row][col] = ' ';
            }
        }


    }

    private boolean squareOccupied(char ch){
        // If there is a symbol then returns true

        if(ch == ' '){
            return false;
        }else{
            return true;
        }

    }

    public boolean updateBoard(int col, char ch){

        if(squareOccupied(this.board[0][col])){
            return false;
        }


        for(int row = NUM_OF_ROW - 1; row >= 0; row--){
            if(!squareOccupied(this.board[row][col])){
                this.row = row;
                this.col = col;
                this.board[row][col] = ch;
                this.symbol = ch;

                break;
            }
        }


        return true;

    }

    public void printBoard() {
        //TODO

        for(int row = 0; row < NUM_OF_ROW; row++){
            for(int col = 0; col < NUM_OF_COLUMNS; col++){
                if (col == 6) System.out.print("|" + this.board[row][col] + "|");
                else System.out.print("|" + this.board[row][col]);
            }
            System.out.println();
        }

    }

    // Row check


    private boolean row(int row, char ch){

        int count = 0;

        for(int i = 0; i < NUM_OF_COLUMNS; i++){

            if(this.board[row][i] != ch){
                count = 0;
            }else if (this.board[row][i] == ch){
                count++;
            }

            if(count >= 4){
                return true;
            }

        }

        return false;
    }


    // Column check
    private boolean col(int col, char ch){

        int count = 0;

        for(int i = NUM_OF_ROW-1; i >= 0; i--){

            if(this.board[i][col] != ch){
                count = 0;
            }else if(this.board[i][col] == ch){
                count++;

            }
            if(count >= 4){
                return true;
            }
        }
        return false;
    }

    // Diagonal

    private boolean dia(int setRow, int setCol, char ch){


        int count  = 0;

        int row = setRow;
        int col = setCol;



        // Top left to Bottom right

        if(row > 0 && col > 0 && this.board[row-1][col-1] == ch){
            count = 1;
        }

        while (row < NUM_OF_ROW && col < NUM_OF_COLUMNS) {
            if (this.board[row][col] != ch) {
                count = 0;
            }else if (this.board[row][col] == ch) {
                count++;
            }


            if (count >= 4) {
                return true;
            }
            row++;
            col++;
        }


        row = setRow;
        col = setCol;
        count = 0;

        if(row < NUM_OF_ROW - 1 && col < NUM_OF_COLUMNS - 1 && this.board[row+1][col+1] == ch){
            count = 1;
        }

        // Bottom right to top left
        while (row >= 0 && col >= 0) {

            if (this.board[row][col] != ch) {
                count = 0;
            }else if (this.board[row][col] == ch) {
                count++;
            }

            if (count >= 4) {
                return true;
            }

            row--;
            col--;
        }


        // Top right to bottom left
        row = setRow;
        col = setCol;
        count = 0;

        if(row > 0 && col < NUM_OF_COLUMNS - 1 && this.board[row-1][col+1] == ch){
            count = 1;
        }


        while (row < NUM_OF_ROW  && col >= 0) {

            if (this.board[row][col] != ch) {
                count = 0;
            }else if (this.board[row][col] == ch) {
                count++;
            }

            if (count >= 4) {
                return true;
            }

            row++;
            col--;
        }


        count = 0;
        row = setRow;
        col = setCol;


        if(row < NUM_OF_ROW - 1  && col > 0 && this.board[row+1][col-1] == ch){
            count = 1;
        }

        // Bottom left to top right
        while (row >= 0 && col < NUM_OF_COLUMNS ) {

            if(this.board[row][col] != ch) {
                count = 0;
            }else if (this.board[row][col] == ch) {
                count++;
            }

            if (count >= 4) {
                return true;
            }

            row--;
            col++;
        }

        return false;
    }


    public boolean containsWin() {

        return row(this.row, this.symbol) || col(this.col, this.symbol)|| dia(this.row, this.col, this.symbol);

    }

    public boolean isTie() {
        //TODO

        for(int i = 0; i < NUM_OF_COLUMNS; i++){
            if(!squareOccupied(this.board[0][i])){
                return false;
            }
        }


        return true;
    }

    private char lastSymbol(char symbol){
        for(int i = 0; i < NUM_OF_ROW; i++){
            for(int j = 0 ; j < NUM_OF_COLUMNS; j++) {
                if(this.board[i][j] != ' ' && this.board[i][j] != symbol){
                    return this.board[i][j];
                }
            }
        }

        return symbol;
    }





    public int AIWinCheck(char ch){

         //Row

        for(int i = 0 ; i < NUM_OF_ROW; i++){
            for(int j = 0; j < NUM_OF_COLUMNS; j++){
                if(this.board[i][j] == ' '){
                    this.board[i][j] = ch;
                    if(row(i, ch) || col(j, ch) || dia(i,j,ch)){
                        if(i < NUM_OF_ROW - 1 && board[i+1][j] != ' '){
                            board[i][j] = ' ';
                            return j;
                        }else if (i == NUM_OF_ROW - 1){
                            board[i][j] = ' ';
                            return j;
                        }else{
                            board[i][j] = ' ';
                        }
                    }else{
                        this.board[i][j]= ' ';
                    }
                }
            }
        }
        return -1;

    }

    public int AIBlockCheck(char ch){
        char lastSymbol = lastSymbol(ch);
        return AIWinCheck(lastSymbol);
    }


    public void reset() {

        for(int i = 0; i < NUM_OF_ROW; i++){
            for(int j = 0; j < NUM_OF_COLUMNS; j++){
                this.board[i][j] = ' ';
            }
        }
    }

}