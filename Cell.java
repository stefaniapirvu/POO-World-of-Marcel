package GAME.player;

public class Cell {
    int Ox;
    int Oy;
    enum Type{
        EMPTY, ENEMY, SHOP, FINISH
    }
    Type type;
    boolean visited;
    CellElement cellelement;

    public Cell( int Ox, int Oy, Type type){
        this.Ox=Ox;
        this.Oy=Oy;
        this.type =type;
        this.visited=false;

    }

    public void setOx(int ox) {
        Ox = ox;
    }

    public void setOy(int oy) {
        Oy = oy;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public CellElement getCellelement() {
        return cellelement;
    }

}
