import java.util.Random;
abstract class Agent {

    int x;
    int y;
    Agent(int x, int y){
        this.x = x;
        this.y = y;
    }
    void go(String[][] board1, int boardSize) {
        Random r = new Random();
        int choice = r.nextInt(8) + 1;

        switch (choice) {
            case 1:
                if (x < boardSize - 1) {
                    x++;
                } else {
                    go(board1, boardSize);
                }
            case 2:
                if (y < boardSize - 1) {
                    y++;
                } else {
                    go(board1, boardSize);
                }
            case 3:
                if (x < boardSize - 1 && y < boardSize - 1) {
                    x++;
                    y++;
                } else {
                    go(board1, boardSize);
                }
            case 4:
                if () {
                    x--;
                }
            case 5:
                if () {
                    y--;
                }
            case 6:
                if () {
                    x--;
                    y--;
                }
            case 7:
                if () {
                    y++;
                }
            case 8:
                if () {
                    y++;
                }
        }

}
