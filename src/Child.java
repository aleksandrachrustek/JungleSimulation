/** class Child extending the class Human */
public class Child extends Human {
    Child(int x, int y, int age){
        super(x,y,age);
        this.type = "CHILD";
    }
    @Override
    public void go(Simulation map) { super.go(map); }
}
