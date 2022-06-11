public class Monkey extends Animal {
    Monkey(int x, int y, int age) {
        super(x,y,age);
        this.type = "MONKEY";
    }
    @Override
    public void go(Simulation map) {
//        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
//        map.setCharacter(getPosition()[0], getPosition()[1], "[M]");
    }
}
