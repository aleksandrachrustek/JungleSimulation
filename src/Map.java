import java.util.ArrayList;
import java.util.Random;

public class Map {

    private int size;
    private String[][] map;
    private ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
    private ArrayList<Lion> lions = new ArrayList<Lion>();
    private ArrayList<Child> children = new ArrayList<Child>();
    private ArrayList<Adult> adults = new ArrayList<Adult>();
    private ArrayList<Hiding> hidings = new ArrayList<Hiding>();
    private Tarzan tarzan;

    private int x, y;

    Random rand;

    public Map(int size, int amountOfMonkeys, int amountOfLions, int amountOfChildren, int amountOfAdults, int numberOfHidings){

        rand = new Random();

        this.size = size;
        map = new String[this.size][this.size];
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.map[i][j] = "[ ]";
            }
        }
//        boolean tab[][] = new boolean[size][size];
//
//        for (int i=0; i<this.size; i++) {
//            for (int j = 0; j < this.size; j++) {
//                tab[i][j] = false;
//            }
//        }


        for(int i=0; i<amountOfAdults; i++){
            findFreeSpace();
            this.adults.add(new Adult(x,y));
            this.map[x][y] = "[A]";
        }

        for(int i=0; i<amountOfChildren; i++){
            findFreeSpace();
            this.children.add(new Child(x,y));
            this.map[x][y] = "[C]";
        }

        findFreeSpace();
        this.tarzan = new Tarzan(x, y);
        this.map[x][y] = "[T]";

        for(int i=0; i<amountOfMonkeys; i++){
            findFreeSpace();
            this.monkeys.add(new Monkey(x,y));
            this.map[x][y] = "[M]";
        }
        for(int i=0; i<amountOfLions; i++){
            findFreeSpace();
            this.lions.add(new Lion(x,y));
            this.map[x][y] = "[L]";
        }
    }

    void findFreeSpace(){
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        }while(!this.map[y][x].equals("[ ]"));
    }

    //zabezpieczyc przed brakiem akcji!!!!
    public void update(){
        int chooseAgent;
        int agentNumber = -1;

        while( agentNumber == -1 ) {

            chooseAgent = rand.nextInt(5)+1;
            switch (chooseAgent) {
                case 1: {
                    System.out.println("Tarzan");
                    agentNumber = 0;
                    tarzan.go(this);
                    break;
                }
                case 2: {
                    if (this.children.size() != 0) {
                        System.out.println("Child");
                        agentNumber = rand.nextInt(this.children.size());
                        this.children.get(agentNumber).go(this);
                    }
                    break;
                }
                case 3: {
                    if (this.adults.size() != 0) {
                        System.out.println("Adults");
                        agentNumber = rand.nextInt(this.adults.size());
                        this.adults.get(agentNumber).go(this);
                    }
                    break;
                }
                case 4: {
                    if (this.monkeys.size() != 0) {
                        System.out.println("Monkey");
                        agentNumber = rand.nextInt(this.monkeys.size());
                        this.monkeys.get(agentNumber).go(this);
                    }
                    break;
                }
                case 5: {
                    if (this.lions.size() != 0) {
                        System.out.println("Lion");
                        agentNumber = rand.nextInt(this.lions.size());
                        this.lions.get(agentNumber).go(this);
                    }
                    break;
                }
            }
        }
    }

    public void show(){
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }

    public void setCharacter(int x, int y, String c){
        this.map[x][y] = c;
    }

    public int getSize(){
        return this.size;
    }

}
