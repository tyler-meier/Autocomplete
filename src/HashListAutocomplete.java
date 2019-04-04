import java.util.*;

public class HashListAutocomplete implements Autocompletor {
	
	private static final int MAX_PREFIX = 10;
	private Map<String, List<Term>> myMap;
	private int mySize;
	
	public HashListAutocomplete(String[] terms, double[] weights) {
		
		initialize(terms, weights);
	}

	@Override
	public List<Term> topMatches(String prefix, int k) {
		
		return null;
	}

	@Override
	public void initialize(String[] terms, double[] weights) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sizeInBytes() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
