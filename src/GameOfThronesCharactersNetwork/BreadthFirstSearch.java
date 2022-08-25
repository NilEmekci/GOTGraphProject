
package GameOfThronesCharactersNetwork;


import java.io.IOException;
import java.util.LinkedList;

public class BreadthFirstSearch {


    boolean[] marked;
    String[] edgeTo;
    String[] distTo;
    String from;


    public BreadthFirstSearch(ListGraph g, String from) throws Exception {
        edgeTo = new String[g.getNumV()];
        marked = new boolean[g.getNumV()];
        distTo = new String[g.getNumV()];
        this.from = from;
        bfs(g, from);
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public String distTo(String w) throws IOException {
        return distTo[hasher(w)];
    }

    public int hasher(String t) throws IOException {

        return ((t.hashCode() & 0x7ffffff) % marked.length);

    }

    public String[] pathTo(String w, ListGraph g) throws Exception {
        String k = edgeTo[g.finder(w)];

        if (k == null) {
            throw new Exception("Character is not connected  to the source");
        }


        java.util.Stack st = new java.util.Stack();
        st.push(w);
        while (k != this.from) {

            st.push(k);
            k = edgeTo[g.finder(k)];


        }

        String[] path = new String[st.size()];
        for (int i = 0; i < path.length; i++)
            path[i] = (String) st.pop();
        return path;
    }

    public String printPathTo(ListGraph g, String w) throws Exception {


        String[] path = pathTo(w, g);

        String s = from + " -> ";
        for (int i = 0; i < path.length; i++) {

            if (i == path.length - 1) {
                s += path[i];
                break;
            }
            s += path[i] + " -> ";

        }
        return s;

    }

    public void bfs(ListGraph g, String source) throws Exception {


        Integer finderResult = g.finder(source);


        marked[finderResult] = true;
        String[] a = g.neighborsArray(finderResult);
        if (a.length == 0) {
            return;
        }

        LinkedList<String> q = new LinkedList<String>();
        q.addLast(source);
        while (!q.isEmpty()) {
            source = q.removeFirst();
            a = g.neighborsArray(g.finder(source));
            for (int i = 0; i < a.length; i++) {
                String w = a[i];
                if (!marked[g.finder(w)]) {
                    q.addLast(w);
                    marked[g.finder(w)] = true;
                    edgeTo[g.finder(w)] = source;
                    distTo[g.finder(w)] = distTo[finderResult] + 1;
                }
            }

        }

    }
}



