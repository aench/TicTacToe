import java.util.Random;
//import java.util.HashSet;

public class AIPlayer {

    //HashSet<GameSituation> allSituations;
    GameSituation[] situations;

    public AIPlayer(){
        /*allSituations = new HashSet<GameSituation>();
        for (int i=0;i<9;i++){
            GameSituation tmp = new GameSituation();
            allSituations.add(tmp);
            tmp.addOne();
        }
        printAllSituations();*/
        situations = new GameSituation[19683];
        for (int i=0;i<19683;i++){
            situations[i] = new GameSituation();
            situations[i].add(i);
        }
        //printAllSituations();
    }

    public void printAllSituations(){
        for(int i=0;i<19683;i++){
            situations[i].print();
        }
    }

    /*public void printAllSituations(){
        for(GameSituation situation : allSituations){
            situation.print();
        }
    }*/

    public boolean possibleMove(GameSituation s, int n){
        System.out.print("Controllo se c'è il gioco: ");
        s.setMove(n);
        s.print();
        for (int i=0;i<19683;i++){
            if (situations[i].isEqual(s)){
                System.out.print("Trovato il gioco: ");
                s.print();
                s.unsetMove(n);
                return true;
            }
        }
        s.unsetMove(n);
        return false;
    }

    public boolean quit(GameSituation s){
        System.out.println("Controllo se mi devo arrendere:");
        for (int i =0;i<9;i++){
            System.out.print("Prima di controllare per i = "+i+" : ");
            s.print();
            if (s.getVector()[i]==0 && possibleMove(s,i)){
                System.out.println("Non mi arrendo!");
                if (s.getVector()[i]==2){
                    s.unsetMove(i);
                }
                return false;
            }
        }

        System.out.println("Mi arrendo!");
        return true;
    }

    public int move(GameSituation s){
        if (quit(s)){
            return 10;
        }
        int m = 10;
        boolean stop = false;
        while(stop != true){
            Random rand = new Random();
            int x = rand.nextInt(9);
            System.out.println("Numero estratto: "+ x);
            if (s.getVector()[x]!=1 && s.getVector()[x]!=2 && possibleMove(s,x)){
                System.out.print("Game after move: ");
                s.setMove(x);
                s.print();
                m=x;
                stop = true;
            }
        }
        return m;
    }

    public int numberOfGames(){
        int result =0;
        GameSituation temporary = new GameSituation();
        for (int i=0;i<19683;i++){
            if (situations[i].isEqual(temporary)){

            }
            else {
                result = result +1;
            }
        }
        return result;
    }

    public void deleteGame(GameSituation s){
        for(int i=0;i<19683;i++){
            if (situations[i].isEqual(s)){
                System.out.print("Delete the game: ");
                situations[i].print();
                situations[i].setStart();
            }
        }
    }
}
