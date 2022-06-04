import java.util.ArrayList;
import java.util.Random;

public class Map {

    private int size;
    private String[][] map;
    private ArrayList<Agent> agents = new ArrayList<>();
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
        for(int i=0; i<amountOfAdults; i++){
            findFreeSpace();
            this.agents.add(new Adult(x,y));
            this.map[x][y] = "[A]";
        }
        for(int i=0; i<amountOfChildren; i++){
            findFreeSpace();
            this.agents.add(new Child(x,y));
            this.map[x][y] = "[C]";
        }

        findFreeSpace();
        this.agents.add(new Tarzan(x,y));
        this.map[x][y] = "[T]";

        for(int i=0; i<amountOfMonkeys; i++){
            findFreeSpace();
            this.agents.add(new Monkey(x,y));
            this.map[x][y] = "[M]";
        }
        for(int i=0; i<amountOfLions; i++){
            findFreeSpace();
            this.agents.add(new Lion(x,y));
            this.map[x][y] = "[L]";
        }
        for(int i=0; i<numberOfHidings; i++){
            choosePlaces();
            this.agents.add(new Hiding(x,y));
            this.map[x][y] = "[X]";
        }
    }
    void findFreeSpace(){
        do {
            x = rand.nextInt(size-1);
            y = rand.nextInt(size-1);
        }while(!this.map[y][x].equals("[ ]"));
    }
    public void choosePlaces() {
        Random rand = new Random();
        x = rand.nextInt(size);
        y = rand.nextInt(size);
    }

    public void update() {

        for (Agent agent : agents) {
            agent.go(this);
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
