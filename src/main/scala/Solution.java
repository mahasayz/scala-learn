import java.util.HashSet;
import java.util.Set;

/**
 * Created by malam on 5/17/17.
 */
class Node{
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
    public int getCount() { return this.count; }
}

public class Solution {


    /**
     * @param str Search string
     * @return Set of unique characters in string
     */
    public static Set<String> uniqueChars(String str) {
        Set<String> result = new HashSet<>();

        for (char c : str.toCharArray()) {
            result.add(String.valueOf(c));
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
     * @param charset Character set
     * @return Optimized string
     */
    public static String optimize(String str, Node[] charset) {
        int start = 0;
        while(charset[(int)str.charAt(start)].getCount() > 1) {
            charset[(int)str.charAt(start)].decrementCount();
            start++;
        }
        return str.substring(start);
    }

    public static void main(String[] args) {
        String str = "CBaBaBCCe";

        Set<String> uniqueChars = uniqueChars(str);
        int uniqueCharsLength = uniqueChars.size();
        Node[] charset = new Node[256];

        // initialize node
        for (int i=0; i<256; i++) {
            charset[i] = new Node(false);
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<str.length(); i++) {
            if (!charset[(int)str.charAt(i)].getVisited()) {
                charset[(int)str.charAt(i)].setVisited(true);
                charset[(int)str.charAt(i)].inrementCount();
                count++;
            } else {
                charset[(int)str.charAt(i)].inrementCount();
            }
            if (count == uniqueCharsLength) {
                sb.append(str.charAt(i));
                String optimizedStr = optimize(sb.toString(), charset);
                System.out.println("Pattern found : " + sb.toString());
                System.out.println("Optimized pattern: " + optimizedStr);
                System.out.println("Rooms needed to visit: " + optimizedStr.length());
                break;
            }
            sb.append(str.charAt(i));
        }

    }

}
