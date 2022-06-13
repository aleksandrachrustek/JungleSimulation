public class Lion extends Animal {
    Lion(int x, int y, int age) {
        super(x, y, age);
        this.type = "LION";
    }
    @Override
    public void go(Simulation map) { super.go(map); }
}
