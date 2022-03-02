package GAME.player;

import GAME.info.Account;
import GAME.info.Credentials;
import GAME.info.Information;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    ArrayList<Account> Accounts =new ArrayList<>();
    HashMap<Cell.Type, ArrayList<String>> dictionar = new HashMap<>();

    private static Game firstInstance = null;

    public static Game getInstance(){
        if (firstInstance == null){
            firstInstance = new Game();
        }

        return firstInstance;
    }


    public void readstories() {
        File input = new File("src/stories.json");
        try {
            JsonElement fileElement = JsonParser.parseReader( new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();


            ArrayList<String> empty =new ArrayList<>();
            ArrayList<String> enemy =new ArrayList<>();
            ArrayList<String> shop =new ArrayList<>();
            ArrayList<String> finish =new ArrayList<>();

            JsonArray jsonArrayofAccounts = fileObject.get("stories").getAsJsonArray();
            for (JsonElement accountElement : jsonArrayofAccounts){
                JsonObject  accountJsonObject = accountElement.getAsJsonObject();

                String type = accountJsonObject.get("type").getAsString();
                String value = accountJsonObject.get("value").getAsString();

                if (type.equalsIgnoreCase("EMPTY"))
                    empty.add(value);
                if (type.equalsIgnoreCase("SHOP"))
                    shop.add(value);
                if (type.equalsIgnoreCase("ENEMY"))
                    enemy.add(value);
                if (type.equalsIgnoreCase("FINISH"))
                    finish.add(value);

            }
            dictionar.put(Cell.Type.SHOP,shop);
            dictionar.put(Cell.Type.EMPTY,empty);
            dictionar.put(Cell.Type.ENEMY,enemy);
            dictionar.put(Cell.Type.FINISH,finish);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void printstory( Cell.Type type ){
        System.out.println(dictionar.get(type).get(0));
        dictionar.get(type).add(dictionar.get(type).get(0));
        dictionar.get(type).remove(0);


    }


    public void run() throws IOException {
        File input =new File("accounts.json");
        try {
            JsonElement fileElement = JsonParser.parseReader( new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayofAccounts = fileObject.get("accounts").getAsJsonArray();
            for (JsonElement accountElement : jsonArrayofAccounts){
                JsonObject  accountJsonObject = accountElement.getAsJsonObject();
                JsonObject credentialsJsonObject = accountJsonObject.get("credentials").getAsJsonObject();
                String email =credentialsJsonObject.get("email").getAsString();
                String password =credentialsJsonObject.get("password").getAsString();
                String name = accountJsonObject.get("name").getAsString();
                String country = accountJsonObject.get("country").getAsString();
                int maps_completed = accountJsonObject.get("maps_completed").getAsInt();
                ArrayList<String> jocuri =new ArrayList<>();
                JsonArray favorite_games= accountJsonObject.get("favorite_games").getAsJsonArray();
                for (int i =0; i< favorite_games.size();i++){
                    String joc =favorite_games.get(i).getAsString();
                    jocuri.add(joc);
                }

                ArrayList<Character> personaje =new ArrayList<>();

                JsonArray jsonArrayofCharacters = accountJsonObject.get("characters").getAsJsonArray();
                for (JsonElement characterElement : jsonArrayofCharacters) {
                    JsonObject characterJsonObject = characterElement.getAsJsonObject();
                    String charactername = characterJsonObject.get("name").getAsString();
                    int level = characterJsonObject.get("level").getAsInt();
                    int experience = characterJsonObject.get("experience").getAsInt();
                    String profession = characterJsonObject.get("profession").getAsString();
                    Character character = new CharacterFactory().makeCharacter(profession, charactername, experience, level);
                    personaje.add(character);
                }

                Credentials credentials= new Credentials(email, password);
                Information info =new Information.InfoBuilder()
                        .nume(name)
                        .tara(country)
                        .credentials(credentials)
                        .jocuri(jocuri)
                        .build();

                Account account =new Account(info , personaje, maps_completed);
                Accounts.add(account);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc =new Scanner(System.in);
        int i=0;
        int modjoc =0;
        System.out.println("Alege modul de joc: 1 -modul text , 2 - interfata grafica, 3- modul text nehardcodat \n ");
//        if (sc.next().equalsIgnoreCase("P")) //pentru testare hardcodata
//            i=1;
        modjoc = sc.nextInt();

        if( modjoc == 2)
            startGUI( Accounts);

        else {
            System.out.println("Email :  - Hint: marcel@yahoo.com");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String email;
            String parola;
            int index = 0;

            boolean okemail = false;
            while (!okemail) {
                email = reader.readLine();
                for ( i = 0; i < Accounts.size(); i++) {
                    if (email.equals(Accounts.get(i).getInfo().getCredentials().getEmail())) {
                        index = i;
                        okemail = true;
                        boolean ok = false;
                        System.out.println("Parola :   - Hint: 6K7GUxjsAc");
                        while (!ok) {
                            parola = reader.readLine();
                            if (Accounts.get(i).getInfo().getCredentials().getParola().equals(parola))
                                ok = true;
                            else
                                System.out.println("Incearca din nou : ");
                        }
                    }
                }
                if (!okemail)
                    System.out.println("Incearca alt email :");

            }
            Account playeraccount = Accounts.get(index);

        while(i != 1 & i != 2 & i != 3){
            System.out.println("Alege personajul : "+ playeraccount.getPersonaje() +" Enter  1 / 2 / 3");
            i= sc.nextInt();
        }
//            System.out.println("Alege personajul : " + playeraccount.getPersonaje() + " Apasa tasta 'P' ");
//            if (sc.next().equalsIgnoreCase("P"))
//                i = 1;
            Character playercharacter = playeraccount.personaje.get(i - 1);
            System.out.println("Ai ales " + playercharacter.toString());
            if (modjoc == 1)
                start(playercharacter, playeraccount);
            if (modjoc == 3)
                 startneharcodat(playercharacter,playeraccount);
            sc.close();
        }

    }

    public void play (Character player , Grid grid, Account playeraccount){

        if (grid.cellcurent.type.equals(Cell.Type.EMPTY)){

            Random rand =new Random();
            int x = rand.nextInt(1,6);  // sansa de 20% sa se gaseasca monede
            if (x == 2 ){
                int monedegasite = rand.nextInt(3,10);
                player.inventory.setNrmonede(player.inventory.getNrmonede() + monedegasite);
                System.out.println("Ai gasit " + monedegasite + " monede :) ");
            }else
                System.out.println("Nu ai gasit monede :( ");


        }

        else if (grid.cellcurent.type.equals(Cell.Type.ENEMY)){

            Enemy enemy = new Enemy();
            Scanner sc =new Scanner(System.in);
            int potiuni =0;
            while (enemy.viatacurenta!=0 && player.viatacurenta!=0){
                System.out.println("Ai viata curenta "+ player.viatacurenta +" si mana"+ player.manacurenta);
                System.out.println("Enemy are viata curenta "+ enemy.viatacurenta +" si mana"+ enemy.manacurenta);
                if (sc.next().equalsIgnoreCase("P")){
                    int damage ;

                    if (!player.abilitati.isEmpty()){
                        if (player.manacurenta > player.abilitati.get(0).getCostmana()) {
                            damage = player.abilitati.get(0).getDamage();
                            System.out.println("Ai folosit" +player.abilitati.get(0).getClass().getSimpleName() +"Enemy a primit damage de "+ damage );
                            player.folosireAbilitate(player.abilitati.get(0), enemy);
                        }
                        else{
                            enemy.receiveDamage(player.getDamage());
                            damage = player.getDamage();
                            System.out.println("Enemy a primit damage de "+ damage );
                        }
                    }else if(potiuni <2){
                            System.out.println("Ai folosit potiunea "+ player.inventory.potiuni.get(0).toString());
                            player.usePotion(player.inventory.potiuni.get(0));
                            player.inventory.potiuni.remove(0);
                            potiuni= potiuni + 1;
                        }
                    else{
                        enemy.receiveDamage(player.getDamage());
                        damage = player.getDamage();
                        System.out.println("Enemy a primit damage de "+ damage );

                    }


                }

                if (sc.next().equalsIgnoreCase("P") && enemy.viatacurenta > 0){
                    int damage ;
                    if (enemy.abilitati.isEmpty()) {
                        player.receiveDamage(enemy.getDamage());
                        damage = enemy.getDamage();
                        System.out.println("Ai primit damage de "+ damage );
                    }
                    else{
                            if (enemy.manacurenta > enemy.abilitati.get(0).getCostmana() ) {
                                damage = enemy.abilitati.get(0).getDamage();

                                enemy.folosireAbilitate(enemy.abilitati.get(0), player);
                                System.out.println("Ai primit damage de "+ damage );
                            }
                            else {
                            player.receiveDamage(enemy.getDamage());
                            damage = enemy.getDamage();
                            System.out.println("Ai primit damage de "+ damage );
                        }
                    }
                }
            }

            if (enemy.viatacurenta == 0){
                System.out.println("Ai castigat batalia si ai viata curenta: "+ player.viatacurenta );
                player.experienta = player.experienta +1;
                if (player.experienta > player.nivelcurent * 2)
                    player.nivelcurent = player.nivelcurent + 1;
                    player.experienta = 0;
            }
            else {
                System.out.println("Ai pierdut batalia !");
            }

        }
        else  if (grid.cellcurent.type.equals(Cell.Type.SHOP)){
            System.out.println("Ai ajuns la Shop ");
            Shop shop = new Shop();
            System.out.println(" Alege din lista de potiuni :" + shop);

            Scanner sc =new Scanner(System.in);

            for (int i= 0;i< 2; i++){
                if (sc.next().equalsIgnoreCase("P"))
                {
                    if (player.inventory.greutateramasa > shop.selectPotion(0).preluareGreutate()) {
                        if (player.inventory.nrmonede > shop.selectPotion(0).preluarePret()) {
                            player.buyPotion(shop.selectPotion(0));
                            System.out.println("Ai cumparat " + shop.selectPotion(0).toString());
                            shop.potionlist.remove(0);
                        } else {
                            System.out.println("Nu ai suficiente monede pentru a cumpara " + shop.selectPotion(0).toString());
                        }
                    } else
                        System.out.println(shop.selectPotion(0).toString() + " nu incape in inventar ");
                }
            }


        }
        else  if (grid.cellcurent.type.equals(Cell.Type.FINISH)){

            System.out.println(" Ai castigat !!! =) ");
            playeraccount.setNrjocuri(playeraccount.getNrjocuri()+1);
            System.out.println("Lvl curent: "+ player.nivelcurent +" si experienta :"+ player.experienta);

        }

        }

    public void start (Character player, Account playeraccount){
        System.out.println("Ati ales modul text");

        readstories();
        Grid grid = Grid.generareMapa(player);
        grid.printMapa(player);


        Scanner sc =new Scanner(System.in);

        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goEast(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);
        }

        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goEast(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);

        }
        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goEast(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);

        }
        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goEast(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);

        }

        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goSouth(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);

        }
        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goSouth(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);

        }
        if (sc.next().equalsIgnoreCase("P")& player.viatacurenta>0 ) {
            grid.goSouth(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);
            grid.printMapa(player);

        }


        if (sc.next().equalsIgnoreCase("P") && player.viatacurenta>0 ) {
            grid.goSouth(player);
            grid.printMapa(player);
            printstory(grid.cellcurent.type);
            play(player, grid, playeraccount);

        }


    }

    public void startGUI( ArrayList<Account> Accounts){
        Login log = new Login(Accounts);

    }

    public void playnehardcodat(Character player , Grid grid, Account playeraccount){

        if (grid.cellcurent.type.equals(Cell.Type.EMPTY)){

            Random rand =new Random();
            int x = rand.nextInt(1,6);  // sansa de 20% sa se gaseasca monede
            if (x == 2 ){
                int monedegasite = rand.nextInt(3,10);
                player.inventory.setNrmonede(player.inventory.getNrmonede() + monedegasite);
                System.out.println("Ai gasit " + monedegasite + " monede :) ");
            }else
                System.out.println("Nu ai gasit monede :( ");


        }

        else if (grid.cellcurent.type.equals(Cell.Type.ENEMY)){

            Enemy enemy = new Enemy();
            Scanner sc =new Scanner(System.in);
            while (enemy.viatacurenta!=0 && player.viatacurenta!=0){
                System.out.println("Ai viata curenta "+ player.viatacurenta +" si mana"+ player.manacurenta);
                System.out.println("Enemy are viata curenta "+ enemy.viatacurenta +" si mana"+ enemy.manacurenta);
                if (player.inventory.potiuni.isEmpty())
                    System.out.println("Nu mai ai potiuni ");
                else
                    System.out.println("Ai urmatoarele potiuni: " + player.inventory.potiuni);

                if (player.abilitati.isEmpty())
                    System.out.println("Nu mai ai abilitati ");
                else
                    System.out.println("Ai urmatoarele abilitati: " + player.afisareAbilitati());

                System.out.println("Alege tipul de atac:  1 -Atac normal / 2 -folosire abilitate / 3 -folosire potiune");
                int x = sc.nextInt();
                if(x == 1){
                    enemy.receiveDamage(player.getDamage());

                }else if(x == 2){
                    System.out.println("Alege indicele abilitatii dorite din lisa : 1/ 2/ 3/..");
                    int index = sc.nextInt();
                        if(index<= player.abilitati.size())
                            player.folosireAbilitate(player.abilitati.get(index-1),enemy );
                        else
                            System.out.println("Nu ai atatea abilitati");


                }else if ( x == 3){
                    System.out.println("Alege indicele potiunii dorite din lisa : 1/ 2/ 3/..");
                    int index = sc.nextInt();
                    player.usePotion(player.inventory.potiuni.get(index-1));
                    player.inventory.potiuni.remove(index-1);

                }


                if ( enemy.viatacurenta > 0){
                    int damage ;
                    if (enemy.abilitati.isEmpty()) {
                        player.receiveDamage(enemy.getDamage());
                        damage = enemy.getDamage();
                        System.out.println("Ai primit damage de "+ damage );
                    }
                    else{
                        if (enemy.manacurenta > enemy.abilitati.get(0).getCostmana() ) {
                            damage = enemy.abilitati.get(0).getDamage();

                            enemy.folosireAbilitate(enemy.abilitati.get(0), player);
                            System.out.println("Ai primit damage de "+ damage );
                        }
                        else {
                            player.receiveDamage(enemy.getDamage());
                            damage = enemy.getDamage();
                            System.out.println("Ai primit damage de "+ damage );
                        }
                    }
                }
            }

            if (enemy.viatacurenta == 0){
                System.out.println("Ai castigat batalia si ai viata curenta: "+ player.viatacurenta );
                player.experienta = player.experienta +1;
                if (player.experienta > player.nivelcurent * 2) {
                    player.experienta = 0;
                    player.levelUp();
                }
            }
            else {
                System.out.println("Ai pierdut batalia !");
            }

        }
        else  if (grid.cellcurent.type.equals(Cell.Type.SHOP)) {
            System.out.println("Ai ajuns la Shop ");
            Shop shop = new Shop();
            System.out.println("Alege indicele potiunii pe care vreisa o cumperi 1/2/3/... \nLista de potiuni :" + shop);
            int index = 0;
            Scanner sc = new Scanner(System.in);
            boolean ok = true;
            while (ok) {
                System.out.println("Vrei sa cumperi o potiune ? DA/NU");
                if (sc.next().equalsIgnoreCase("DA")) {
                    System.out.println("Alege indicele potiunii pe care vreisa o cumperi \nLista de potiuni :" + shop);
                    index = sc.nextInt();
                    if (player.inventory.greutateramasa > shop.selectPotion(index - 1).preluareGreutate()) {
                        if (player.inventory.nrmonede > shop.selectPotion(index - 1).preluarePret()) {
                            player.buyPotion(shop.selectPotion(index - 1));
                            System.out.println("Ai cumparat " + shop.selectPotion(index - 1).toString());
                            shop.potionlist.remove(index - 1);
                        } else {
                            System.out.println("Nu ai suficiente monede pentru a cumpara " + shop.selectPotion(0).toString());
                        }
                    } else
                        System.out.println(shop.selectPotion(0).toString() + " nu incape in inventar ");
                } else {
                    ok = false;
                }
            }
        }
        else  if (grid.cellcurent.type.equals(Cell.Type.FINISH)){

            System.out.println(" Ai castigat !!! =) ");
            playeraccount.setNrjocuri(playeraccount.getNrjocuri()+1);

        }


    }

    public void startneharcodat (Character player, Account playeraccount){
        readstories();
        Grid grid = Grid.generareMapa2(player);
        grid.printMapa(player);
        Scanner sc = new Scanner(System.in);
            while( ! grid.cellcurent.type.equals(Cell.Type.FINISH) && player.viatacurenta > 0){
                boolean ok = false;
                System.out.println("Alege o directie: nord / sud / est / vest");
                String directie = sc.next();
                if(directie.equalsIgnoreCase("nord")){
                    grid.goNorth(player);
                    ok = true;
                }
                else if(directie.equalsIgnoreCase("sud")){
                    grid.goSouth(player);
                    ok = true;
                }
                else if(directie.equalsIgnoreCase("est")){
                    grid.goEast(player);
                    ok = true;
                }
                else if(directie.equalsIgnoreCase("vest")){
                    grid.goWest(player);
                    ok = true;
                }

                if (ok){
                    grid.printMapa(player);
                    printstory(grid.cellcurent.type);
                    playnehardcodat(player, grid, playeraccount);
                }
            }
    }


}
