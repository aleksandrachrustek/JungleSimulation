import java.io.FileNotFoundException;
import java.util.Scanner;
/** class Main responsible for communication with user and controlling
 * respective methods needed for the simulation to work such as
 * update or show.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        int size,count;
        int [] amounts= new int[5];

        /** scanning the input from the user - map size, amount of each agent's type, number of rounds */
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy: ");
        size = scan.nextInt();
        System.out.println("Podaj ilość małp (nie może przekraczać "+size*size+"): ");
        amounts[0] = scan.nextInt();
        System.out.println("Podaj ilość lwów (nie może przekraczać "+(size*size-amounts[0])+"): ");
        amounts[1] = scan.nextInt();
        System.out.println("Podaj ilość dorosłych (nie może przekraczać "+(size*size-amounts[0]-amounts[1])+"): ");
        amounts[2] = scan.nextInt();
        System.out.println("Podaj ilość dzieci (nie może przekraczać "+(size*size-amounts[0]-amounts[1]-amounts[2])+"): ");
        amounts[3] = scan.nextInt();
        System.out.println("Podaj ilość kryjówek (nie może przekraczać "+(size*size-amounts[0]-amounts[1]-amounts[2]-amounts[3])+"): ");
        amounts[4] = scan.nextInt();
        System.out.println("Podaj ilość rund do rozegrania: ");
        count = scan.nextInt();
        scan.close();

        /** creating a map with given parameters */
        Simulation map = new Simulation(size,amounts);
        map.show();
        Thread.sleep(1000);
        /** showcasing a map for given number of rounds */
        for(int i=1;i<=count;i++){
            System.out.println("Runda "+i+":");
            map.update();
            map.show();
            Thread.sleep(150);
        }
    }
}
