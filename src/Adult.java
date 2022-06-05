//human, adult, child, tarzan, map, i agent

public class Adult extends Human {
    private int age;

    public Adult(int x, int y){
        super(x,y);
    }

    @Override
    public void go(Simulation map) {
        map.setCharacter(getPosition()[0], getPosition()[1], "[ ]");
        super.go(map);
        map.setCharacter(getPosition()[0], getPosition()[1], "[A]");
    }

}
