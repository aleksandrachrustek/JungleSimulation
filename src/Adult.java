public class Adult extends Human {
    public Adult(int x, int y, int age){
        super(x,y,age);
        this.type = "ADULT";
    }
    @Override
    public void go(Simulation map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[A]");
    }
}
