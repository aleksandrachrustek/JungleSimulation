import java.util.ArrayList;

public class Map extends Simulation {

    public static ArrayList<Agent> agents = new ArrayList<>();

    //interakcje miedzy agentami (dodac smierc czlowieka ze wzgledu na wiek, zamiane dziecka w doroslego, kryjowke)
    public void interactions() {

        for (Agent agent1 : Map.agents) {
            String type1 = agent1.getType();
            /*if (type1.equals("ADULT") || type1.equals("CHILD")) {
                //inkrementacja wieku

            }*/
            for (Agent agent2 : Map.agents) {
                if (agent1 != agent2) {
                    String type2 = agent2.getType();
                    if (agent1.getPosition() == agent2.getPosition()) {
                        if (type1.equals("MONKEY") && type2.equals("LION")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                            Map.agents.remove(agent1);
                        }
                        if (type1.equals("MONKEY") && type2.equals("ADULT")) {
                            findFreeSpace();
                            setCharacter(x, y, "[M]");
                            agent1.setPosition(x, y);
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[A]");
                        }
                        if (type1.equals("MONKEY") && type2.equals("CHILD")) {
                            findFreeSpace();
                            setCharacter(x, y, "[M]");
                            agent1.setPosition(x, y);
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[C]");
                        }
                        if (type1.equals("MONKEY") && type2.equals("TARZAN")) {
                            findFreeSpace();
                            setCharacter(x, y, "[M]");
                            agent1.setPosition(x, y);
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                        }
                        if (type1.equals("LION") && type2.equals("ADULT")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                            Map.agents.remove(agent2);
                        }
                        if (type1.equals("LION") && type2.equals("CHILD")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                            Map.agents.remove(agent2);
                        }
                        if (type1.equals("LION") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            Map.agents.remove(agent1);
                        }
                        if (type1.equals("ADULT") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            for (Agent agent3 : Map.agents) {
                                if (agent3.getType().equals("MONKEY")) {
                                    findFreeSpace(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    agent1.setPosition(x, y);
                                    setCharacter(x, y, "[A]");
                                }
                            }
                        }
                        if (type1.equals("LION") && type2.equals("LION")) {
                            findFreeSpace();
                            Map.agents.add(new Lion(x, y));
                            this.map[x][y] = "[L]";
                        }
                        if (type1.equals("MONKEY") && type2.equals("MONKEY")) {
                            findFreeSpace();
                            Map.agents.add(new Monkey(x, y));
                            this.map[x][y] = "[M]";
                        }
                        if (type1.equals("ADULT") && type2.equals("ADULT")) {
                            findFreeSpace();
                            Map.agents.add(new Child(x, y));
                            this.map[x][y] = "[C]";
                        }
                    }
                }
            }
        }
    }
}