package main.maze;

import java.io.IOException;

public class Main {
	
	 public static void main(String[] args) throws IOException {
	    	
	    	long startTime = System.nanoTime();

	        Maze2D maze2D = new Maze2D();
	        
	        //Enter Full file path below
	        maze2D.fileReader("C:\\Users\\chanjo\\eclipse-workspace\\2DMaze\\input.txt");	        
	        maze2D.outputMaze();
	                
	        long endTime = System.nanoTime();
	        long duration = endTime - startTime;      
	        System.out.println("Runtime took " + duration/1000000 + " seconds");
	        
	    }   

}
