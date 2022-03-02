package GAME.player;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Potion> potiuni = new ArrayList<>();
    int greutatemax;
    int nrmonede;
    int greutateramasa;

    public Inventory(){
        this.greutatemax=0;
        this.nrmonede=0;

    }
    public Inventory(int greutatemax, int nrmonede){
        this.greutatemax=greutatemax;
        this.nrmonede=nrmonede;
        this.greutateramasa=greutatemax;
     }


     public String listaPotiuni(){
        String s =new String();
        for (int i =0;  i< potiuni.size(); i++)
            s = s + potiuni.get(i).getClass().getSimpleName()+ " ";

        return s;
     }

    public int getNrmonede() {
        return nrmonede;
    }

    public void setNrmonede(int nrmonede) {
        this.nrmonede = nrmonede;
    }

//    public int getGreutatemax() {
//        return greutatemax;
//    }
//
//    public void setGreutatemax(int greutatemax) {
//        this.greutatemax = greutatemax;
//    }

    public void addPotiune(Potion potiune){
      if (greutateramasa > potiune.preluareGreutate() && nrmonede > potiune.preluarePret()){
          potiuni.add(potiune);
          nrmonede=nrmonede- potiune.preluarePret();
          greutateramasa=greutateramasa- potiune.preluareGreutate();
      }
    }

    public void removePotiune(Potion potiune){
        if(potiuni.contains(potiune)){
            potiuni.remove(potiune);
            greutateramasa=greutateramasa + potiune.preluareGreutate();
        }
    }


}
