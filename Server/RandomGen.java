package Server;
import java.util.Random;

/**
 * This classes allows for the generation of random numbers to be assigned to the client ID number. 
 * @author aaron
 *
 */
public class RandomGen {
	
	/**
	 * This method generates a random number between the upper and lower bounds set by the user.
	 * @param lo Lower bound of the range of numbers allowed
	 * @param hi Upper bound of the range of numbers allowed
	 * @return An integer between the lower and upper bound
	 */
	int discrete(int lo, int hi) {
		if (lo >= hi) {
			System.out.println("Error discrete, lo >= hi");
			System.exit(0);
		}

		Random r = new Random();
		int d = r.nextInt(hi - lo + 1) + lo;
		return d;
	}

}
