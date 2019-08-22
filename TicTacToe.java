package games;

import java.io.IOException;
import java.util.Objects;


public class TicTacToe
{
    static char[][] Board = new char[3][3];
    static enum BoardStatus
    {
        NOT_STARTED, IN_PROGRESS, WON, DRAWN;
    };

    public TicTacToe()
    {
        initializeBoard();
    }

    private static void makeMove(int row, int col, char move)
    {
        if(!Objects.isNull(Board[row-1][col-1]))
            Board[row-1][col-1] = move;
    }
    
    //Check the status of the Board
    private static BoardStatus checkState(char move)
    {
        int checkCountForDraw = 0;
        
        //Check Rows
        for (int i = 0; i < 3; i++) 
        {
            //System.out.println(Board[i][0]==move && Board[i][1]==move && Board[i][2]==move);
            if(Board[i][0]==move && Board[i][1]==move && Board[i][2]==move)
            {
                return BoardStatus.WON;
            }
        }
        //Check Columns
        for (int i = 0; i < 3; i++) {
            if(Board[0][i]==move && Board[1][i]==move && Board[2][i]==move)
            {
                return BoardStatus.WON;
            }
        }

        //Check Diagnols
        //diagnol1
        if(Board[0][0]==move && Board[1][1]==move && Board[2][2]==move)
        {
            
            return BoardStatus.WON;
        }
        if(Board[0][2]==move && Board[1][1]==move && Board[2][0]==move)
        {
            
            return BoardStatus.WON;
        }

        //CheckForDraw
        for(int i=0;i<=2; i++)
        {
            for(int j=0; j<=2; j++)
            {
                if(Objects.isNull(Board[i][j]))
                {
                    System.out.print(checkCountForDraw++);
                }
            }
            
        }
        if(checkCountForDraw == 9)
        {
            return BoardStatus.DRAWN;
        }

        return BoardStatus.IN_PROGRESS;
    }
    //prints the board
    private static void printBoard()
    {
        for(int i=0;i<=2; i++)
        {
            for(int j=0; j<=2; j++)
            {
                System.out.printf("|%s", Board[i][j]);
            }
            System.out.println("|");
            System.out.println("-------");
        }
        
    }

    //initialize the board to empty strings
    private static void initializeBoard()
    {
        for(int i=0;i<=2; i++)
        {
            for(int j=0; j<=2; j++)
            {
                Board[i][j] = 0;
            }
        }

    }
    
    public static void main(String[] args) throws IOException
    {
        //TicTacToe game = new TicTacToe();
        BoardStatus gameStatus = BoardStatus.NOT_STARTED;
        boolean PlayerPlaying = true;
        char move;
        int irow; 
        int icol;
        String console_string;
        //Allow users to select characters X, O
        char player1Char, player2Char;

        initializeBoard();

        System.out.println("Player1 select a character to play with (X, O) \n :");
        console_string = System.console().readLine();
        player1Char = console_string.charAt(0);
        System.out.println("Player2 select a character to play with (X, O) \n :");
        console_string = System.console().readLine();
        player2Char = console_string.charAt(0);

        printBoard();
        //Loop
        while((gameStatus == BoardStatus.NOT_STARTED || gameStatus == BoardStatus.IN_PROGRESS))
        {
            if(PlayerPlaying)
            {
                System.out.print("Player 1 your Chance, please enter cell coordinates, starting with \nRow (1..3):");
                console_string = System.console().readLine();
                irow =  Character.getNumericValue(console_string.charAt(0));
                System.out.print("\nColumn (1..3):");
                console_string = System.console().readLine();
                icol =  Character.getNumericValue(console_string.charAt(0));
                
                makeMove(irow, icol, player1Char);
                PlayerPlaying = !PlayerPlaying;
                move = player1Char;
            }
            else
            {
                System.out.print("Player 2 your Chance, please enter cell coordinates, starting with \nRow (1..3):");
                console_string = System.console().readLine();
                irow =  Character.getNumericValue(console_string.charAt(0));
                System.out.print("\nColumn (1..3):");
                console_string = System.console().readLine();
                icol =  Character.getNumericValue(console_string.charAt(0));
                
                makeMove(irow, icol, player2Char);
                PlayerPlaying = !PlayerPlaying;
                move = player2Char;
            }
            gameStatus = checkState(move);
            System.out.println(gameStatus);
            printBoard();
            if(move == player1Char && gameStatus == BoardStatus.WON)
                System.out.printf("Player 1 has %s the game", gameStatus);
            else if(move == player2Char && gameStatus == BoardStatus.WON)
                System.out.printf("Player 2 has %s the game", gameStatus);
            else 
                System.out.printf("Game is %s", gameStatus);
            
                
        }
        if(gameStatus == BoardStatus.WON || gameStatus == BoardStatus.DRAWN)
        {    
            System.out.print("\nEnd of the Game");

        }

        System.console().readLine();
    }
}