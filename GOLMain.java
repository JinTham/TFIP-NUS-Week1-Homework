package HomeWorkWeek1;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;

public class GOLMain {
    public static void main(String[] args) {
        //Read the .gol file
        Path Path = Paths.get(args[0]);
        File file = Path.toFile();
        if (!file.exists()){ //Check if the file exist
            System.err.println("Cannot find file");
            System.exit(1);
        }
        try {
            //Setup reader
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            //Variable Declaration
            String line;
            String[] lineStream;
            int startRow = 0;
            int startCol = 0;
            String[][] grid = new String[0][0];
            int dataLine = -1;
            //Read each line of the file and build the grid accordingly
            while (null != (line = bReader.readLine())){
                lineStream = line.split(" ");
                //To read DATA grid (after the keyword DATA)
                if (dataLine>=0){
                    lineStream = line.split("");
                    for (int i=0;i<lineStream.length;i++){
                        grid[startRow+dataLine][startCol+i] = lineStream[i];
                    }
                    dataLine ++;
                }
                switch (lineStream[0]){
                    case "GRID":
                        grid = new String[Integer.parseInt(lineStream[1])][Integer.parseInt(lineStream[2])];
                        break;
                    case "START":
                        startRow = Integer.parseInt(lineStream[1]);
                        startCol = Integer.parseInt(lineStream[2]);
                        break;    
                    case "DATA":
                        dataLine ++;    
                        break;
                    default:
                }
            }
            //Close the reader
            bReader.close();
            reader.close();
            //Convert all the null values in grid to space string " "
            System.out.println("Original >>>");
            for (int i=0;i<grid.length;i++){
                for (int j=0;j<grid[0].length;j++){
                    if (grid[i][j]==null){
                        grid[i][j] = " ";
                    }
                }
                System.out.printf("%d:%s\n",i,String.join("",grid[i]));
            }
            //Iterate each generation
            Generation generation = new Generation(grid);
            int iter = 5;
            for (int i=0;i<iter;i++){
                System.out.printf("Iteration %d >>>\n",i+1);
                generation.run();
            }
        } catch (IOException ex) {
            System.err.printf("IO exception:%s\n", ex.getMessage());
            ex.printStackTrace();
        }
    }
}

