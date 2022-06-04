public class Monkey extends Animal {
    Monkey(int x, int y) {
        super(x, y);
    }

    @Override
    public void go(Map map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[M]");
    }
}
