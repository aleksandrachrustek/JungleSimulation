import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        int size,count,amountOfMonkeys,amountOfLions,amountOfAdults,amountOfChildren,numberOfHidings;

        //wczytanie ilości agentów podanej przez użytkownika
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy: ");
        size = scan.nextInt();
        System.out.println("Podaj ilość małp: ");
        amountOfMonkeys = scan.nextInt();
        System.out.println("Podaj ilość lwów: ");
        amountOfLions = scan.nextInt();
        System.out.println("Podaj ilość dorosłych: ");
        amountOfAdults = scan.nextInt();
        System.out.println("Podaj ilość dzieci: ");
        amountOfChildren = scan.nextInt();
        System.out.println("Podaj ilość kryjówek: ");
        numberOfHidings = scan.nextInt();
        System.out.println("Podaj ilość rund do rozegrania: ");
        count = scan.nextInt();
        scan.close();

        //stworzenie mapy o zadanych parametrach
        Map map = new Map(size,amountOfMonkeys,amountOfLions,amountOfChildren,amountOfAdults,numberOfHidings);
        map.show();
        Thread.sleep(5000);
        //wyświetlanie zaktualizowanej mapy przez podaną ilość rund
        for(int i=1;i<=count;i++){
            System.out.println("Runda "+i+":");
            map.update();
            map.show();
            Thread.sleep(5000);
        }
    }
}
