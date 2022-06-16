/** abstrakcyjna klasa Human dziedzicząca po klasie Agent */
abstract class Human extends Agent {
    public Human(int x, int y, int age){
        super(x,y,age);
    }
    @Override
    public void go(Simulation map) {
        super.go(map);
    }
}
