import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy: ");
        Simulation.boardSize = scan.nextInt();
        System.out.println("Podaj ilość małp: ");
        Simulation.amountOfMonkeys = scan.nextInt();
        System.out.println("Podaj ilość lwów: ");
        Simulation.amountOfLions = scan.nextInt();
        System.out.println("Podaj ilość dorosłych: ");
        Simulation.amountOfAdults = scan.nextInt();
        System.out.println("Podaj ilość dzieci: ");
        Simulation.amountOfChildren = scan.nextInt();
        System.out.println("Podaj ilość tarzanów: ");
        Simulation.amountOfTarzans = scan.nextInt();
        System.out.println("Podaj ilość kryjówek: ");
        Simulation.numberOfHidings = scan.nextInt();
        scan.close();



    }
}
