import java.util.Random;

public class Simulation {
    private int size;
    private String[][] map;
    private int x, y;
    Random rand;
    int adultsCount, childrenCount, monkeysCount, lionsCount;

    public Simulation() {}

    public Simulation(int size, int amountOfMonkeys, int amountOfLions, int amountOfChildren, int amountOfAdults, int numberOfHidings){

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
            Map.agents.add(new Adult(x,y));
            this.map[x][y] = "[A]";
        }
        for(int i=0; i<amountOfChildren; i++){
            findFreeSpace();
            Map.agents.add(new Child(x,y));
            this.map[x][y] = "[C]";
        }

        findFreeSpace();
        Map.agents.add(new Tarzan(x,y));
        this.map[x][y] = "[T]";

        for(int i=0; i<amountOfMonkeys; i++){
            findFreeSpace();
            Map.agents.add(new Monkey(x,y));
            this.map[x][y] = "[M]";
        }
        for(int i=0; i<amountOfLions; i++){
            findFreeSpace();
            Map.agents.add(new Lion(x,y));
            this.map[x][y] = "[L]";
        }
        for(int i=0; i<numberOfHidings; i++){
            findFreeSpace();
            //            choosePlaces();
            Map.agents.add(new Hiding(x,y));
            this.map[x][y] = "[X]";
        }
    }

    public void interactions() {

        for(Agent agent1 : Map.agents){
            for(Agent agent2 : Map.agents) {
                if (agent1!=agent2) {
                    if (agent1.getPosition() == agent2.getPosition()) {
                        String type1 = agent1.getType();
                        String type2 = agent2.getType();

                        if(type1.equals("MONKEY") && type2.equals("LION")){
                           setCharacter(agent1.getPosition()[0],agent1.getPosition()[1], "[L]");
                           Map.agents.remove(agent1);
                        }
                        if(type1.equals("MONKEY") && type2.equals("ADULT")){
                            findFreeSpace();
                            setCharacter(x,y,"[M]");
                            agent1.setPosition(x,y);
                            setCharacter(agent2.getPosition()[0],agent2.getPosition()[1], "[A]");
                        }
                        if(type1.equals("MONKEY") && type2.equals("CHILD")){
                            findFreeSpace();
                            setCharacter(x,y,"[M]");
                            agent1.setPosition(x,y);
                            setCharacter(agent2.getPosition()[0],agent2.getPosition()[1], "[C]");
                        }
                        if(type1.equals("MONKEY") && type2.equals("TARZAN")){
                            findFreeSpace();
                            setCharacter(x,y,"[M]");
                            agent1.setPosition(x,y);
                            setCharacter(agent2.getPosition()[0],agent2.getPosition()[1], "[T]");
                        }
                        if(type1.equals("LION") && type2.equals("ADULT")){
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                            Map.agents.remove(agent2);
                        }
                        if(type1.equals("LION") && type2.equals("CHILD")){
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                            Map.agents.remove(agent2);
                        }
                        if(type1.equals("LION") && type2.equals("TARZAN")){
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            Map.agents.remove(agent1);
                        }
                        if(type1.equals("ADULT") && type2.equals("TARZAN")){
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            for (Agent agent3 : Map.agents){
                                if(agent3.getType().equals("MONKEY")){
                                    findFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    agent1.setPosition(x,y);
                                    setCharacter(x,y,"[A]");
                                }
                            }

                        }
                    }
                }
            }
        }

    }

    void findFreeSpace(int x0, int y0){
        int minx=-1, miny=-1;
        int maxx=1, maxy=1;

        if(x0 == 0){
            minx = 0;
        }
        if(y0 == 0){
            miny = 0;
        }

        if(x0 == this.size-1){
            minx = 0;
        }
        if(y0 == this.size-1){
            miny = 0;
        }

        for(int i=x0+minx; i<x0+maxx; i++){
            for(int j=y0+miny; j<y0+maxy; j++){
                if(this.map[i][j].equals("[ ]")){
                    x = i;
                    y = j;
                    return;
                }
            }
        }
        // if place not found !!!
    }


    void findFreeSpace(){
        do {
            x = rand.nextInt(size-1);
            y = rand.nextInt(size-1);
        }while(!this.map[y][x].equals("[ ]"));
    }
//    public void choosePlaces() {
//        Random rand = new Random();
//        x = rand.nextInt(size);
//        y = rand.nextInt(size);
//    }
    public void update() {
//        this.map = getMap();
        for (Agent agent : Map.agents) {
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
    public String[][] getMap() {
        return map;
    }
    public void setMap(String[][] map) {
        this.map = map;
    }
}
