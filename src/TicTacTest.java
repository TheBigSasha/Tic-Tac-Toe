public class TicTacTest {
    private static final Boolean[][] winnerStartHor = new Boolean[][]{
            {true, true, true},
            {null, false, null},
            {false, false, null}};

    private static final Boolean[][] winnerMiddleHor = new Boolean[][]{
            {null, false, null},
            {true, true, true},
            {false, false, null}};

    private static final Boolean[][] winnerEndHor = new Boolean[][]{
            {null, false, null},
            {false, false, null},
            {true, true, true}};

    private static final Boolean[][] noWin = new Boolean[][]{
            {null, false, null},
            {true, null, true},
            {false, false, null}};

    private static final Boolean[][] allNull = new Boolean[][]{
            {null, null, null},
            {null, null, null},
            {null, null, null}};


    public static void testHorizontalWins(){
        if(TicTacToe.horizontalWinner(winnerStartHor) == null){
            System.out.println("Failed to declare a winner when true won on the first line");
        }

        if(TicTacToe.horizontalWinner(winnerMiddleHor) == null){
            System.out.println("Failed to declare a winner when true won on the middle line");
        }

        if(TicTacToe.horizontalWinner(winnerEndHor) == null){
            System.out.println("Failed to declare a winner when true won on the last line");
        }

        if(TicTacToe.horizontalWinner(noWin) != null){
            System.out.println("Declared a horizontal winner when there should be none!");
        }

        if(TicTacToe.horizontalWinner(allNull) != null){
            System.out.println("Winner declared on empty board");
        }
    }

    private static final Boolean[][] winnerFirstDiagonal = new Boolean[][]{
            {true, false, null},
            {false, true, false},
            {null, null, true}};


    private static final Boolean[][] winnerSecondDiagonal = new Boolean[][]{
            {true, false, null},
            {false, true, false},
            {null, null, true}};



    public static void testDiagonalWins(){
        if(TicTacToe.diagonalWinner(winnerFirstDiagonal) == null){
            System.out.println("Failed to declare a winner when true won on the first diagonal");
        }

        if(TicTacToe.diagonalWinner(winnerSecondDiagonal) == null){
            System.out.println("Failed to declare a winner when true won on the second diagonal");
        }

        if(TicTacToe.diagonalWinner(noWin) != null){
            System.out.println("Declared a diagonal winner when there should be none!");
        }

        if(TicTacToe.diagonalWinner(allNull) != null){
            System.out.println("Winner declared on empty board");
        }
    }

    private static final Boolean[][] winnerStartVer = new Boolean[][]{
            {true, false, false},
            {true, null, null},
            {true, null, false}};

    private static final Boolean[][] winnerMiddleVer = new Boolean[][]{
            {null, false, null},
            {true, false, true},
            {true, false,null}};

    private static final Boolean[][] winnerEndVer = new Boolean[][]{
            {null, false, true},
            {false, false, true},
            {null, null, true}};


    public static void testVerticalWins(){
        if(TicTacToe.verticalWinner(winnerStartVer) == null){
            System.out.println("Failed to declare a winner when true won on the first line");
        }

        if(TicTacToe.verticalWinner(winnerMiddleVer) == null){
            System.out.println("Failed to declare a winner when true won on the middle line");
        }

        if(TicTacToe.verticalWinner(winnerEndVer) == null){
            System.out.println("Failed to declare a winner when true won on the last line");
        }

        if(TicTacToe.verticalWinner(noWin) != null){
            System.out.println("Declared a horizontal winner when there should be none!");
        }

        if(TicTacToe.verticalWinner(allNull) != null){
            System.out.println("Winner declared on empty board");
        }
    }

    public static void main(String[] args){
        testDiagonalWins();
        testHorizontalWins();
        testVerticalWins();
    }
}
