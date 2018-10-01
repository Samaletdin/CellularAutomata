import static java.lang.Math.random;

public class Cell {



    public enum CellFeature{
        E,
        F,
        R;
    }

    private CellFeature feature;

    public Cell(){
        if(random() < 0.3) this.feature = CellFeature.F;
        else this.feature = CellFeature.R;
    }

    public Cell(CellFeature feat){
        this.feature = feat;
    }

    public CellFeature getFeature() {
        return feature;
    }

    public void setFeature(CellFeature feature) {
        this.feature = feature;
    }

    @Override
    public String toString(){
        switch (this.feature){
            case E: return "O";
            case F: return "X";
            case R: return "-";
            default: return "NAN";
        }
    }

}
