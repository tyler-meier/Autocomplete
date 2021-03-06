
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 * @author Owen Astrachan, revised for Java 8-11, toString added
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		// TODO: Complete Term constructor, throw exceptions
	
		myWord = word;
		myWeight = weight;
		if (weight < 0) {
			throw new IllegalArgumentException("negative weight "+weight);
		}
		if (word == null) {
			throw new NullPointerException("null word "+word);
		}
	}
	
	/**
	 * Default compare is by word, no weight involved
	 * @return this.getWord().compareTo(that.getWord())
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter for Term's word
	 * @return this Term's word
	 */
	public String getWord() {
		return myWord;
	}

	/**
	 * Getter for Term's weight
	 * @return this Term's weight
	 */
	public double getWeight() {
		return myWeight;
	}

	/**
	 * @return (word,weight) for this Term
	 */
	@Override
	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	/**
	 * Use default compareTo, so only word for equality
	 * @return true if this.getWord().equal(o.getWord())
	 */
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. 
	 * This Comparator required for assignment.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

		public PrefixOrder(int r) {
			this.myPrefixSize = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {
			int vlen = v.getWord().length();
			int wlen = w.getWord().length();
			int fin = myPrefixSize;
			
			if (vlen < fin) fin  = vlen;	
			if (wlen < fin) fin = wlen;
			
			for (int k = 0; k < fin; k++) {
				char vind = v.getWord().charAt(k);
				char wind = w.getWord().charAt(k);
				
				if (vind < wind) return -13;
				if (vind > wind) return 13;			
			}
			
			if (vlen < myPrefixSize) {
				if (vlen < wlen) return -13;
				if (vlen > wlen) return 13;
			}
			
			if (wlen < myPrefixSize) {
				if (wlen < vlen) return 13;
				if (wlen > vlen) return -13;
			}	
			
			return 0;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This comparator required for assignment.
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			
			if (v.getWeight() < w.getWeight()) return 13;
			if (v.getWeight() > w.getWeight()) return -13;
			
			return 0;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This comparator required for assignment.
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			
			if (v.getWeight() < w.getWeight()) return -13;			
			if (v.getWeight() > w.getWeight()) return 13;

			return 0;
		}
	}
}
