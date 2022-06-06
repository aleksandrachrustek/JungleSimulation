public class Tarzan extends Agent {
    Tarzan(int x, int y, int age){
        super(x, y, age);
        this.type = "TARZAN";
    }

    @Override
    public void go(Simulation map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[T]");
    }

}
