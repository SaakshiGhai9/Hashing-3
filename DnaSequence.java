// Time Complexity : Computing hash for 1 st substring O(10) , Sliding and updating hash O(n). SO total is O(n)
// Space complexity : Hashset space O(n)
import java.util.*;

public class DnaSequence {
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return result; // No 10-letter substring possible
        }

        Map<Character, Integer> charToValue = Map.of(
                'A', 0,
                'C', 1,
                'G', 2,
                'T', 3
        );

        int base = 4; // Base-4 encoding for nucleotides
        int length = 10;
        int hash = 0;
        int maxBasePower = (int) Math.pow(base, length - 1); // Base^(length-1)

        Set<Integer> seenHashes = new HashSet<>();
        Set<String> addedSubstrings = new HashSet<>();

        // Compute the initial hash for the first 10 characters
        for (int i = 0; i < length; i++) {
            hash = hash * base + charToValue.get(s.charAt(i));
        }
        seenHashes.add(hash);

        // Slide over the string
        for (int i = length; i < s.length(); i++) {
            // Rolling hash: remove the influence of the leftmost character and add the new one
            hash = ( hash - charToValue.get(s.charAt(i - length)) * maxBasePower) * base + charToValue.get(s.charAt(i));

            // Check if hash is seen
            if (seenHashes.contains(hash)) {
                String substring = s.substring(i - length + 1, i + 1);
                if (!addedSubstrings.contains(substring)) {
                    result.add(substring);
                    addedSubstrings.add(substring); // Avoid duplicate substrings in the result
                }
            } else {
                seenHashes.add(hash);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DnaSequence solution = new DnaSequence();
        String dna1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(solution.findRepeatedDnaSequences(dna1));
        // Output: ["AAAAACCCCC", "CCCCCAAAAA"]

        String dna2 = "AAAAAAAAAAAAA";
        System.out.println(solution.findRepeatedDnaSequences(dna2));
        // Output: ["AAAAAAAAAA"]
    }
}
