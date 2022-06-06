import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private int size;
    String[][] map;
    int x;
    int y;
    int adultsCount=0, childrenCount=0, monkeysCount=0, lionsCount=0;
    Random rand;
    ArrayList<Agent> toRemove;
    ArrayList<Agent> toAdd;
    public Simulation() {}

    public Simulation(int size, int amountOfMonkeys, int amountOfLions, int amountOfChildren, int amountOfAdults, int numberOfHidings){

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
        for(int i=0; i<amountOfAdults; i++){
            findFreeSpace();
            Map.agents.add(new Adult(x,y,18));
            this.map[x][y] = "[A]";
        }
        for(int i=0; i<amountOfChildren; i++){
            findFreeSpace();
            Map.agents.add(new Child(x,y,1));
            this.map[x][y] = "[C]";
        }
        findFreeSpace();
        Map.agents.add(new Tarzan(x,y,100));
        this.map[x][y] = "[T]";
        for(int i=0; i<amountOfMonkeys; i++){
            findFreeSpace();
            Map.agents.add(new Monkey(x,y,1));
            this.map[x][y] = "[M]";
        }
        for(int i=0; i<amountOfLions; i++){
            findFreeSpace();
            Map.agents.add(new Lion(x,y,1));
            this.map[x][y] = "[L]";
        }
        for(int i=0; i<numberOfHidings; i++){
            findFreeSpace();
//            choosePlaces();
            Map.agents.add(new Hiding(x,y,0));
            this.map[x][y] = "[X]";
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
            maxx = 0;
        }
        if(y0 == this.size-1){
            maxy = 0;
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
        System.out.println("Pos not found in 'findFreeSpace(x,y)'");
    }


    void findFreeSpace(){
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        }while(!this.map[y][x].equals("[ ]"));
    }
   public void choosePlaces() {
       Random rand = new Random();
       x = rand.nextInt(size);
       y = rand.nextInt(size);
   }

    //aktualizowanie mapy i zliczanie ilosc agentow
    public void update() {
        toRemove = new ArrayList<>();
        toAdd = new ArrayList<>();
        for (Agent agent : Map.agents) {
            agent.go(this);
            //wywolanie metody interakcje

            interactions(agent);

            String type = agent.getType();
            if(type.equals("MONKEY")){
                monkeysCount++;
            }
            if(type.equals("LION")){
                lionsCount++;
            }
            if(type.equals("ADULT")){
                adultsCount++;
            }
            if(type.equals("CHILDREN")) {
                childrenCount++;
            }
        }
        Map.agents.removeAll(toRemove);
        Map.agents.addAll(toAdd);
    }

    //interakcje miedzy agentami
    public void interactions(Agent agent1) {

            String type1 = agent1.getType();
            //operacje na wieku
//            if (type1.equals("ADULT") || type1.equals("CHILD") || type1.equals("MONKEY") || type1.equals("LION")) {
//                int a = agent1.getAge();
//                agent1.setAge(agent1.getAge()+10);
//            }
//            if (type1.equals("MONKEY") || type1.equals("LION")) {
//                int a = agent1.getAge();
//                agent1.setAge(a+10);
//            }
//            if (type1.equals("ADULT")) {
////                int a = agent1.getAge();
//                if(agent1.getAge()>=100) {
//                    setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
//                    Map.agents.remove(agent1);
//                    toRemove.add(agent1);
//                    System.out.println("Dead A");
//                }
//            }
//            if (type1.equals("LION")) {
////                int a = agent1.getAge();
//                if(agent1.getAge()>=100) {
//                    setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
//                    Map.agents.remove(agent1);
//                    toRemove.add(agent1);
//                    System.out.println("Dead L");
//                }
//            }
//            if (type1.equals("MONKEY")) {
////                int a = agent1.getAge();
//                if(agent1.getAge()>=100) {
//                    setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
//                    Map.agents.remove(agent1);
//                    toRemove.add(agent1);
//                    System.out.println("Dead M");
//                }
//            }
//            if (type1.equals("CHILD")) {
////                int a = agent1.getAge();
//                if(agent1.getAge()>=18) {
//                    setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[A]");
////                    Map.agents.add(new Adult(agent1.getPosition()[0],agent1.getPosition()[1],18));
////                    Map.agents.remove(agent1);
//                    toAdd.add(new Adult(agent1.getPosition()[0],agent1.getPosition()[1],18));
//                    toRemove.add(agent1);
//                }
//            }

            for (Agent agent2 : Map.agents) {
                if (agent1 != agent2) {
                    String type2 = agent2.getType();
                    if (agent1.getPosition()[0] == agent2.getPosition()[0] && agent1.getPosition()[1] == agent2.getPosition()[1]) {
                        //kryjowka
                        if ((type1.equals("ADULT")||type1.equals("CHILD")||type1.equals("MONKEY")) && type2.equals("HIDING")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[X]");
                            return;
//                            for (Agent agent3 : Map.agents) {
//                                if (agent3.getType().equals("LION")) {
//                                    agent3.go(this);
//                                    setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[L]");
//                                }
//                            }
                        }
                        else if (type1.equals("LION") && type2.equals("HIDING")){
                            findFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                            agent1.setPosition(x,y);
                            setCharacter(x, y, "[L]");
                            return;
                        }
                        //funkcja kill

                        if (type1.equals("MONKEY") && type2.equals("LION")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
//                            Map.agents.remove(agent1);
                            toRemove.add(agent1);
                            System.out.println("Lion ate Monkey");
                            return;
                        }
                        if (type1.equals("LION") && type2.equals("ADULT")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
//                            Map.agents.remove(agent2);
                            toRemove.add(agent2);
                            System.out.println("Lion ate Adult");
                            return;
                        }
                        if (type1.equals("LION") && type2.equals("CHILD")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
//                            Map.agents.remove(agent2);
                            toRemove.add(agent2);
                            System.out.println("Lion ate Child");
                            return;
                        }
                        if (type1.equals("LION") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
//                            Map.agents.remove(agent1);
                            toRemove.add(agent1);
                            System.out.println("Tarzan killed Lion");
                            return;
                        }
                        //ponowny ruch agentow nie zabijajacych sie nawzajem
                        if (type1.equals("MONKEY") && type2.equals("ADULT")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[A]");
                            findFreeSpace();
                            agent1.setPosition(x,y);
                            setCharacter(x,y,"[M]");
//                            agent2.setPosition(agent1.getPosition()[0], agent1.getPosition()[1]);
//                            agent1.go(this);
                            return;
                        }
                        if (type1.equals("MONKEY") && type2.equals("CHILD")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[C]");
                            findFreeSpace();
                            agent1.setPosition(x,y);
                            setCharacter(x,y,"[M]");
//                            agent2.setPosition(agent1.getPosition()[0],agent1.getPosition()[1]);
//                            agent1.go(this);
                            return;
                        }
                        if (type1.equals("MONKEY") && type2.equals("TARZAN")) {
                            findFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                            setCharacter(x, y, "[M]");
                            findFreeSpace(agent2.getPosition()[0], agent2.getPosition()[1]);
                            setCharacter(x, y, "[T]");
                            return;
                        }
                        if (type1.equals("ADULT") && type2.equals("CHILD")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[C]");
//                            agent2.setPosition(agent1.getPosition()[0], agent1.getPosition()[1]);
                            findFreeSpace(agent1.getPosition()[0], agent1.getPosition()[1]);
                            agent1.setPosition(x,y);
                            setCharacter(x, y, "[A]");
                            return;
                        }
                        //tarzan pomaga ludziom
                        if (type1.equals("ADULT") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            for (Agent agent3 : Map.agents) {
                                if (agent3.getType().equals("MONKEY")) {
                                    agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[A]");

                                    findFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    agent3.setPosition(x,y);
                                    setCharacter(x, y, "[M]");
                                    System.out.println("Tarzan helped Human");
                                    return;
                                }
                            }

                        }
                        if (type1.equals("CHILD") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            for (Agent agent3 : Map.agents) {
                                if (agent3.getType().equals("MONKEY")) {
                                    agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[C]");
                                    findFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    agent3.setPosition(x,y);
                                    setCharacter(x, y, "[M]");
                                    System.out.println("Tarzan helped Child");
                                    return;
                                }
                            }
                        }
                        //funkcja copy
                        if (type1.equals("LION") && type2.equals("LION")) {
                            findFreeSpace();
//                            Map.agents.add(new Lion(x,y,1));
                            toAdd.add(new Lion(x,y,1));
                            this.map[x][y] = "[L]";
                            System.out.println("Copied Lion");
                            return;

                        }
                        if (type1.equals("MONKEY") && type2.equals("MONKEY")) {
                            findFreeSpace();
//                            Map.agents.add(new Monkey(x,y,1));
                            toAdd.add(new Monkey(x,y,1));
                            this.map[x][y] = "[M]";
                            System.out.println("Copied Monkey");
                            return;

                        }
                        if (type1.equals("ADULT") && type2.equals("ADULT")) {
                            findFreeSpace();
//                            Map.agents.add(new Child(x,y,1));
                            toAdd.add(new Child(x,y,1));
                            this.map[x][y] = "[C]";
                            System.out.println("Copied Adult");
                            return;

                        }
                    }
                }
            }
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
    public void save() throws FileNotFoundException {
        PrintWriter saver = new PrintWriter("dane.txt");
        saver.print(adultsCount+" "+childrenCount+" "+monkeysCount+" "+lionsCount);
        saver.close();
    }
    public void setCharacter(int x, int y, String c){
        this.map[x][y] = c;
    }
    public int getSize() {
        return size;
    }
}
