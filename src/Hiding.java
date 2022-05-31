import java.util.Random;

public class Hiding {

    public static int x;
    public static int y;

    public Hiding(int x, int y){
        this.x=x;
        this.y=y;
    }
    public static void choosePlaces(int size) {
        Random rand = new Random();
        x = rand.nextInt(size);
        y = rand.nextInt(size);
    }

}
