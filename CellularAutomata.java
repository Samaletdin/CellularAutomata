import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CellularAutomata {

    private Cell[][] cellMatrix;
    private int size;


    public CellularAutomata(int N){
        this.size = N;
        this.cellMatrix = new Cell[N][N];
        for(int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                cellMatrix[i][j] = new Cell();
            }
        }
    }

    public boolean checkFire(int index_i, int index_j, Cell[][] tempMatrix){
        int counter = 0;
        if(tempMatrix[index_i-1][index_j].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i+1][index_j].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i][index_j+1].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i][index_j-1].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i-1][index_j-1].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i+1][index_j-1].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i-1][index_j+1].getFeature() == Cell.CellFeature.F) counter++;
        if(tempMatrix[index_i+1][index_j+1].getFeature() == Cell.CellFeature.F) counter++;
        if(counter == 2) return true;                                                           //Array here that stores the number of firings per timestep
        return false;                                                                           //fix an output method to plot in matlab
    }

    public void iterate(){
        Cell[][] tempMatrix = new Cell[this.size+2][this.size+2];
        for(int i = 1; i < this.size+1; i++ ) {
            tempMatrix[0][i] = this.cellMatrix[this.size - 1][i-1];
            tempMatrix[this.size + 1][i] = this.cellMatrix[0][i-1];
            tempMatrix[i][0] = this.cellMatrix[i-1][this.size - 1];
            tempMatrix[i][this.size + 1] = this.cellMatrix[i-1][0];
            for (int j = 1; j < this.size + 1; j++) {
                tempMatrix[i][j] = this.cellMatrix[i - 1][j - 1];
            }
        }
        tempMatrix[0][0] = this.cellMatrix[this.size-1][this.size-1];
        tempMatrix[0][this.size+1] = this.cellMatrix[0][this.size-1];
        tempMatrix[this.size+1][0] = this.cellMatrix[this.size-1][0];
        tempMatrix[this.size+1][this.size+1] = this.cellMatrix[0][0];

        Cell tempCell;
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                tempCell = tempMatrix[i+1][j+1];
                if(tempCell.getFeature() == Cell.CellFeature.R && checkFire(i+1,j+1, tempMatrix)) this.cellMatrix[i][j] = new Cell(Cell.CellFeature.F);
                else if(tempCell.getFeature() == Cell.CellFeature.E) this.cellMatrix[i][j] = new Cell(Cell.CellFeature.R);
                else if(tempCell.getFeature() == Cell.CellFeature.F) this.cellMatrix[i][j] = new Cell(Cell.CellFeature.E);
            }
        }
        this.toString();
    }

    public String toString(){
        String retval = "";
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                retval += this.cellMatrix[i][j].toString() + " ";
            }
            retval += "\n";
        }
        return retval;
    }

    public static void main(String[] args){
        int iterations = 100;
        int sizee = 40;

        CellularAutomata cellularAutomata = new CellularAutomata(sizee);
        System.out.println(cellularAutomata);
        int c = 0;
        while(c < iterations){
            cellularAutomata.iterate();
            System.out.println(cellularAutomata);
            c++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
