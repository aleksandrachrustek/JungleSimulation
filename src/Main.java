import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Map map = new Map(5,1,0,2,2,0);
        map.show();

        scan.nextByte();

        map.update();
        map.show();

        //wczytanie ilości agentów podanej przez użytkownika

//        System.out.println("Podaj rozmiar mapy: ");
//        Simulation.boardSize = scan.nextInt();
//        System.out.println("Podaj ilość małp: ");
//        Simulation.amountOfMonkeys = scan.nextInt();
//        System.out.println("Podaj ilość lwów: ");
//        Simulation.amountOfLions = scan.nextInt();
//        System.out.println("Podaj ilość dorosłych: ");
//        Simulation.amountOfAdults = scan.nextInt();
//        System.out.println("Podaj ilość dzieci: ");
//        Simulation.amountOfChildren = scan.nextInt();
//        System.out.println("Podaj ilość tarzanów: ");
//        Simulation.amountOfTarzans = scan.nextInt();
//        System.out.println("Podaj ilość kryjówek: ");
//        Simulation.numberOfHidings = scan.nextInt();
//        scan.close();

    }
}
