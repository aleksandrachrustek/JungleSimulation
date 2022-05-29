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

    public Map(int size, int numberOfMonkeys, int numberOfLions, int numberOfChildren, int numberOfAdults, int numberOfHidings){

        Random rand = new Random();

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
//
        int x, y;
        for(int i=0; i<numberOfAdults; i++){
            do {
                x = rand.nextInt(size);
                y = rand.nextInt(size);
            }while(!this.map[x][y].equals("[ ]"));
            this.adults.add(new Adult(x,y));
            this.map[x][y] = "[A]";
        }

        for(int i=0; i<numberOfChildren; i++){
            do {
                x = rand.nextInt(size - 1);
                y = rand.nextInt(size - 1);
            }while(!this.map[x][y].equals("[ ]"));
            this.children.add(new Child(x,y));
            this.map[x][y] = "[C]";
        }

        do {
            x = rand.nextInt(size - 1);
            y = rand.nextInt(size - 1);
        }while(!this.map[x][y].equals("[ ]"));
        this.tarzan = new Tarzan(x, y);
        this.map[x][y] = "[T]";



    }

    public void show(){
        for (int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }


}
