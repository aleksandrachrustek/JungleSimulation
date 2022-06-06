import java.util.Random;

abstract class Agent {
    private int age;
    protected String type;
    private int[] pos = new int[2];


    Agent(int x, int y, int age) {
        this.pos[0] = x;
        this.pos[1] = y;
        this.age = age;
    }

    //poruszanie sie agentow
    public void go(Simulation map) {
        Random rand = new Random();
        int choice = rand.nextInt(8);

        do {
            switch (choice) {
                case 0: {
                    if (!(pos[0] == 0)) {
                        pos[0]--;
                    }
                }
                case 1: {
                    if (!(pos[1] == 0)) {
                        pos[1]--;
                    }
                }
                case 2: {
                    if (!(pos[0] == map.getSize() - 1)) {
                        pos[0]++;
                    }
                }
                case 3: {
                    if (!(pos[1] == map.getSize() - 1)) {
                        pos[1]++;
                    }
                }
                case 4: {
                    if (!(pos[0] == 0 || pos[1] == 0)) {
                        pos[0]--;
                        pos[1]--;
                    }
                }
                case 5: {
                    if (!(pos[0] == map.getSize() - 1 || pos[1] == map.getSize() - 1)) {
                        pos[0]++;
                        pos[1]++;
                    }
                }
                case 6: {
                    if (!(pos[1] == 0 || pos[0] == map.getSize() - 1)) {
                        pos[0]++;
                        pos[1]--;
                    }
                }
                case 7: {
                    if (!(pos[1] == map.getSize() - 1 || pos[0] == 0)) {
                        pos[0]--;
                        pos[1]++;
                    }
                }
                default:
                    choice = -1;
            }
        } while (choice != -1);
    }

    public int[] getPosition() {
        return this.pos;
    }

    public void setPosition(int x, int y) {
        this.pos[0] = x;
        this.pos[1] = y;
    }

    public String getType() {
        return this.type;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
