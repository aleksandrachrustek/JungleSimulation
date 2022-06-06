import java.util.ArrayList;

public class Map extends Simulation {

    public static ArrayList<Agent> agents = new ArrayList<>();

    //interakcje miedzy agentami
    public void interactions() {

        for (Agent agent1 : Map.agents) {
            String type1 = agent1.getType();
            //operacje na wieku
            if (type1.equals("ADULT") || type1.equals("CHILD")) {
                int a = agent1.getAge();
                agent1.setAge(a+1);
            }
            if (type1.equals("ADULT")) {
                int a = agent1.getAge();
                if(a>=99) {
                    setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[ ]");
                    Map.agents.remove(agent1);
                }
            }
            if (type1.equals("CHILD")) {
                int a = agent1.getAge();
                if(a>=18) {
                    setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[A]");
                    Map.agents.add(new Adult(agent1.getPosition()[0],agent1.getPosition()[1]));
                    Map.agents.remove(agent1);
                }
            }
            for (Agent agent2 : Map.agents) {
                if (agent1 != agent2) {
                    String type2 = agent2.getType();
                    if (agent1.getPosition() == agent2.getPosition()) {
                        //kryjowka
                        if ((type1.equals("ADULT")||type1.equals("CHILD")||type1.equals("MONKEY")) && type2.equals("HIDING")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[X]");
                            for (Agent agent3 : Map.agents) {
                                if (agent3.getType().equals("LION")) {
                                    agent3.go(this);
                                }
                            }
                        }
                        //funkcja kill
                        if (type1.equals("MONKEY") && type2.equals("LION")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[L]");
                            Map.agents.remove(agent1);
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
                        //ponowny ruch agentow nie zabijajacych sie nawzajem
                        if (type1.equals("MONKEY") && type2.equals("ADULT")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[A]");
                            agent2.setPosition(agent1.getPosition()[0], agent1.getPosition()[1]);
                            agent1.go(this);
                        }
                        if (type1.equals("MONKEY") && type2.equals("CHILD")) {
                            setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[C]");
                            agent2.setPosition(agent1.getPosition()[0],agent1.getPosition()[1]);
                            agent1.go(this);
                        }
                        if (type1.equals("MONKEY") && type2.equals("TARZAN")) {
                            agent1.go(this);
                            agent2.go(this);
                        }
                        if (type1.equals("ADULT") && type2.equals("CHILD")) {
                                setCharacter(agent1.getPosition()[0], agent1.getPosition()[1], "[C]");
                                agent2.setPosition(agent1.getPosition()[0], agent1.getPosition()[1]);
                                agent1.go(this);
                        }
                        //tarzan pomaga ludziom
                        if (type1.equals("ADULT") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            for (Agent agent3 : Map.agents) {
                                if (agent3.getType().equals("MONKEY")) {
                                    agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[A]");
                                    agent3.go(this);
                                }
                            }
                        }
                        if (type1.equals("CHILD") && type2.equals("TARZAN")) {
                            setCharacter(agent2.getPosition()[0], agent2.getPosition()[1], "[T]");
                            for (Agent agent3 : Map.agents) {
                                if (agent3.getType().equals("MONKEY")) {
                                    agent1.setPosition(agent3.getPosition()[0], agent3.getPosition()[1]);
                                    setCharacter(agent3.getPosition()[0], agent3.getPosition()[1], "[C]");
                                    agent3.go(this);
                                }
                            }
                        }
                        //funkcja copy
                        if (type1.equals("LION") && type2.equals("LION")) {
                            findFreeSpace();
                            Map.agents.add(new Lion(x,y));
                            this.map[x][y] = "[L]";
                        }
                        if (type1.equals("MONKEY") && type2.equals("MONKEY")) {
                            findFreeSpace();
                            Map.agents.add(new Monkey(x,y));
                            this.map[x][y] = "[M]";
                        }
                        if (type1.equals("ADULT") && type2.equals("ADULT")) {
                            findFreeSpace();
                            Map.agents.add(new Child(x,y));
                            this.map[x][y] = "[C]";
                        }
                    }
                }
            }
        }
    }
}