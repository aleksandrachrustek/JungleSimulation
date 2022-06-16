import java.io.FileNotFoundException;
import java.util.ArrayList;
/** klasa Map zależna od klasy Simulation.
 * posiada ona listę przechowującą wszystkich agentów
 */
public class Map extends Simulation{
    public static ArrayList<Agent> agents = new ArrayList<>();
   public Map(int size, int[] amounts) throws FileNotFoundException {
       super(size, amounts);
   }
}