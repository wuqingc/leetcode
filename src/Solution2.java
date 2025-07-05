import java.util.*;

/**
 * @author tianjiaxuan
 * @date 2024-04-10 10:01
 */
class Solution2 {

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    public static int countOfSubstrings(String word, int k) {
        int n = word.length();
        int totalCount = 0;

        for (int start = 0; start < n; start++) {
            int consonantCount = 0;
            Set<Character> vowelsSeen = new HashSet<>();

            for (int end = start; end < n; end++) {
                char currentChar = word.charAt(end);
                if (isVowel(currentChar)) {
                    vowelsSeen.add(currentChar);
                } else if (Character.isLetter(currentChar)) {
                    consonantCount++;
                }
                if (consonantCount > k) {
                    break;
                }
                if (consonantCount == k && vowelsSeen.size() == 5) {
                    totalCount++;
                }
            }
        }

        return totalCount;
    }

    public static void main(String[] args) {
        System.out.println(countOfSubstrings("iqeaouqi",2));
    }
}