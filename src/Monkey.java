/** class Monkey extending the class Animal */
public class Monkey extends Animal {
    Monkey(int x, int y, int age) {
        super(x,y,age);
        this.type = "MONKEY";
    }
    @Override
    public void go(Simulation map) { super.go(map); }
}
