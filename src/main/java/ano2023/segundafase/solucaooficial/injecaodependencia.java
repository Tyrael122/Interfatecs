package ano2023.segundafase.solucaooficial;

import util.TestCaseReader;

import java.util.*;

public class injecaodependencia {

    static Map<String, List<String>> adjVertices = new HashMap<>();
    static Set<String> visited = new HashSet<>();
    static Set<String> stack = new HashSet<>();

    public static String main(TestCaseReader reader) {
        int n = reader.nextInt();

        for (int i = 0; i < n; i++) {
            String a = reader.nextString();
            String b = reader.nextString();

            adjVertices.putIfAbsent(a, new ArrayList<>());
            adjVertices.putIfAbsent(b, new ArrayList<>());
            adjVertices.get(a).add(b);
        }

        boolean hasCycle = false;
        for (String vertex : adjVertices.keySet()) {
            if (hasCycleUtil(vertex)) {
                hasCycle = true;
                break;
            }
        }

        return hasCycle ? "usar injecao tardia" : "ok";
    }

    private static boolean hasCycleUtil(String vertex) {
        if (stack.contains(vertex))
            return true;
        if (visited.contains(vertex))
            return false;

        visited.add(vertex);
        stack.add(vertex);

        for (String neighbor : adjVertices.get(vertex)) {
            if (hasCycleUtil(neighbor)) {
                return true;
            }
        }

        stack.remove(vertex);
        return false;
    }
}
