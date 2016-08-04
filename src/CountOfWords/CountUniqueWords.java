package CountOfWords;

import java.util.*;

/**
 * The CountUniqueWords program implements an application that prints a list of
 * unique words contained in the string along with the number of occurrences of
 * each word in the string in ascending order.
 * 
 * @author Jyothi
 * 
 */
public class CountUniqueWords {

	public static void main(String[] args) {
		System.out.println("Enter the input string:");

		// Read the input sentence
		Scanner input = new Scanner(System.in);
		String s1 = input.nextLine();
		
		// Convert the input string to lower case
		String s2 = s1.toLowerCase();

		CountUniqueWords obj = new CountUniqueWords();
		obj.splitSentence(s2);

		}
	
	// HashMap hs1 holds the words and the no of occurrences.
	//HashMap hs2 holds the words and no of occurrences in descending order 
	public HashMap<String, Integer> splitSentence(String s) {
		String token = "";
		s += ",";
		int maxCounter = 0;
		
		HashMap<String, Integer> hs1 = new HashMap<String, Integer>();
		HashMap<Integer, Set> hs2 = new HashMap<Integer, Set>();
		
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) == ' ') || (s.charAt(i) == ',') || (s.charAt(i) == '.') || (s.charAt(i) == ';')
					|| (s.charAt(i) == '!') || (s.charAt(i) == '?')) {
				if (token != "") {
					if (hs1.containsKey(token)) {

						Set<String> tempSet = hs2.get(hs1.get(token));
						tempSet.remove(token);
						hs2.remove(hs1.get(token));
						hs2.put(hs1.get(token), tempSet);

						if (hs2.containsKey(hs1.get(token) + 1)) {
							Set<String> tempSet1 = hs2.get(hs1.get(token) + 1);
							tempSet1.add(token);
							hs2.put(hs1.get(token) + 1, tempSet1);
						} else {
							Set<String> tempSet2 = new HashSet<String>();
							tempSet2.add(token);
							hs2.put(hs1.get(token) + 1, tempSet2);
						}

						hs1.put(token, hs1.get(token) + 1);
						if (maxCounter < hs1.get(token)) {
							maxCounter = hs1.get(token);
						}
					} else {
						hs1.put(token, 1);
						if (maxCounter == 0) {
							maxCounter = 1;
						}
						if (hs2.containsKey(1)) {
							Set<String> tempSet = hs2.get(1);
							tempSet.add(token);
							hs2.put(1, tempSet);
						} else {
							Set<String> tempSet = new HashSet<String>();
							tempSet.add(token);
							hs2.put(1, tempSet);
						}
					}
					token = "";
				}

			} else {
				token += s.charAt(i);
			}
		}

		for (int k = maxCounter; k > 0; k--) {
			if (hs2.containsKey(k)) {
				Iterator iterator = hs2.get(k).iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();

					System.out.println(key + " " + k);
				}
			}
		}

		return hs1;

	}
}