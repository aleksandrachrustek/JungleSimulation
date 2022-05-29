import java.util.Scanner;

public class Main {

    int boardSize;
    int amountOfMonkeys;
    int amountOfLions;
    int amountOfAdults;
    int amountOfChildren;
    int amountOfTarzans;
    int numberOfHidings;
    public void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj rozmiar mapy: ");
        boardSize = scan.nextInt();
        System.out.println("Podaj ilość małp: ");
        amountOfMonkeys = scan.nextInt();
        System.out.println("Podaj ilość lwów: ");
        amountOfLions = scan.nextInt();
        System.out.println("Podaj ilość dorosłych: ");
        amountOfAdults = scan.nextInt();
        System.out.println("Podaj ilość dzieci: ");
        amountOfChildren = scan.nextInt();
        System.out.println("Podaj ilość tarzanów: ");
        amountOfTarzans = scan.nextInt();
        System.out.println("Podaj ilość kryjówek: ");
        numberOfHidings = scan.nextInt();
        scan.close();

    }
}
