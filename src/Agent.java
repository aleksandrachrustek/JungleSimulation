import java.util.Random;

abstract class Agent {
    private int age;
    protected String type;
    private int[] pos = new int[2];


    Agent(int x, int y, int age) {
        pos[0] = x;
        pos[1] = y;
        this.age = age;
    }

    //poruszanie sie agentow
    public void go(Simulation map) {
        Random rand = new Random();
        int choice;

        do {
            choice = rand.nextInt(8);
            switch (choice) {
                case 0: {
                    if (!(pos[0] == 0)) {
                        pos[0]--;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 1: {
                    if (!(pos[1] == 0)) {
                        pos[1]--;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 2: {
                    if (!(pos[0] == map.getSize() - 1)) {
                        pos[0]++;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 3: {
                    if (!(pos[1] == map.getSize() - 1)) {
                        pos[1]++;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 4: {
                    if (pos[0] != 0 && pos[1] != 0) {
                        pos[0]--;
                        pos[1]--;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 5: {
                    if (pos[0] != map.getSize() - 1 && pos[1] != map.getSize() - 1) {
                        pos[0]++;
                        pos[1]++;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 6: {
                    if (pos[1] != 0 && pos[0] != map.getSize() - 1) {
                        pos[0]++;
                        pos[1]--;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                case 7: {
                    if (pos[1] != map.getSize() - 1 && pos[0] != 0) {
                        pos[0]--;
                        pos[1]++;
                    }
                    else{
                        choice = -1;
                    }
                    break;
                }
                default:
                    choice = -1; // ???
                    break;
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
