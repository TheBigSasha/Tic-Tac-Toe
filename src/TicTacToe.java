import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    //TODO: Code this uwu
    //Could steal from https://junilearning.com/blog/coding-projects/java-beginner-tic-tac-toe-tutorial/

    private Boolean[][] board;
    private Boolean lastPlayed;

    public TicTacToe(int gridSize){
        board = new Boolean[gridSize][gridSize];
    }

    public void play(int x,int y,  Boolean xOrO){
        //Checks to make sure that the play is inside the board
        if(y > board.length - 1 || x > board[0].length - 1){
            throw new IllegalArgumentException("Coordinate is outside of the board");
        }

        //Check to make sure you are playing in the right order
        if(xOrO.equals(lastPlayed)){
            throw new IllegalArgumentException("Cannot play 2 of the same symbol in a row");
        }

        //Check to make sure that there isn't something already in that spot
        if(board[y][x] != null){
            throw new IllegalArgumentException("There is already a symbol in this spot");
        }


        //All checks are passed, we can make this move
        board[y][x] = xOrO;

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

    public static Boolean diagonalWinner(Boolean[][] board){
        Boolean first = board[0][0];
        Boolean second = board[0][board.length - 1];
        boolean checkFirst = true;
        boolean checkSecond = true;

        if(first == null){
            checkFirst = false;
        }

        if(second == null){
            checkSecond = false;
        }

        for(int i = 0; i < board.length; i++){
            if(checkFirst){
                //i,i
                //0,0
                //1,1
                //2,2
                //...
                //length - 1, length - 1
                if(!first.equals(board[i][i])){
                    //Not null before .equals, maybe null after .equals
                    checkFirst = false;
                }
            }
            if(checkSecond){
                //i, length - 1 - i
                //0, length,
                //1, length -1
                //...
                //length, 0
                if(!second.equals(board[i][board.length -1 - i])){
                    //Not null before .equals, maybe null after .equals
                    checkFirst = false;
                }
            }
        }

        if(checkFirst){
            return first;
        }

        if(checkSecond){
            return  second;
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
        System.out.println("How many players? (1 or 2)");
        int players = scanner.nextInt();
        if(players > 1){
            twoPlayer(game);
        }else{
            csStudent(game);
        }
    }

    public static void twoPlayer(TicTacToe game){
        Scanner scanner = new Scanner(System.in);
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

    /**
     * A game mode for the loneliest of the lonely
     */
    public static void csStudent(TicTacToe game){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        boolean xTurn = false;
        while(true){
            String player = "O";
            Boolean playerBool = false;
            if(xTurn){
                player = "X";
                playerBool = true;
                System.out.println(player + "'s turn!");
                game.display();

                boolean hasPlayed = false;
                while(!hasPlayed){
                    int xCoord = rand.nextInt(game.board.length);
                    int yCoord = rand.nextInt(game.board.length);
                    try {
                        game.play(xCoord, yCoord, playerBool);
                        hasPlayed = true;
                    }catch(Exception ex){
                        hasPlayed = false;
                    }
                }
            }else {
                System.out.println(player + "'s turn!");
                game.display();
                System.out.println("Specify X coordinate");
                int xCoord = scanner.nextInt();
                System.out.println("Specify Y coordinate");
                int yCoord = scanner.nextInt();
                game.play(xCoord, yCoord, playerBool);
            }
            xTurn = !xTurn;

        }
    }
}
