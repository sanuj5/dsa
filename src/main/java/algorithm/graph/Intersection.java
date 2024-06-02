package algorithm.graph;

import java.util.*;

public class Intersection {

    class DirectedGraph {
        private final Map<String, String> adj;

        public DirectedGraph() {
            adj = new HashMap<>();
        }

        public void addEdgeTo(final String v, final String w) {
            adj.put(v,w);
        }

        public String adj(final String v) {
            return adj.get(v);
        }
    }

    public static void main(String[] args) {
        Intersection i = new Intersection();
        System.out.println(i.DoLinkedListsIntersect(null));
    }

    private String DoLinkedListsIntersect(List<String> input){
        Map<String,String> graph = new HashMap<>();
        graph.put("a","b");
        graph.put("r","s");
        graph.put("b","c");
        graph.put("x","c");
        graph.put("q","r");
        graph.put("y","x");
        graph.put("w","z");
        List<String> nodes = Arrays.asList(new String[]{"a","c","r"});
        Set<String> visited = new HashSet<>();
        boolean isInterSectionFound = false;
        boolean isCycleFound = false;
        for(String node: nodes){
            Set<String> currentVisited = new HashSet<>();
            String currentNode = node;
            while(currentNode != null){
                if(visited.contains(currentNode)){
                    isInterSectionFound = true;
                    break;
                }
                visited.add(currentNode);
                currentVisited.add(currentNode);
                currentNode = graph.get(currentNode);
                if(currentVisited.contains(null)) {
                    isCycleFound = true;
                    break;
                }
            }
            if(isInterSectionFound) return "True";
            if(isCycleFound) return "Error Thrown!";
        }
        return "False";
    }
}
