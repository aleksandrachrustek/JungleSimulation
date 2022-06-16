import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
public class Simulation {
    private final int size;
    String[][] map;
    int x, y;
    PrintWriter saver = new PrintWriter("dane.txt");
    int[] count;
    Random rand;
    ArrayList<Agent> toRemove;
    ArrayList<Agent> toAdd;

    public Simulation(int size, int[] amounts) throws FileNotFoundException {
        rand = new Random();
        this.size = size;

        /** tworzenie mapy - tablicy dwuwymiarowej typu String */
        map = new String[this.size][this.size];
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.map[i][j] = "[ ]";
            }
        }
        /** rozmieszczenie zadanej przez użytkownika ilości agentów na mapie */
        for(int i=0; i<amounts[0]; i++){
            findFreeSpace();
            Map.agents.add(new Monkey(x,y,1));
            this.map[x][y] = "[M]";
        }
        for(int i=0; i<amounts[1]; i++){
            findFreeSpace();
            Map.agents.add(new Lion(x,y,1));
            this.map[x][y] = "[L]";
        }
        for(int i=0; i<amounts[2]; i++){
            findFreeSpace();
            Map.agents.add(new Adult(x,y,18));
            this.map[x][y] = "[A]";
        }
        for(int i=0; i<amounts[3]; i++){
            findFreeSpace();
            Map.agents.add(new Child(x,y,1));
            this.map[x][y] = "[C]";
        }
        for(int i=0; i<amounts[4]; i++){
            findFreeSpace();
            Map.agents.add(new Hiding(x,y,0));
            this.map[x][y] = "[X]";
        }
        findFreeSpace();
        Map.agents.add(new Tarzan(x,y,100));
        this.map[x][y] = "[T]";
    }
    /** interakcje między agentami */
    public void interactions(Agent agent1) {

        String type1 = agent1.getType();
        /** operacje na wieku */
        if (type1.equals("ADULT") || type1.equals("CHILD") || type1.equals("MONKEY") || type1.equals("LION")) {
            agent1.setAge(agent1.getAge() + 2);
        }
        if (type1.equals("ADULT") && agent1.getAge() >= 70) { toRemove.add(agent1); }
        else if (type1.equals("LION") && agent1.getAge() >= 15) { toRemove.add(agent1); }
        else if (type1.equals("MONKEY") && agent1.getAge() >= 20) { toRemove.add(agent1); }
        else if (type1.equals("CHILD") && agent1.getAge() >= 18) {
            toAdd.add(new Adult(agent1.getPosition()[0], agent1.getPosition()[1], 18));
            toRemove.add(agent1);
        }
        for (Agent agent2 : Map.agents) {
            if (agent1 != agent2) {
                String type2 = agent2.getType();
                if (agent1.getPosition()[0] == agent2.getPosition()[0] && agent1.getPosition()[1] == agent2.getPosition()[1]) {
                    /** kryjówka */
                    if ((type1.equals("ADULT") || type1.equals("CHILD") || type1.equals("MONKEY")) && type2.equals("HIDING")) {
                        for (Agent agent3 : Map.agents) {
                            if (agent3.getType().equals("LION")) {
                                findNearestFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                agent3.setPosition(x, y);
                            }
                        }
                    } else if (type1.equals("LION") && type2.equals("HIDING")) {
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                    }
                    /** zabijanie */
                    if ((type1.equals("MONKEY") || type1.equals("ADULT") || type1.equals("CHILD")) && type2.equals("LION")) {
                        toRemove.add(agent1);
                    }
                    if (type1.equals("LION") && type2.equals("TARZAN")) {
                        toRemove.add(agent1);
                    }
                    /** ponowny ruch agentów nie zabijających się nawzajem */
                    if (type1.equals("MONKEY") && (type2.equals("ADULT")||type2.equals("CHILD")||type2.equals("TARZAN"))) {
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                    }
                    if (type1.equals("ADULT") && type2.equals("CHILD")) {
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                    }
                    /** pomoc ludziom przez tarzana */
                    if ((type1.equals("ADULT")||type1.equals("CHILD")) && type2.equals("TARZAN")) {
                        for (Agent agent3 : Map.agents) {
                            if (agent3.getType().equals("MONKEY")) {
                                agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                findNearestFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                agent3.setPosition(x, y);
                            }
                        }
                    }
                    /** rozmnażanie */
                    if (type1.equals("LION") && type2.equals("LION")) {
                        findFreeSpace();
                        toAdd.add(new Lion(x, y, 1));
                    }
                    if (type1.equals("MONKEY") && type2.equals("MONKEY")) {
                        findFreeSpace();
                        toAdd.add(new Monkey(x, y, 1));
                    }
                    if (type1.equals("ADULT") && type2.equals("ADULT")) {
                        findFreeSpace();
                        toAdd.add(new Child(x, y, 1));
                    }
                }
            }
        }
    }
    /** funkcja znajdująca najbliższe wolne miejsce w pobliżu przekazanych argumentów */
    void findNearestFreeSpace(int x0, int y0){
        int minx=-1, miny=-1, maxx=1, maxy=1;

        if(x0 == 0) minx = 0;
        if(y0 == 0) miny = 0;
        if(x0 == this.size-1) maxx = 0;
        if(y0 == this.size-1) maxy = 0;
        for(int i=x0+minx; i<x0+maxx; i++){
            for(int j=y0+miny; j<y0+maxy; j++){
                if(this.map[i][j].equals("[ ]")){
                    x = i;
                    y = j;
                }
            }
        }
        findFreeSpace();
    }
    /** funkcja znajdująca losowe wolne miejsce na mapie */
    void findFreeSpace(){
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while(!this.map[y][x].equals("[ ]"));
    }
    /** aktualizowanie mapy i zliczanie ilości agentów */
    public void update() {
        toRemove = new ArrayList<>();
        toAdd = new ArrayList<>();
        count = new int[]{0, 0, 0, 0};
        for (Agent agent : Map.agents) {
            String type = agent.getType();
            if(!type.equals("HIDING")) agent.go(this);
            interactions(agent);
        }
        Map.agents.removeAll(toRemove);
        Map.agents.addAll(toAdd);
        toRemove.clear();
        toAdd.clear();
        for (Agent agent : Map.agents) {
            String type = agent.getType();
            if(type.equals("MONKEY")) count[0]++;
            else if(type.equals("LION")) count[1]++;
            else if(type.equals("ADULT")) count[2]++;
            else if(type.equals("CHILD")) count[3]++;
        }
        save(count);
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                this.map[i][j] = "[ ]";
            }
        }
        for(Agent agent : Map.agents){
            int xx = agent.getPosition()[0];
            int yy = agent.getPosition()[1];
            String type = agent.getType();
            if(type.equals("MONKEY")) setCharacter(xx,yy,"[M]");
            else if(type.equals("LION")) setCharacter(xx,yy,"[L]");
            else if(type.equals("ADULT")) setCharacter(xx,yy,"[A]");
            else if(type.equals("CHILD")) setCharacter(xx,yy,"[C]");
            else if(type.equals("TARZAN")) setCharacter(xx,yy,"[T]");
        }
        for(Agent agent : Map.agents){
            if(agent.getType().equals("HIDING")){
                setCharacter(agent.getPosition()[0], agent.getPosition()[1], "[X]");
            }
        }
    }
    /** wyświetlanie mapy */
    public void show(){
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.map[i][j]);
            } System.out.println();
        }
    }
    /** zapis ilości agentów do pliku */
    public void save(int[] c) {
        saver.append(String.valueOf(c[0])).append(" ");
        saver.append(String.valueOf(c[1])).append(" ");
        saver.append(String.valueOf(c[2])).append(" ");
        saver.append(String.valueOf(c[3])).append("\n");
        saver.flush();
    }
    public void setCharacter(int x, int y, String c) { this.map[x][y] = c; }
    public int getSize() { return size; }
}
