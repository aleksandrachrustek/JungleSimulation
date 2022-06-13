import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        int size,count;
        int [] amounts= new int[5];

        //wczytanie ilości agentów podanej przez użytkownika
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

        //stworzenie mapy o zadanych parametrach
        Simulation map = new Simulation(size,amounts);
        map.show();
        Thread.sleep(1000);
        //wyświetlanie zaktualizowanej mapy przez podaną ilość rund
        for(int i=1;i<=count;i++){
            System.out.println("Runda "+i+":");
            map.update();
            map.show();
            Thread.sleep(250);
        }
    }
}
