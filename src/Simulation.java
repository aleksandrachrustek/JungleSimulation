public class Simulation {

    static int boardSize;
    static int amountOfMonkeys;
    static int amountOfLions;
    static int amountOfAdults;
    static int amountOfChildren;
    static int amountOfTarzans;
    static int numberOfHidings;

    String[][] board1 = new String[boardSize][boardSize];

     public void boardDisplay() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print("[" + board1[i][j] + "]");
            }
            System.out.println();
        }
    }
    



}
