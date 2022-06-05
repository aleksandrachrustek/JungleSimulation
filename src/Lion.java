public class Lion extends Animal {
    Lion(int x, int y) {
        super(x, y);
        this.type = "LION";
    }

    @Override
    public void go(Simulation map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[L]");
    }
}
