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
//    public void go(Map map, int boardSize) {
//        Random r = new Random();
//        int choice = r.nextInt(8) + 1;
//
//        switch (choice) {
//            case 1:
//                if (x < boardSize - 1) {
//                    x++;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 2:
//                if (y < boardSize - 1) {
//                    y++;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 3:
//                if (x < boardSize - 1 && y < boardSize - 1) {
//                    x++;
//                    y++;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 4:
//                if () {
//                    x--;
//                }
//            case 5:
//                if () {
//                    y--;
//                }
//            case 6:
//                if () {
//                    x--;
//                    y--;
//                }
//            case 7:
//                if () {
//                    y++;
//                }
//            case 8:
//                if () {
//                    y++;
//                }
//        }
//    }

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
