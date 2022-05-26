import java.util.Scanner;

public class Main {
    static int amountOfMonkeys;
    static int amountOfLions;
    static int amountOfAdults;
    static int amountOfChildren;
    static int amountOfTarzans;
    static int numberOfHidings;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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
