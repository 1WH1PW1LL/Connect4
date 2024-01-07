import java.util.*;
public class Connect4 {
    public static void main(String[] args) {
        // Visual and Break.
        Scanner keyboard = new Scanner(System.in);

        String[][] board = new String[6][7]; // Array [6][7] 6 rows and 7 columns
        Initialize(board);

        System.out.println(" The first player plays red and the second player plays blu");

        boolean firstPlayer = true;
        boolean secondPlayer = false;
        String playerName = "FirstPlayer (red)"; //red
        while (true) {
            System.out.println(playerName + " Please enter a number between 0 and 6 (inclusive) representing the zero based index of the column that you want to play");
            int columnNumber = keyboard.nextInt();
            if(columnNumber>6){
                System.out.println("A number between 0 and 6 (inclusive). Please try again...");
                continue;
            }

            String color = "";
            if (firstPlayer) color = "red";
            else if (secondPlayer) color = "blu";

            int row;
            for (row = 5; row >= 0; row--) {
                if (board[row][columnNumber].equals("")) {
                    continue;
                }else{
                    break;
                }
            }
            if(row == 5){
                System.out.println(playerName + "This column is full. Please choose another column");
                continue;
            }else{
                row = row+1;
                board[row][columnNumber] = color;
            }

            if (Winner(board, color,  row, columnNumber) == true) {
                System.out.println("We have a winner. " + playerName + " has won the game");
                break;
            }

            if (firstPlayer) {
                playerName = "SecondPlayer (blu)";//blu
                firstPlayer = false;
                secondPlayer = true;
            } else if (secondPlayer) {
                playerName = "FirstPlayer (red)";//red
                firstPlayer = true;
                secondPlayer = false;
            }

            PrintTheBoard(board);

            if (AllColumnsAreFull(board) == true) {
                System.out.println("There are no winners. Please play again");
                break;
            }

        }
    }
    public static boolean Winner(String[][] board, String color, int row, int column){
        //check horizontal left and right. On the same row go to left column and then right column
        int leftColumn=0;
        if(column >= 1)
            leftColumn = column-1;
        while (leftColumn >=0){
            if(board[row][leftColumn].equals(column)){
                leftColumn--;
            }else{
                break;
            }
        }
        if(leftColumn < 0) leftColumn++;
        int rightColumn=6;
        if(column <= 5)
            rightColumn = column+1;
        while (rightColumn <= 5){
            if(board[row][rightColumn].equals(color)){
                rightColumn++;
            }else {
                break;
            }
        }
        if(rightColumn > 6) rightColumn = 6;
        if(rightColumn-leftColumn == 3){
            System.out.println("4 in a row for " + color);
            return true;
        }

        //check vertical down. on the same column go down the rows.
        int count = 1;
        int verticalRow = row-1;
        while (verticalRow >= 0) {
            if(board[verticalRow][column].equals(color)){
                verticalRow--;
                count++;
            }else{
                break;
            }
        }
        if(count == 4){
            System.out.println("4 in a column for " + color);
            return true;
        }
        //check left top diagonal right bottom diagonal
        count=1;
        //left top diagonal - increase row and decrease column.
        int diagonalRow = row+1;
        int diagonalColumn = column-1;
        while (diagonalRow <=5 && diagonalColumn >= 0){
            if(board[diagonalRow][diagonalColumn].equals(color)){
                count++;
                diagonalRow++;
                diagonalColumn--;
            }else{
                break;
            }
        }
        //right bottom diagonal - decrease row and increase column.
        diagonalRow = row-1;
        diagonalColumn = column+1;
        while (diagonalRow >=0 && diagonalColumn <=6 ){
            if(board[diagonalRow][diagonalColumn].equals(color)){
                count++;
                diagonalRow--;
                diagonalColumn++;
            }else{
                break;
            }
        }
        if(count == 4){
            System.out.println("4 in a diagonal leaning backward (top left o bottom right) for " + color);
            return true;
        }

        //check right top diagonal left bottom diagonal
        count=1;
        //right top diagonal - increase row and increase column.
        diagonalRow = row+1;
        diagonalColumn = column+1;
        while (diagonalRow <=5 && diagonalColumn <= 6){
            if(board[diagonalRow][diagonalColumn].equals(color)){
                count++;
                diagonalRow++;
                diagonalColumn++;
            }else{
                break;
            }
        }
        //left bottom diagonal - decrease row and decrease column.
        diagonalRow = row-1;
        diagonalColumn = column-1;
        while (diagonalRow >=0 && diagonalColumn >= 0 ){
            if(board[diagonalRow][diagonalColumn].equals(color)){
                count++;
                diagonalRow--;
                diagonalColumn--;
            }else{
                break;
            }
        }
        if(count == 4){
            System.out.println("4 in a diagonal leaning forward (top right to bottom left) for " + color);
            return true;
        }

        return false;
    }
    public static boolean AllColumnsAreFull(String[][] board){
        for (int col = 0; col <= 6; col++) {
            if(board[5][col].equals(""))
                return false;
        }
        return true;
    }

    public static void PrintTheBoard(String[][] board){
        System.out.println("--0----1-----2-----3-----4-----5-----6--");
        for(int row=5; row>=0; row--) {
            for (int col = 0; col <= 6; col++) {
                if (board[row][col].equals("")){
                    System.out.print("   ");
                    System.out.print(" | ");
                }else{
                    System.out.print(board[row][col]);
                    System.out.print(" | ");

                }
            }
            System.out.print(row);
            System.out.println();
            System.out.println("----------------------------------------");
        }
    }
    public static void Initialize(String[][] board){
        for(int row=5; row>=0; row--) {
            for (int col = 0; col <= 6; col++) {
                board[row][col]="";
            }
        }
    }
}
