import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Simulation {
    private int size;
    String[][] map;
    int x;
    int y;
    int adultsCount=0, childrenCount=0, monkeysCount=0, lionsCount=0;
    Random rand;

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
            choosePlaces();
            Map.agents.add(new Hiding(x,y));
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
        // if place not found !!!
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

    //aktualizowanie mapy i zliczanie ilosc agentow
    public void update() {
        for (Agent agent : Map.agents) {
            agent.go(this);
            //wywolanie interakcji
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
