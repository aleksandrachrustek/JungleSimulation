public class Lion extends Animal {
    Lion(int x, int y) {
        super(x, y);
    }

    @Override
    public void go(Map map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[L]");
    }
}
