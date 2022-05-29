import java.util.ArrayList;
import java.util.Random;
abstract class Agent {

    private boolean hidden;
    private int pos[] = new int [2];

    Agent(){
        this.pos[0]=this.pos[1]=0;
        this.hidden=false;
    }

    Agent(int x, int y){
        this.pos[0] = x;
        this.pos[1] = y;
        this.hidden=false;
    }

    //0-left, 1-up, 2-right, 3-down, (do poprawienia: 4-up left, 5-down right, 6-up right, 7-down left)
    public void go(Map map) {
        Random rand = new Random();
        int dir = rand.nextInt(4);

        switch (dir) {
            case 0: {
                if(pos[0]==0){
                    System.out.println("nie mozna isc w lewo");
                }
                else{
                    pos[0]--;
                }
                break;
            }
            case 1: {
                if(pos[1]==0){
                    System.out.println("nie mozna isc w gore");
                }
                else{
                    pos[1]--;
                }
                break;
            }
            case 2: {
                if(pos[0]== map.getSize()-1){
                    System.out.println("nie mozna isc w prawo");
                }
                else{
                    pos[0]++;
                }
                break;
            }
            case 3: {
                if(pos[1]== map.getSize()-1){
                    System.out.println("nie mozna isc w dol");
                }
                else{
                    pos[1]++;
                }
                break;
            }
            /*case 4: {
                if(pos[0]==0 && pos[1]==0){
                    go(map);
                }
                else{
                    pos[0]--;
                    pos[1]--;
                }
                break;
            }
            case 5: {
                if(pos[0]== map.getSize()-1 && pos[1]== map.getSize()-1){
                    go(map);
                }
                else{
                    pos[0]++;
                    pos[1]++;
                }
                break;
            }
            case 6: {
                if(pos[1]==0 || pos[0]== map.getSize()-1){
                    go(map);
                }
                else{
                    pos[0]++;
                    pos[1]--;
                }
                break;
            }
            case 7: {
                if(pos[1]== map.getSize()-1 || pos[0]==0){
                    go(map);
                }
                else{
                    pos[0]--;
                    pos[1]++;
                }
                break;
            }*/
        }
    }

    public void setPosition(int x, int y){
        this.pos[0]=x;
        this.pos[1]=y;
    }

    public int[] getPosition(){
        return this.pos;
    }

    public boolean isHidden(){
        return this.hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
