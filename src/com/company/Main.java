package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main
{

    public static void main( String[] args )
    {
        try
        {
            int gridSize = 25;

            List<String> lines = Files.readAllLines( Path.of( "first-gen.txt" ) );

            String[][] canvas = new String[gridSize][gridSize];

            for( int i = 0; i < gridSize; i++ )
            {
                String[] line = lines.get( i ).split( "" );
                for( int j = 0; j < gridSize; j++ )
                {
                    if( j < line.length )
                    {
                        canvas[i][j] = line[j];
                    }
                    else
                    {
                        canvas[i][j] = "";
                    }
                }
            }

            new GameOfLife( canvas ).go();

        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}
