public class GameSituation {

    int[] v;

    public GameSituation(){
        v = new int[9];
        for (int i=0;i<9;i++){
            v[i]=0;
        }
    }

    public GameSituation setMove(int n){
        v[n]=2;
        return this;
    }
    public GameSituation unsetMove(int n){
        v[n]=0;
        return this;
    }

    public void setSituation(String[] s){
        for (int i=0;i<9;i++){
            if (s[i].equals("X")){
                v[i]=1;
            }
            else if (s[i].equals("O")){
                v[i]=2;
            }
            else if (s[i].equals(" ")){
                v[i]=0;
            }
        }
    }

    public void setStart(){
        for (int i=0;i<9;i++){
            v[i]=0;
        }
    }

    public int[] getVector(){
        return v;
    }

    public boolean isEqual(GameSituation s){
        int counter = 0;
        for (int i=0;i<9;i++){
            if (v[i]==s.getVector()[i]){
                counter++;
            }
        }
        if (counter == 9){
            return true;
        }
        return false;
    }

    public void addOne(){
        v[8]=v[8]+1;
        for (int i=8;i>=0;i--){
            if (v[i]==3){
                v[i-1]=v[i-1]+1;
                v[i]=0;
            }
        }
        if (v[8]==3){
            v[8]=0;
        }
    }

    public void add(int n){
        for (int i=0;i<n;i++){
            addOne();
        }
    }

    public void print(){
        System.out.print("[");
        for (int i=0;i<9;i++){
            System.out.print(v[i]+" ");
        }
        System.out.println("]");
    }


}
