import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private int[][] edges;
    private ArrayList<Node> nodes = new ArrayList<>();
    public Graph(int number_of_nodes) {
        edges = new int[number_of_nodes][number_of_nodes];
        for (int i = 1; i < number_of_nodes+1; i++) {
            nodes.add(new Node(i));
        }
        //Arrays.fill(edges,0);
    }
    public ArrayList<Node> getNodes() {
        return nodes;
    }
    public Node getNodeWithID(int ID) {
        return nodes.get(ID-1);
    }
    public void addDirectedEdge(Node u,Node v) {
        edges[nodes.indexOf(u)][nodes.indexOf(v)] = 1;
    }
    public ArrayList<Node> getNeighborsOf(Node u) {
        ArrayList<Node> arr = new ArrayList<>();
        for (int i = 0; i < edges.length;i++) {
            if (edges[nodes.indexOf(u)][i] == 1)
                arr.add(nodes.get(i));
        }
        return arr;
    }
    public static void depth_first_search(Graph g,Node start) {
        Stack<Node> stack = new Stack<>();
        stack.push(start);
        System.out.println("Der Knoten " + start.getID() + " wurde gefunden!");
        start.setColor(Color.GRAY);

        while (!stack.empty()) {
            Node n = stack.peek();
            ArrayList<Node> neu = g.getNeighborsOf(n);
            if (neu.size() == 0 || neu.stream().noneMatch(i -> i.getColor() != Color.BLACK)) {
                n.setColor(Color.BLACK);
                System.out.println("Der Knoten " + n.getID() + " wurde schwarz gefärbt!");
                stack.pop();
            } else {
                for (Node i : neu) {
                    if (i.getColor() == Color.WHITE) {
                        System.out.println("Der Knoten " + i.getID() + " wurde gefunden!");
                        i.setColor(Color.GRAY);
                        stack.push(i);
                        break;
                    }
                }
            }
        }
    }

    public static void depth_first_searc(Graph g, Node start) {
        Stack<Node> active = new Stack<Node>();
        start.setColor(Color.GRAY);
        active.push(start);
        while(!active.empty()) {
            Node current = active.pop();
            ArrayList<Node> neighbors = g.getNeighborsOf(current);
            Node whiteNode = null;
            for(Node neighbor: neighbors) {
                if(neighbor.isWhite()) {
                    whiteNode = neighbor;
                    break;
                }
            }
            if(whiteNode == null) {
                current.setColor(Color.BLACK);
                System.out.println(current.getID() + " wurde schwarz gefärbt ");
            }
            else {
                whiteNode.setColor(Color.GRAY);
                System.out.println(whiteNode.getID() + " wurde grau gefärbt");
                active.push(current);
                active.push(whiteNode);
            }
        }
    }

    public static void breitensuche(Graph g, Node start) {
        start.setColor(Color.GRAY);
        System.out.println("Der Knoten " + start.getID() + " wurde grau gefärbt!");

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(start);
        while (queue.size() != 0) {
               Node current = queue.removeLast();
               for (Node n : g.getNeighborsOf(current)) {
                   if (n.getColor() == Color.WHITE) {
                       queue.addFirst(n);
                       n.setColor(Color.GRAY);
                       System.out.println("Der Knoten " + n.getID() + " wurde grau gefärbt!");
                   }
               }
               current.setColor(Color.BLACK);
            System.out.println("Der Knoten " + current.getID() + " wurde schwarz gefärbt!");
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addDirectedEdge(g.getNodeWithID(1),g.getNodeWithID(2));
        g.addDirectedEdge(g.getNodeWithID(2),g.getNodeWithID(4));
        g.addDirectedEdge(g.getNodeWithID(2),g.getNodeWithID(5));
        g.addDirectedEdge(g.getNodeWithID(5),g.getNodeWithID(7));
        g.addDirectedEdge(g.getNodeWithID(1),g.getNodeWithID(3));
        g.addDirectedEdge(g.getNodeWithID(3),g.getNodeWithID(6));
        g.addDirectedEdge(g.getNodeWithID(6),g.getNodeWithID(7));
        g.breitensuche(g,g.getNodeWithID(1));
    }
}
