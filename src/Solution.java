import java.util.*;

/**
 * @author tianjiaxuan
 * @date 2024-04-10 10:01
 *
 */
class Solution {
    public static int countOfSubstrings(String word, int k) {
        int n = word.length();
        int[] consonantPrefixSum = new int[n + 1];
        Map<Integer, Integer> freqMap = new HashMap<>();
        int[] lastVowelPositions = new int[5]; // For 'a', 'e', 'i', 'o', 'u'
        for (int i = 0; i < 5; i++) {
            lastVowelPositions[i] = -1;
        }

        freqMap.put(0, 1); // Initialize with prefix sum 0
        int l = 0;
        int totalCount = 0;

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            consonantPrefixSum[i + 1] = consonantPrefixSum[i] + (isConsonant(c) ? 1 : 0);

            if (isVowel(c)) {
                lastVowelPositions[vowelIndex(c)] = i;
            }

            int oldL = l;
            l = getMin(lastVowelPositions);

            // Remove counts of positions before the new 'l' from the frequency map
            for (int s = oldL; s < l; s++) {
                int prefixSum = consonantPrefixSum[s];
                freqMap.put(prefixSum, freqMap.get(prefixSum) - 1);
                if (freqMap.get(prefixSum) == 0) {
                    freqMap.remove(prefixSum);
                }
            }

            int targetSum = consonantPrefixSum[i + 1] - k;
            totalCount += freqMap.getOrDefault(targetSum, 0);

            // Add current prefix sum to the frequency map
            freqMap.put(consonantPrefixSum[i + 1], freqMap.getOrDefault(consonantPrefixSum[i + 1], 0) + 1);
        }

        return totalCount;
    }

    private static boolean isConsonant(char c) {
        return !isVowel(c);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static int vowelIndex(char c) {
        switch (c) {
            case 'a':
                return 0;
            case 'e':
                return 1;
            case 'i':
                return 2;
            case 'o':
                return 3;
            case 'u':
                return 4;
            default:
                return -1; // Not a vowel
        }
    }

    private static int getMin(int[] positions) {
        int min = Integer.MAX_VALUE;
        for (int pos : positions) {
            if (pos == -1) {
                return -1; // Not all vowels have been seen yet
            }
            min = Math.min(min, pos);
        }
        return min + 1; // Convert from index to position
    }

    public static void main(String[] args) {
        System.out.println(countOfSubstrings("iqeaouqi",2));
        System.out.println(countOfSubstrings("aouiei",0));
    }

}