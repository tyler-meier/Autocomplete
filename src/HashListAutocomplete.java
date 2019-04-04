import java.util.*;

public class HashListAutocomplete implements Autocompletor {
	
	private static final int MAX_PREFIX = 10;
	private Map<String, List<Term>> myMap;
	private int mySize;
	
	/**
	 * The constructor for the HashListAutocomplete class. 
	 * @param terms, list of all the possible terms
	 * @param weights, list of all weights corresponding to each term in terms
	 * @throws NullPointerException, if terms or weights is null
	 * @throws IllegalArgumentException, if the lengths of terms and weights don't match
	 * 
	 */
	public HashListAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}
		if (terms.length != weights.length) {
			throw new IllegalArgumentException("terms and weights are not the same length");
		}
		initialize(terms, weights);
	}

	/**
	 * Returns the top k matching terms in descending order of weight. If there
	 * are fewer than k matches, return all matching terms in descending order
	 * of weight. If there are no matches, return an empty list.
	 * @param prefix, is the prefix being searched for in map
	 * @param k, is the amount of top terms desired
	 * @return top k matching terms or empty list depending on above
	 */
	@Override
	public List<Term> topMatches(String prefix, int k) {
		List<Term> all = myMap.get(prefix);
		if (all == null) {
			return new ArrayList<>();
		}
		return all.subList(0, Math.min(k, all.size()));
	}

	/**
	 * Create internal state needed to store Term objects
	 * from the parameters. Should be called in implementing
	 * constructors. Creates a map with all prefixes and a list of terms
	 * corresponding to each prefix
	 * @param terms is array of Strings for words in each Term
	 * @param weights is corresponding weight for word in terms
	 */
	@Override
	public void initialize(String[] terms, double[] weights) {
		
		myMap = new HashMap<>();
		
		for(int i = 0; i < terms.length; i++) {
			String term = terms[i];
			double weight = weights[i];
			Term val = new Term(term, weight);
			int j = Math.min(terms[i].length(), MAX_PREFIX);
			for (int x = 0; x <= j; x++) {
				String key = term.substring(0,x);
				if (! myMap.containsKey(key)) {
					myMap.put(key, new ArrayList<Term>());
				}
				myMap.get(key).add(val);
			}
		}
		Comparator<Term> rwo = Comparator.comparing(Term::getWeight).reversed();
		for(String key : myMap.keySet()) {
			Collections.sort(myMap.get(key), rwo);
		}
	}

	/**
	 * Return size in bytes of all Strings and doubles
	 * stored in implementing class. To the extent that
	 * other types are used for efficiency, there size should
	 * be included too 
	 * @return number of bytes used after initialization
	 */
	@Override
	public int sizeInBytes() {	
		if (mySize == 0) {
			for(String key : myMap.keySet()) {
			    mySize += BYTES_PER_CHAR*key.length();
			    for (Term val : myMap.get(key)) {
			    	mySize += BYTES_PER_DOUBLE + BYTES_PER_CHAR*val.getWord().length(); 
			    }
			}
			
		}
		return mySize;
	}
	
}
