package ano2023.segundafase;

import util.TestCaseReader;

import java.util.ArrayList;
import java.util.List;

public class InjecaoDependencia {
    public static String main(TestCaseReader reader) {
        int numeroDeDependencias = reader.nextInt();

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numeroDeDependencias; i++) {
            String nodeString = reader.nextString();
            String sonString = reader.nextString();

            Node node = getNodeFromLetter(nodeString, nodes);
            Node son = getNodeFromLetter(sonString, nodes);

            node.addSon(son);
        }

        boolean[] nodesPercorridos = new boolean[numeroDeDependencias * 2];

        List<Node> nosParaPercorrer = new ArrayList<>();
        nosParaPercorrer.add(nodes.getFirst());

        for (int i = 0; i < nosParaPercorrer.size(); i++) {
            Node node = nosParaPercorrer.get(i);
            int nodeIndex = getNodeIndexFromLetter(node.getValue());

            if (nodesPercorridos[nodeIndex]) {
                return "usar injecao tardia";
            }

            nodesPercorridos[nodeIndex] = true;

            nosParaPercorrer.addAll(node.getFilhos());
        }

        return "ok";
    }

    private static Node getNodeFromLetter(String nodeString, List<Node> nodes) {
        List<Node> nodesMatched = nodes.stream().filter(node -> nodeString.equals(node.getValue())).toList();

        if (!nodesMatched.isEmpty()) return nodesMatched.getFirst();

        Node node = new Node(nodeString);
        nodes.add(node);

        return node;
    }

    private static int getNodeIndexFromLetter(String letter) {
        return letter.charAt(0) - 'A';
    }
}

class Node {
    private final String value;
    private final List<Node> filhos = new ArrayList<>();

    Node(String value) {
        this.value = value;
    }

    public void addSon(Node node) {
        filhos.add(node);
    }

    public String getValue() {
        return value;
    }

    public List<Node> getFilhos() {
        return filhos;
    }
}