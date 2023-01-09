package HomeWorkWeek1;

public class Generation {
    private String[][] grid;
    //Constructor
    public Generation(String[][] grid){
        this.grid = grid;
    }
    //Method
    public void run(){
        //Create a temp grid to store cells with "*" as int 1, and extend 1 layer of empty cell on all 4 directions
        int[][] tempGrid = new int[this.grid.length+2][this.grid[0].length+2];
        for (int i=0;i<this.grid.length;i++){
            for (int j=0;j<this.grid[i].length;j++){
                if (this.grid[i][j]!=null&&this.grid[i][j].equals("*")){
                    tempGrid[i+1][j+1] = 1;
                }
            }
        }
        //Count the number of Alive neighbour
        int neighbour = 0;
        for (int i=0;i<this.grid.length;i++){
            for (int j=0;j<this.grid[i].length;j++){
                //Count of neighbour = top cell + bottom cell + left cell + right cell + topleft cell + topright cell + bottomleft cell + bottomright cell
                neighbour = tempGrid[i+1-1][j+1]+tempGrid[i+1+1][j+1]+tempGrid[i+1][j+1-1]+tempGrid[i+1][j+1+1]+tempGrid[i+1-1][j+1-1]+tempGrid[i+1-1][j+1+1]+tempGrid[i+1+1][j+1-1]+tempGrid[i+1+1][j+1+1];
                if (this.grid[i][j].equals("*")){ //Check if cell is Alive or Dead
                    //Cell is Alive
                    switch (neighbour){
                        case 0: //Cells die
                        case 1:
                            this.grid[i][j] = " ";
                            break;
                        case 2: //Cells survive
                        case 3:
                            break;
                        default: //Cells with more than 3 neighbours will die
                            this.grid[i][j] = " ";
                    }
                } else if (this.grid[i][j].equals(" ")&&neighbour==3){
                    //Cell is Dead && with 3 neighbours will become alive
                    this.grid[i][j] = "*";
                }
                //System.out.printf("[%d,%d]%d%s,",i,j,neighbour,this.grid[i][j]);
            }
            //Print out each row of nextGrid
            System.out.printf("%d:%s\n",i,String.join("",this.grid[i]));
        }
    }
}
