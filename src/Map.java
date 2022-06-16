import java.io.FileNotFoundException;
import java.util.ArrayList;
/** class Map being in dependency with class Simulation.
 * contains an arraylist of all the agents
 */
public class Map extends Simulation{
    public static ArrayList<Agent> agents = new ArrayList<>();
   public Map(int size, int[] amounts) throws FileNotFoundException {
       super(size, amounts);
   }
}