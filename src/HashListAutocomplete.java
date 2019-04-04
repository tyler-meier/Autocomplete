import java.util.*;

public class HashListAutocomplete implements Autocompletor {
	
	private static final int MAX_PREFIX = 10;
	private Map<String, List<Term>> myMap;
	private int mySize;
	
	public HashListAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}

		if (terms.length != weights.length) {
			throw new IllegalArgumentException("terms and weights are not the same length");
		}
		initialize(terms, weights);
	}

	@Override
	public List<Term> topMatches(String prefix, int k) {
		
		return null;
	}

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

	@Override
	public int sizeInBytes() {	
		return mySize;
	}
}
