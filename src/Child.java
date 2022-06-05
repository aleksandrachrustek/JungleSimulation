public class Child extends Human {
    private int age;
    Child(int x, int y){
        super(x,y);
        this.type = "CHILD";
    }

    @Override
    public void go(Simulation map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[C]");
    }

}
