/** abstract class Animal extending the class Agent */
abstract class Animal extends Agent{
    Animal(int x, int y, int age) {
        super(x,y,age);
    }
    @Override
    public void go(Simulation map) {
        super.go(map);
    }
}
