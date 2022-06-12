public class Child extends Human {
    Child(int x, int y, int age){
        super(x,y,age);
        this.type = "CHILD";
    }
    @Override
    public void go(Simulation map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[C]");
    }
}
