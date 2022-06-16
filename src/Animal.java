/** abstrakcyjna klasa Animal dziedzicząca po klasie Agent. */
abstract class Animal extends Agent{
    Animal(int x, int y, int age) {
        super(x,y,age);
    }
    @Override
    public void go(Simulation map) {
        super.go(map);
    }
}
