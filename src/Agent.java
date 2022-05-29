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

//    public void go(Map map) {
//        Random rand = new Random();
//        int choice = rand.nextInt(4);

//    void go(String[][] board1, int boardSize) {
//        Random r = new Random();
//        int choice = r.nextInt(13) + 1;
//
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
//                if (x<boardSize-1 && x>0) {
//                    x--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 5:
//                if (y<boardSize-1 && y>0) {
//                    y--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 6:
//                if (x<boardSize-1 && x>0 && y<boardSize-1 && y>0) {
//                    x--;
//                    y--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 7:
//                if (x==boardSize-1 && y<boardSize) {
//                    y++;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 8:
//                if (x==boardSize-1 && y<boardSize && y>0) {
//                    y--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 9:
//                if (x<boardSize-1 && y==boardSize) {
//                    x++;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 10:
//                if (x<boardSize-1 && x>0 && y==boardSize) {
//                    x--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 11:
//                if (x==boardSize-1 && y==boardSize) {
//                    x--;
//                    y--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 12:
//                if (x==boardSize-1 && y==boardSize) {
//                    x--;
//                } else {
//                    go(board1, boardSize);
//                }
//            case 13:
//                if (x==boardSize-1 && y==boardSize) {
//                    y--;
//                } else {
//                    go(board1, boardSize);
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
