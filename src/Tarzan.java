/** klasa Tarzan dziedzicząca po klasie Agent */
public class Tarzan extends Agent {
    Tarzan(int x, int y, int age){
        super(x, y, age);
        this.type = "TARZAN";
    }
    @Override
    public void go(Simulation map) { super.go(map); }
}
