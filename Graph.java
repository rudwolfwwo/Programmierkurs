import java.util.ArrayList;

public class Graph {
    private final int size;
    private boolean[][] edges;
    public Graph(int size) {
        this.size = size;
         edges = new boolean [size] [size];
    }
    public void addEdges(int node1, int node2) {
        edges[node1] [node2] = true;
        edges[node2] [node1] = true;
    }
    public ArrayList<Integer> getPath(ArrayList<Integer> path, int to) {
        //if (path.get(path.size()-1) == to) return path;
        if (!path.contains(to) && edges[path.get(path.size()-1)][to]) {
            path.add(to);
            return path;
        }
        for (int i = 0; i < size;i++){
            if (!path.contains(i) && edges[path.get(path.size()-1)][i]) {
                path.add(i);
                if (getPath(path,to).size() != 0) return path;
                path.remove(path.size()-1);
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.addEdges(1, 2);
        g.addEdges(2, 3);
        g.addEdges(3, 1);
        g.addEdges(2, 4);
        g.addEdges(5, 1);
        g.addEdges(4, 5);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        g.getPath(arr,3).forEach(System.out::println);
    }
}
