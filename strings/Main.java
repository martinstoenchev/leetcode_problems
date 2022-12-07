import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Main {

    public static String reverseWords(String S) {
        String[] words = S.split("\\.");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i > 0; i--) {
            sb.append(words[i]).append('.');
        }
        sb.append(words[0]);

        return sb.toString();
    }

    private static List<String> findPermutations(String S) {
        if (S.length() == 1) {
            return new ArrayList<>(List.of(S));
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            String part1s = S.substring(0, i);
            String part2s = S.substring(i + 1);
            char s = S.charAt(i);
            List<String> permutations = find_permutation(part1s + part2s);
            for (String perm : permutations) {
                result.add(s + perm);
            }
        }

        return result;
    }

    public static List<String> find_permutation(String S) {
        Set<String> set = Set.copyOf(findPermutations(S));
        List<String> result = new ArrayList<>(List.copyOf(set));
        Collections.sort(result);
        return result;
    }

    public static String reverseWord(String str) {
        char[] chars = str.toCharArray();
        int n = str.length();

        for (int i = 0; i < n / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[n - 1 - i];
            chars[n - 1 - i] = tmp;
        }

        return String.valueOf(chars);
    }

    private static boolean hasDuplicates(String S) {
        char[] chars = S.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return true;
            }
        }

        return false;
    }

    public static String recursivelyRemoveDuplicates(String S) {
        if (!hasDuplicates(S) || S.length() == 0 || S.length() == 1) {
            return S;
        }

        int k;
        char[] chars = S.toCharArray();
        int n = chars.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                k = i;
                while (k < n - 1 && chars[k] == chars[k + 1]) {
                    k++;
                }
                i = k;
            } else {
                sb.append(chars[i]);
            }
        }
        if (chars[n - 1] == chars[n - 2]) {
            return recursivelyRemoveDuplicates(sb.toString());
        }

        return recursivelyRemoveDuplicates(sb.append(chars[n - 1]).toString());
    }

    public static boolean isRotated(String str1, String str2) {
        String part1 = str1.substring(0, 2);
        String part2 = str1.substring(2);

        if (str2.equals(part2 + part1)) {
            return true;
        }

        part1 = str1.substring(0, str1.length() - 2);
        part2 = str1.substring(str1.length() - 2);

        return str2.equals(part2 + part1);
    }

    private static int tableValues(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default  -> 0;
        };
    }

    public static int romanToDecimal(String str) {
        char[] chars = str.toCharArray();
        int sum = 0;
        int n = chars.length;
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = tableValues(chars[i]);
        }

        for (int i = 0; i < n - 1; i++) {
            if (numbers[i] < numbers[i + 1]) {
                sum -= numbers[i];
            } else {
                sum += numbers[i];
            }
        }

        sum += numbers[n - 1];

        return sum;
    }

    public static boolean isAnagram(String a, String b) {
        int[] lettersA = new int[26];
        int[] lettersB = new int[26];

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        for (char aChar : aChars) {
            lettersA[aChar - 97]++;
        }

        for (char bChar : bChars) {
            lettersB[bChar - 97]++;
        }

        for (int i = 0; i < 26; i++) {
            if (lettersA[i] != lettersB[i]) {
                return false;
            }
        }

        return true;
    }

    public static String removeDuplicates(String S) {
        int[] letters = new int[26];
        StringBuilder sb = new StringBuilder();
        char c;

        for (int i = 0; i < S.length(); i++) {
            c = S.charAt(i);
            if (letters[c - 97] == 0) {
                letters[c - 97]++;
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static boolean isPalindrome(String S) {
        String reversed = reverseWord(S);
        return S.equals(reversed);
    }

    public static int countMinimumCharactersToAddAsPrefix(String str) {
        if (isPalindrome(str)) {
            return 0;
        }

        String reversed = reverseWord(str);
        char[] strChars = str.toCharArray();
        int n = str.length();

        for (int i = 0; i < strChars.length; i++) {
            if (str.substring(i).equals(reversed.substring(0, n - i))) {
                return i;
            }
        }

        return n - 1;
    }

    private static int countMinRec(char[] str, int l, int h) {
        if (l >= h) {
            return 0;
        }

        if (str[l] == str[h]) {
            return countMinRec(str, l + 1, h - 1);
        }

        return Math.min(countMinRec(str, l + 1, h), countMinRec(str, l, h - 1)) + 1;
    }

    public static int countMinSlowly(String str) {
        return countMinRec(str.toCharArray(), 0, str.length() - 1);
    }

    public static int countMin(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();

        int[][] dyn = new int[n][n];

        for (int i = 1; i < n; i++) {
            for (int l = 0, h = i; h < n; l++, h++) {
                dyn[l][h] = chars[l] == chars[h] ? dyn[l + 1][h - 1] : Integer.min(dyn[l + 1][h], dyn[l][h - 1]) + 1;
            }
        }

        return dyn[0][n - 1];
    }

    public static int longestSubstrDistinctChars(String S) {
        int[] indexes = new int[26];
        char[] chars = S.toCharArray();

        int length = 0, maxLength = 0;

        for (int i = 0; i < 26; i++) {
            indexes[i] = -1;
        }

        for (int i = 0; i < S.length(); i++) {
            if (indexes[chars[i] - 97] == -1) {
                indexes[chars[i] - 97] = i;
                length++;
            } else {
                maxLength = Integer.max(maxLength, length);
                i = indexes[chars[i] - 97];
                length = 0;
                for (int j = 0; j < 26; j++) {
                    indexes[j] = -1;
                }
            }
        }

        maxLength = Integer.max(maxLength, length);

        return maxLength;
    }

    private static int getDigit(char c) {
        switch (c) {
            case '0':  return 0;
            case '1':  return 1;
            case '2':  return 2;
            case '3':  return 3;
            case '4':  return 4;
            case '5':  return 5;
            case '6':  return 6;
            case '7':  return 7;
            case '8':  return 8;
            default: return 9;
        }
    }

    public static int atoi(String str) {
        int k = 0;
        boolean negative = false;

        if (str.charAt(0) == '-') {
            negative = true;
            k = 1;
        }

        int result = 0;

        for (int i = k; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('0' <= c && c <= '9') {
                result = result * 10 + getDigit(c);
            } else {
                return -1;
            }
        }

        if (negative) {
            return -result;
        }
        return result;
    }

    public static int strstr(String s, String x) {
        for (int i = 0; i < s.length(); i++) {
            if (s.startsWith(x, i)) {
                return i;
            }
        }

        return -1;
    }

    public static String longestCommonPrefix(String[] arr, int n){
        String minLengthWord = "";
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i].length() < minLength) {
                minLength = arr[i].length();
                minLengthWord = arr[i];
            }
        }

        int i;

        while (!minLengthWord.equals("")) {
            for (i = 0; i < n; i++) {
                if (!arr[i].startsWith(minLengthWord)) {
                    break;
                }
            }

            if (i < n) {
                minLengthWord = minLengthWord.substring(0, minLength - 1);
                minLength--;
            } else {
                return minLengthWord;
            }
        }

        return "-1";
    }

    public static void main(String[] args) {

        /*String S = "i.like.this.program.very.much";
        System.out.println(Main.reverseWords(S));*/

        // System.out.println(reverseWord("1234"));

        // System.out.println(romanToDecimal("CMXVI"));

        // System.out.println(isAnagram("geeksforgeeks", "forgeeksgeeks"));

        // System.out.println(removeDuplicates("zvvov"));

        //System.out.println(countMin("geeks"));

        // System.out.println(longestSubstrDistinctChars("aewergrththy"));

        // System.out.println(atoi("-10-23"));

        String[] arr = {"geeksforgeeks", "geeks", "geek", "geezer"};
        //String[] arr = {"hello", "world"};
        System.out.println(longestCommonPrefix(arr, arr.length));
    }

}
