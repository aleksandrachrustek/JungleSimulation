import java.util.Random;
abstract class Agent {

    private boolean hidden;
    private int[] pos = new int [2];


    Agent(int x, int y){
        this.pos[0] = x;
        this.pos[1] = y;
        this.hidden=false;
    }

    //0-left, 1-up, 2-right, 3-down, 4-up left, 5-down right, 6-up right, 7-down left
    public void go(Simulation map) {
        Random rand = new Random();
        int dir = rand.nextInt(8);

        int a = 0;
        do {
            switch (dir) {
                case 0 -> {
                    if (pos[0] == 0) {
                        break;
                    } else {
                        pos[0]--;
                        a = 1;
                    }
                }
                case 1 -> {
                    if (pos[1] == 0) {
                        break;
                    } else {
                        pos[1]--;
                        a = 1;
                    }
                }
                case 2 -> {
                    if (pos[0] == map.getSize() - 1) {
                        break;
                    } else {
                        pos[0]++;
                        a = 1;
                    }
                }
                case 3 -> {
                    if (pos[1] == map.getSize() - 1) {
                        break;
                    } else {
                        pos[1]++;
                        a = 1;
                    }
                }
                case 4 -> {
                    if (pos[0] == 0 || pos[1] == 0) {
                        break;
                    } else {
                        pos[0]--;
                        pos[1]--;
                        a = 1;
                    }
                }
                case 5 -> {
                    if (pos[0] == map.getSize() - 1 || pos[1] == map.getSize() - 1) {
                        break;
                    } else {
                        pos[0]++;
                        pos[1]++;
                        a = 1;
                    }
                }
                case 6 -> {
                    if (pos[1] == 0 || pos[0] == map.getSize() - 1) {
                        break;
                    } else {
                        pos[0]++;
                        pos[1]--;
                        a = 1;
                    }
                }
                case 7 -> {
                    if (pos[1] == map.getSize() - 1 || pos[0] == 0) {
                        break;
                    } else {
                        pos[0]--;
                        pos[1]++;
                        a = 1;
                    }
                }
            }
        } while (a == 0);
    }

    public int[] getPosition(){
        return this.pos;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public boolean isHidden(){
        return this.hidden;
    }



}
