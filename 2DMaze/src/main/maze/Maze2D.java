package main.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Maze2D {

    private char [][] maze = null;
    
    private static int start_X = 0;
    private static int start_Y = 0;
    private int end_X = 0;
    private int end_Y = 0;
    private int height = 0;
    private int width = 0;


    /**
     * @param filename
     */
    public void fileReader(String filename) throws IOException {

        BufferedReader bufferReader = null;
        File file = null;
        char temp;
        String line = null;
        int count = 1;
        int heightCounter = 0;
        try {
            file = new File(filename);

           
            bufferReader = new BufferedReader(new FileReader(filename));
            while((line = bufferReader.readLine()) != null) {
                switch (count) {
                case (1):
                    width  = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                    height = Integer.parseInt((line.substring(line.indexOf(' ')+1)));
                    maze = new char[height][width];
                    break;
                case (2):
                    temp = line.charAt(0);
                    start_X = Character.getNumericValue(temp);
                    temp = line.charAt(2);
                    start_Y = Character.getNumericValue(temp);
                    break;
                case (3):
                    end_Y = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                    end_X = Integer.parseInt((line.substring(line.indexOf(' ') +1 )));
                    break;
                default:
                    int counter = 0;
                    for (int i = 0; i < line.length(); i++){
                        if(line.charAt(i) != ' '){
                            maze[heightCounter][counter] = line.charAt(i);
                            counter++;
                        }
                    }
                    heightCounter++;
                    break;
                }
                count++;
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Could not find file : " + file.getName() );
            e.printStackTrace();         
        }
    }


    /**
     * setting position value
     */
    private void computeMaze() {

        maze[start_X][start_Y] = 'S';
        maze[end_X][end_Y] = 'E'; 

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {

                if(maze[i][j] == '1') {
                    maze[i][j] = '#';
                }

                if(maze[i][j] == '0') {
                    maze[i][j] = ' ';
                }
            }  
        }       
    }


    /**
     * @param x     - x coordinate
     * @param y     - y coordinate
     * @return      - true when maze is solved
     */
    private boolean canSolve(int x, int y) { 

        if (maze[x][y] == '#') {
            return false;
        }

        if (maze[x][y] == 'E') {
            return true;
        }

        if (maze[x][y] == 'X') {
            return false;
        }

        maze[x][y] = 'X';
        
        //North
        if ((canSolve(x - 1 , y)) == true) {
            return true;
        }   

        //South
        if ((canSolve(x + 1, y)) == true) {
            return true;
        }
        
        //East
        if ((canSolve(x , y + 1)) == true) {
            return true;
        }
        
        //West
        if ((canSolve(x, y - 1)) == true) {
            return true;
        }
     

        maze[x][y] = ' ';
        return false;
    }

    /**
     * Output the solved maze
     */
    public void outputMaze() {
    	computeMaze();
    	
    	if(canSolve(start_X, start_Y)) {
        	System.out.println("OUTPUT:");
        	maze[start_X][start_Y] = 'S';
            for (int i = 0; i < maze.length; i++) {
                System.out.println(maze[i]);
            }
        }
        else {
            System.out.println("Could not solve maze");
        }    
      
    }
    
}
