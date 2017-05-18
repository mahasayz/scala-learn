import java.util.*;

/**
 * Created by malam on 5/17/17.
 */

public class Solution {

    static class Node{
        private boolean visited;
        private int count;

        public Node(boolean visited) {
            this.visited = visited;
            this.count = 0;
        }

        public void setVisited(boolean b) { this.visited = b; }
        public boolean getVisited() { return this.visited; }
        public void inrementCount() { this.count++; }
        public void decrementCount() { this.count--; }
        public void resetCount() { this.count = 0; }
        public int getCount() { return this.count; }
    }

    /**
     * @param str Search string
     * @return Set of unique characters in string
     */
    public static Map<String, Node> uniqueChars(String str) {
        Map<String, Node> result = new HashMap<>();

        for (char c : str.toCharArray()) {
            result.put(String.valueOf(c), new Node(false));
        }

        return result;
    }

    /**
     * Here the idea is to further optimize the pattern found
     * e.g. Pattern found = aaBCDae
     * >> first character 'a' exists in remaining pattern, remove 'a'
     * Pattern = aBCDae
     * >> first character 'a' still exists in remaining pattern, remove 'a'
     * Pattern = BCDae
     * >> first character 'B' only appears once, so stop and return pattern
     * @param str Pattern found
     * @param uniqueChars Character set
     * @return Optimized string
     */
    public static String optimize(String str, Map<String, Node> uniqueChars) {
        int start = 0;
        while(uniqueChars.get(String.valueOf(str.charAt(start))).getCount() > 1) {
            uniqueChars.get(String.valueOf(str.charAt(start))).decrementCount();
            start++;
        }
        return str.substring(start);
    }

    public static void findMinRooms(String str) {
        Map<String, Node> uniqueChars = uniqueChars(str);
        int uniqueCharsLength = uniqueChars.keySet().size();

        int count = 0;
        int start = 0;
        StringBuilder sb = new StringBuilder();
        List<String> patterns = new ArrayList<>();
        for (int i=0; i<str.length(); i++) {
            if (!uniqueChars.get(String.valueOf(str.charAt(i))).getVisited()) {
                uniqueChars.get(String.valueOf(str.charAt(i))).setVisited(true);
                uniqueChars.get(String.valueOf(str.charAt(i))).inrementCount();
                count++;
            } else {
                uniqueChars.get(String.valueOf(str.charAt(i))).inrementCount();
            }
            if (count == uniqueCharsLength) {
                sb.append(str.charAt(i));
                String optimizedStr = optimize(sb.toString(), uniqueChars);
//                System.out.println("Pattern found : " + sb.toString());
//                System.out.println("Optimized pattern: " + optimizedStr);
//                System.out.println("Rooms needed to visit: " + optimizedStr.length());
                int skip = sb.toString().indexOf(optimizedStr);
                start += (skip + 1);

                patterns.add(optimizedStr);
                if (optimizedStr.length() == uniqueCharsLength)
                    break;
                else
                {
                    i = start;
                    sb.setLength(0);    //clearing sb
                    count = 0;
                    for (Node n : uniqueChars.values()) {
                        n.resetCount();
                        n.setVisited(false);
                    }
                    continue;
                }
            }
            sb.append(str.charAt(i));
        }

        int min = Integer.MAX_VALUE;
        for (String pattern : patterns) {
            if (pattern.length() < min)
                min = pattern.length();
        }

        System.out.println(min);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = Integer.parseInt(scanner.nextLine());
            String str = scanner.nextLine();
            findMinRooms(str);
        }

    }

}
