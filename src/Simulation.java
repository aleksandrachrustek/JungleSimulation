import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private final int size;
    String[][] map;
    int x, y;
    int[] count;
    Random rand;
    PrintWriter saver = new PrintWriter("dane.txt");
    ArrayList<Agent> toRemove;
    ArrayList<Agent> toAdd;

    public Simulation(int size, int[] amounts) throws FileNotFoundException {//int size, int amountOfMonkeys, int amountOfLions, int amountOfChildren, int amountOfAdults, int numberOfHidings) throws FileNotFoundException {
        rand = new Random();
        this.size = size;

        //tworzenie mapy - tablicy dwuwymiarowej typu String
        map = new String[this.size][this.size];
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.map[i][j] = "[ ]";
            }
        }
        //rozmieszczenie zadanej przez użytkownika ilosci agentow na mapie
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
    //interakcje miedzy agentami
    public void interactions(Agent agent1) {

        String type1 = agent1.getType();
        //operacje na wieku
        if (type1.equals("ADULT") || type1.equals("CHILD") || type1.equals("MONKEY") || type1.equals("LION")) {
            agent1.setAge(agent1.getAge() + 2);
        }
        if (type1.equals("ADULT") && agent1.getAge() >= 70) {
            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
            toRemove.add(agent1);
        } else if (type1.equals("LION") && agent1.getAge() >= 15) {
            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
            toRemove.add(agent1);
        } else if (type1.equals("MONKEY") && agent1.getAge() >= 20) {
            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
            toRemove.add(agent1);
        } else if (type1.equals("CHILD") && agent1.getAge() >= 18) {
            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[A]");
            toAdd.add(new Adult(agent1.getPosition()[0], agent1.getPosition()[1], 18));
            toRemove.add(agent1);
        }
        for (Agent agent2 : Map.agents) {
            if (agent1 != agent2) {
                String type2 = agent2.getType();
                if (agent1.getPosition()[0] == agent2.getPosition()[0] && agent1.getPosition()[1] == agent2.getPosition()[1]) {
                    //kryjowka
                    if ((type1.equals("ADULT") || type1.equals("CHILD") || type1.equals("MONKEY")) && type2.equals("HIDING")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[X]");
//                        for (Agent agent3 : Map.agents) {
//                            if (agent3.getType().equals("LION")) {
//                                findNearestFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
//                                agent3.setPosition(x, y);
//                                setCharacter(x, y, "[L]");
//                            }
//                        }
                    } else if (type1.equals("LION") && type2.equals("HIDING")) {
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                        setCharacter(x, y, "[L]");
                    }
                    //funkcja kill
                    if ((type1.equals("MONKEY") || type1.equals("ADULT") || type1.equals("CHILD")) && type2.equals("LION")) {
                        setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                        toRemove.add(agent1);
                    }
                    if (type1.equals("LION") && type2.equals("TARZAN")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                        toRemove.add(agent1);
                    }
                    //ponowny ruch agentow nie zabijajacych sie nawzajem
                    if (type1.equals("MONKEY") && type2.equals("ADULT")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[A]");
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                        setCharacter(x, y, "[M]");
                    }
                    if (type1.equals("MONKEY") && type2.equals("CHILD")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[C]");
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                        setCharacter(x, y, "[M]");
                    }
                    if (type1.equals("MONKEY") && type2.equals("TARZAN")) {
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        setCharacter(x, y, "[M]");
                        findNearestFreeSpace(agent2.getPosition()[0], agent2.getPosition()[1]);
                        setCharacter(x, y, "[T]");
                    }
                    if (type1.equals("ADULT") && type2.equals("CHILD")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[C]");
                        findNearestFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                        agent1.setPosition(x, y);
                        setCharacter(x, y, "[A]");
                    }
                    //tarzan pomaga ludziom
                    if (type1.equals("ADULT") && type2.equals("TARZAN")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                        for (Agent agent3 : Map.agents) {
                            if (agent3.getType().equals("MONKEY")) {
                                agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[A]");
                                findNearestFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                agent3.setPosition(x, y);
                                setCharacter(x, y, "[M]");
                            }
                        }
                    }
                    if (type1.equals("CHILD") && type2.equals("TARZAN")) {
                        setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                        for (Agent agent3 : Map.agents) {
                            if (agent3.getType().equals("MONKEY")) {
                                agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[C]");
                                findNearestFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                agent3.setPosition(x, y);
                                setCharacter(x, y, "[M]");
                            }
                        }
                    }
                    //funkcja copy
                    if (type1.equals("LION") && type2.equals("LION")) {
                        findFreeSpace();
                        toAdd.add(new Lion(x, y, 1));
                        this.map[x][y] = "[L]";
                    }
                    if (type1.equals("MONKEY") && type2.equals("MONKEY")) {
                        findFreeSpace();
                        toAdd.add(new Monkey(x, y, 1));
                        this.map[x][y] = "[M]";
                    }
                    if (type1.equals("ADULT") && type2.equals("ADULT")) {
                        findFreeSpace();
                        toAdd.add(new Child(x, y, 1));
                        this.map[x][y] = "[C]";
                    }
                }
            }
        }
        // Zeby nie znikaly kryjowki
//        for (Agent agent : Map.agents) {
//            if (agent.getType().equals("HIDING")) {
//                setCharacter(agent.getPosition()[0], agent.getPosition()[1], "[X]");
//            }
//        }
    }

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
                    return;
                }
            }
        }
        findFreeSpace(); // jezeli nie ma wolnego miejsca wokol (x0,y0)
    }
    void findFreeSpace(){
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        }while(!this.map[y][x].equals("[ ]"));
    }

    //aktualizowanie mapy i zliczanie ilosc agentow
    public void update() {
        toRemove = new ArrayList<>();
        toAdd = new ArrayList<>();
        count = new int[4];
        for (Agent agent : Map.agents) {
            agent.go(this);
            interactions(agent);
            String type = agent.getType();
            switch (type) {
                case "MONKEY" -> count[0]++;
                case "LION" -> count[1]++;
                case "ADULT" -> count[2]++;
                case "CHILD" -> count[3]++;
            }
        }
        Map.agents.removeAll(toRemove);
        Map.agents.addAll(toAdd);
        save();
        toRemove.clear();
        toAdd.clear();

    }
    //wyswietlanie mapy
    public void show(){
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }
    //zapis ilosci agentow do pliku
    public void save() {
        saver.append(String.valueOf(count[0])).append(" ");
        saver.append(String.valueOf(count[1])).append(" ");
        saver.append(String.valueOf(count[2])).append(" ");
        saver.append(String.valueOf(count[3]));
        saver.append("\n");
    }
    public void setCharacter(int x, int y, String c){
        this.map[x][y] = c;
    }
    public int getSize() {
        return size;
    }
}
