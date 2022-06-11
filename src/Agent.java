import java.util.Random;

abstract class Agent {
    private int age;
    protected String type;
    private final int[] pos = new int[2];

    Agent(int x, int y, int age) {
        pos[0] = x;
        pos[1] = y;
        this.age = age;
    }

    //poruszanie sie agentow
    public void go(Simulation map) {
        Random rand = new Random();
        int choice;
        /*int begin, end;

        begin = rand.nextInt(2);
        if(begin == 0){
            end = begin + rand.nextInt(2) + 1;
        }
        else // begin == 1 -> end = 2
            end = begin + 1;

       for(int i=begin; i<end; i++) {
           do {
               choice = rand.nextInt(2);
               switch (choice) {
                   case 0 -> {
                       if (!(pos[i] == 0)) {
                           pos[i]--;
                       } else {
                           choice = -1;
                       }
                       break;
                   }
                   case 1 -> {
                       if (!(pos[i] == map.getSize()-1)) {
                           pos[i]++;
                       } else {
                           choice = -1;
                       }
                       break;
                   }
               }
           } while (choice == -1);
       }*/
        do {
            choice = rand.nextInt(8);
            switch (choice) {
                case 0 -> {
                    if (!(pos[0] == 0)) {
                        pos[0]--;
                    } else {
                        choice = -1;
                    }
                }
                case 1 -> {
                    if (!(pos[1] == 0)) {
                        pos[1]--;
                    } else {
                        choice = -1;
                    }
                }
                case 2 -> {
                    if (!(pos[0] == map.getSize() - 1)) {
                        pos[0]++;
                    } else {
                        choice = -1;
                    }
                }
                case 3 -> {
                    if (!(pos[1] == map.getSize() - 1)) {
                        pos[1]++;
                    } else {
                        choice = -1;
                    }
                }
                case 4 -> {
                    if (pos[0] != 0 && pos[1] != 0) {
                        pos[0]--;
                        pos[1]--;
                    } else {
                        choice = -1;
                    }
                }
                case 5 -> {
                    if (pos[0] != map.getSize() - 1 && pos[1] != map.getSize() - 1) {
                        pos[0]++;
                        pos[1]++;
                    } else {
                        choice = -1;
                    }
                }
                case 6 -> {
                    if (pos[1] != 0 && pos[0] != map.getSize() - 1) {
                        pos[0]++;
                        pos[1]--;
                    } else {
                        choice = -1;
                    }
                }
                case 7 -> {
                    if (pos[1] != map.getSize() - 1 && pos[0] != 0) {
                        pos[0]--;
                        pos[1]++;
                    } else {
                        choice = -1;
                    }
                }
            }
        } while (choice == -1);
    }

    public int[] getPosition() {
        return pos;
    }

    public void setPosition(int x, int y) {
        this.pos[0] = x;
        this.pos[1] = y;
    }
    public String getType() {
        return this.type;
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
