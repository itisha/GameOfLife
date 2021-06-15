package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GameOfLife
{
    private static final String DEAD = "";
    private static final String ALIVE = "X";
    private static final int[][] neighbours = new int[8][2];

    static {
        neighbours[0] = new int[] { -1, -1 };//top left
        neighbours[1] = new int[] { -1, 0 };//top
        neighbours[2] = new int[] { -1, +1 };//top right

        neighbours[3] = new int[] { 0, -1 };//left
        neighbours[4] = new int[] { 0, +1 };//right

        neighbours[5] = new int[] { +1, -1 };//bottom left
        neighbours[6] = new int[] { +1, 0 };//bottom
        neighbours[7] = new int[] { +1, +1 };//bottom right
    }

    private final String[][] currentGen;
    private final int height;
    private final int width;
    private final int generation;

    public GameOfLife( String[][] canvas )
    {
        this.currentGen = canvas;
        this.height = canvas.length;
        this.width = canvas[0].length;
        this.generation = 0;
    }

    private GameOfLife( String[][] canvas, int generation )
    {
        this.currentGen = canvas;
        this.height = canvas.length;
        this.width = canvas[0].length;
        this.generation = generation;
    }

    public void go()
    {
        final String[][] nextGen = new String[width][height];

        for( int i = 0; i < currentGen.length; i++ )
        {
            for( int j = 0; j < currentGen[i].length; j++ )
            {
                String currentCell = currentGen[i][j];
                int numberOfNeighbors = getNumberOfNeighbors( currentGen, i, j );

                String nextgenCell;
                if( isAlive( currentCell ) )
                {
                    nextgenCell = getNextStateForAliveCell( numberOfNeighbors );
                }
                else
                {
                    nextgenCell = getNextStateForDeadCell( numberOfNeighbors );
                }

                nextGen[i][j] = nextgenCell;
            }
        }

        print( nextGen );

//        Scanner scan = new Scanner(System.in);
//        System.out.print("Press Enter to continue . . . ");
//        scan.nextLine();

        new GameOfLife( nextGen, generation + 1 ).go();
    }

    private void print( String[][] nextGen )
    {
        System.out.println( "Generation : " + generation );
        for( int i = 0; i < height; i++ )
        {
            System.out.println( Arrays.toString( nextGen[i] ) );
        }
    }

    private int getNumberOfNeighbors( String[][] currentGen, int i, int j )
    {
        int count = 0;
        for( int[] neighbour : neighbours )
        {
            String neighbourCell = pokeNeighbour( currentGen, neighbour[0] + i, neighbour[1] + j );
            if( isAlive( neighbourCell ) )
            {
                count++;
            }
        }
        return count;
    }

    private String pokeNeighbour( String[][] currentGen, int i, int j )
    {
        if( i < 0 || i >= currentGen.length )
        {
            return DEAD;
        }
        if( j < 0 || j >= currentGen[i].length )
        {
            return DEAD;
        }
        return currentGen[i][j];
    }

    private boolean isAlive( String currentCell )
    {
        if( currentCell == null )
        {
            return false;
        }
        else
        {
            return !currentCell.isBlank();
        }
    }

    private String getNextStateForAliveCell( int numberOfNeighbors )
    {
        if( numberOfNeighbors == 2 || numberOfNeighbors == 3 )
        {
            return ALIVE;
        }
        else
        {
            return DEAD;
        }
    }

    private String getNextStateForDeadCell( int numberOfNeighbors )
    {
        if( numberOfNeighbors == 3 )
        {
            return ALIVE;
        }
        else
        {
            return DEAD;
        }
    }

}
