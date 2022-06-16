/** class Adult extending the class Human */
public class Adult extends Human {
    public Adult(int x, int y, int age){
        super(x,y,age);
        this.type = "ADULT";
    }
    @Override
    public void go(Simulation map) { super.go(map); }
}
