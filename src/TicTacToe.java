import java.util.Scanner;

public class TicTacToe {
    //TODO: Code this uwu
    //Could steal from https://junilearning.com/blog/coding-projects/java-beginner-tic-tac-toe-tutorial/

    private Boolean[][] board;
    private Boolean lastPlayed;

    public TicTacToe(int gridSize){
        board = new Boolean[gridSize][gridSize];
    }

    public void play(int x, int y, Boolean xOrO){
        //Checks to make sure that the play is inside the board
        if(x > board.length - 1 || y > board[0].length - 1){
            throw new IllegalArgumentException("Coordinate is outside of the board");
        }

        //Check to make sure you are playing in the right order
        if(xOrO.equals(lastPlayed)){
            throw new IllegalArgumentException("Cannot play 2 of the same symbol in a row");
        }

        //Check to make sure that there isn't something already in that spot
        if(board[x][y] != null){
            throw new IllegalArgumentException("There is already a symbol in this spot");
        }


        //All checks are passed, we can make this move
        board[x][y] = xOrO;

        if(winner(board) != null){

            //Declare the winner

            if(winner(board)){
                System.out.println("X wins");
            }else{
                System.out.println("O wins");
            }

            //Display the board!
            display();

            //Clear the board!
            board = new Boolean[board.length][board.length];
        }
    }

    static Boolean winner(Boolean[][] board){
        if(horizontalWinner(board) != null){
            return horizontalWinner(board);
        }
        if(verticalWinner(board) != null){
            return verticalWinner(board);
        }
        if(diagonalWinner(board) != null){
            return diagonalWinner(board);
        }
        return null;
    }

    static Boolean horizontalWinner(Boolean[][] board) {
        //Check horizontals:
        //checks if 3 of the same non-null value are in a row horizontally, for every row

        for(Boolean[] row : board){         //Go through every single row of the board.
                                            //Notice the use of an "enhanced for loop"
                                            //We use the enhanced for loop because we don't
                                            //Care about the index

            boolean rowAllEqual = true;     //We use a flag to see if the whole row is all equal

            if(row[0] != null){             //Start by checking for null. If we don't,
                                            //we get a NullPointerException by calling a method
                                            //on a null object (.equals()).

                for(int i = 1; i < row.length; i++){        //We iterate through the whole row
                                                            //Here we use an indexed for loop
                                                            //becasue we need to use the index
                                                            //to access adjacent items

                    if (!row[i - 1].equals(row[i])) {       //We compare each element to the previous
                                                            //to see if they're equal. If they aren't we dip.
                        rowAllEqual = false;
                        break;
                    }
                }
                if(rowAllEqual){
                    return row[0];                          //If all the row was equal and not null,
                                                            //we declare the winner
                }
            }
        }
        return null;                        //In case no one wins, we send back a null
    }

    /**
     * @return true, false, or null depending on what's what
     */
    static Boolean verticalWinner(Boolean[][] board) {
        //THIS METHOD CRASHES ON NON-MATRIX BOARDS
        //Check verticals:
        //Checks if 3 of the same non-null value are in a row vertically, for every row.
        for(int i = 0; i < board[0].length; i++){          //Loop through the rows
            boolean columnAllEqual = true;
            Boolean columnFirst = board[0][i];

            for(int j = 1; j < board.length; j++){
                if(board[j][i] != columnFirst){
                    columnAllEqual = false;
                }
            }

            if(columnAllEqual){
                return columnFirst;
            }
        }

        return null;

    }

    /**
     *
     */
    static Boolean diagonalWinner(Boolean[][] board) {
        Boolean firstCorner = board[0][0];

        boolean hasWonFirstDiagonal = true;
        if(firstCorner != null) {
            for (int i = 0; i < board.length; i++) {
                if (!firstCorner.equals(board[i][i])) {
                    hasWonFirstDiagonal = false;
                    break;
                }
            }
            if(hasWonFirstDiagonal){
                return firstCorner;
            }
        }

        boolean hasWonSecondDiagonal = true;
        if(firstCorner != null) {
            for (int i = board.length - 1; i >= 0; i--) {
                if (!firstCorner.equals(board[i][i])) {
                    hasWonSecondDiagonal = false;
                    break;
                }
            }
            if(hasWonSecondDiagonal){
                return firstCorner;
            }
        }

        return null;
    }

    public void display(){
        for(Boolean[] row : board){
            for(Boolean item : row){
                if(item == null){
                    System.out.print("-");
                }else if(item){
                    System.out.print("x");
                }else{
                    System.out.print("o");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How big of a board do you want?");
        int boardSize = scanner.nextInt();
        TicTacToe game = new TicTacToe(boardSize);
        boolean xTurn = false;
        while(true){
            String player = "O";
            Boolean playerBool = false;
            if(xTurn){
                player = "X";
                playerBool = true;
            }
            System.out.println(player+"'s turn!");
            game.display();
            System.out.println("Specify X coordinate");
            int xCoord = scanner.nextInt();
            System.out.println("Specify Y coordinate");
            int yCoord = scanner.nextInt();
            game.play(xCoord,yCoord,playerBool);
            xTurn = !xTurn;
        }
    }
}
