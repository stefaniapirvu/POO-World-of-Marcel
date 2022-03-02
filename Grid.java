package GAME.player;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>> {

    int lungime;
    int inaltime;
    Character character;
    Cell cellcurent;

    private Grid(int lungime, int inaltime, Character character ,int Ox, int Oy) {
        this.lungime = lungime;
        this.inaltime = inaltime;
        this.character = character;

        for(int i  = 0; i < inaltime; i++){
           add(new ArrayList<>(lungime));
       }
        for(int i  = 0; i < inaltime; i++)
            for(int j  = 0; j < lungime; j++)
                get(i).add(new Cell(i,j,Cell.Type.EMPTY));

        get(inaltime-1).get(lungime-1).setType(Cell.Type.FINISH);
        get(0).get(3).setType(Cell.Type.SHOP);
        get(1).get(3).setType(Cell.Type.SHOP);
        get(2).get(0).setType(Cell.Type.SHOP);
        get(3).get(4).setType(Cell.Type.ENEMY);

        this.cellcurent = get(Ox).get(Oy);
        this.cellcurent.visited = true;


    }

    public static Grid generareMapa2(Character player){

        Grid grid = new Grid(5,5,player,0,0);
        player.Oxcurent = 0;
        player.Oycurent = 0;
        Random rand = new Random();
        int x= grid.lungime * grid.inaltime/3;
        int Shops = rand.nextInt(2,x);
        int Enemys = rand.nextInt(4 ,x);
        for ( int i = 0; i < Shops; i++ ){
            int j = rand.nextInt(grid.inaltime);
            int k = rand.nextInt(grid.lungime);
            grid.get(j).get(k).setType(Cell.Type.SHOP);
        }
        for ( int i = 0; i < Enemys; i++ ){
            int j = rand.nextInt(grid.inaltime);
            int k = rand.nextInt(grid.lungime);
            grid.get(j).get(k).setType(Cell.Type.ENEMY);
        }
        grid.get(0).get(0).setType(Cell.Type.EMPTY);
        grid.get(grid.inaltime-1).get(grid.lungime-1).setType(Cell.Type.FINISH);

        return grid;

    }

    public static Grid generareMapa(Character player){

        Grid grid = new Grid(5,5,player,0,0);
        player.Oxcurent = 0;
        player.Oycurent = 0;

        return grid;

    }

    public void printMapa (Character player){
//        get(0).get(0).visited =true;
        for(int i  = 0; i < inaltime; i++) {
            for (int j = 0; j < lungime; j++)
                if (get(i).get(j).visited) {
                    if (get(i).get(j).type.equals(Cell.Type.EMPTY)) {
                        if(player.Oxcurent == i && player.Oycurent ==j)
                            System.out.print("PN");
                        else
                            System.out.print("N ");
                    }
                    else if (get(i).get(j).type.equals(Cell.Type.FINISH)) {
                        if(player.Oxcurent == i && player.Oycurent ==j)
                            System.out.print("PF");
                        else
                            System.out.print("F ");
                    }
                    else if (get(i).get(j).type.equals(Cell.Type.SHOP)) {
                        if(player.Oxcurent == i && player.Oycurent ==j)
                            System.out.print("PS");
                        else
                            System.out.print("S ");
                    }
                    else {
                        if(player.Oxcurent == i && player.Oycurent ==j)
                            System.out.print("PE");
                        else
                            System.out.print("E ");
                    }
                } else
                    System.out.print("? ");
            System.out.println();
        }
        System.out.println();

    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void goNorth(Character player){
        if(player.Oxcurent == 0)
            System.out.println("Nu se poate deplasa in Nord");
        else {
            get(player.Oxcurent - 1).get(player.Oycurent).visited = true;
            cellcurent = get(player.Oxcurent - 1).get(player.Oycurent );
            player.Oxcurent = player.Oxcurent -1 ;

            cellcurent.setOx(player.Oxcurent);
            cellcurent.setOy(player.Oycurent);
        }

    }
    public void goSouth(Character player){
        if(player.Oxcurent == inaltime-1)
            System.out.println("Nu se poate deplasa in Sud");
        else {

            get(player.Oxcurent + 1).get(player.Oycurent).visited = true;
            cellcurent = get(player.Oxcurent + 1).get(player.Oycurent );
            player.Oxcurent = player.Oxcurent +1 ;

            cellcurent.setOx(player.Oxcurent);
            cellcurent.setOy(player.Oycurent);
        }

    }
    public void goWest(Character player){
        if(player.Oycurent == 0)
            System.out.println("Nu se poate deplasa in Vest");
        else {

            get(player.Oxcurent).get(player.Oycurent - 1).visited = true;
            cellcurent = get(player.Oxcurent).get(player.Oycurent - 1);
            player.Oycurent = player.Oycurent - 1 ;

            cellcurent.setOx(player.Oxcurent);
            cellcurent.setOy(player.Oycurent);
        }
    }
    public void goEast(Character player){
        if(player.Oycurent == lungime-1)
            System.out.println("Nu se poate deplasa in Est");
        else {

            get(player.Oxcurent).get(player.Oycurent + 1).visited = true;
            cellcurent = get(player.Oxcurent).get(player.Oycurent + 1);
            player.Oycurent = player.Oycurent +1 ;

            cellcurent.setOx(player.Oxcurent);
            cellcurent.setOy(player.Oycurent);

        }
    }



}
