package algorithm.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionQuickUnion {

    Map<String, String> parentMap = new HashMap<>();

    public static void main(String[] args) {
        IntersectionQuickUnion i = new IntersectionQuickUnion();
        i.DoLinkedListsIntersect(null);
    }

    private String DoLinkedListsIntersect(List<String> input){
        connect("a","b");
        connect("r","s");
        connect("b","c");
        connect("x","c");
        connect("c","a");
        connect("q","r");
        connect("y","x");
        connect("w","z");
        System.out.println(isConnected("a","c"));
        System.out.println(isConnected("w","a"));
        return null;
    }

    private void connect(String a, String b){
        String x = root(a);
        String y = root(b);
        String root = !y.equals(b) ? y : x;
        parentMap.put(y,root);
        parentMap.put(a,root);
    }

    private String root(String a){
        String temp;
        do{
            temp = a;
            a = parentMap.get(a);
        }
        while(a != null && !a.equals(temp));
        return temp;
    }
    
    private boolean isConnected(String a, String b){
        return root(a).equals(root(b));
    }
}
