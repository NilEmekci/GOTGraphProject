
package GameOfThronesCharactersNetwork;






public class ConnectedComponents {
    private boolean marked[];
    private int count;
    private int[] id;


    public ConnectedComponents(ListGraph listGraph) throws Exception {

        marked = new boolean[listGraph.getNumV()];
        count = 0;
        id = new int[listGraph.getNumV()];
        for (int v = 0; v < listGraph.getNumV(); v++) {
            if (!marked[v]) {
                dfs(listGraph, listGraph.finderString(v));
                count++;
            }
        }

    }

    public String printComponents(ListGraph listGraph) {


        int max = 0;
        for (int i = 0; i < listGraph.getNumV(); i++) {
            if (id[i] > max) {
                max = id[i];
            }
        }

        String s = "";
        int counter = 0;
        s += "Group " + counter + " has [";
        for (int i = 0; i < listGraph.getNumV(); i++) {


            if ((id[i] == counter)) {
                s += listGraph.edges[i].nodeHolder + ",";
            }
            if (i == listGraph.getNumV() - 1) {
                i = 0;
                counter++;
                if (counter == max + 1) {
                    s += "]";
                    return s;
                }
                s += "]\nGroup " + counter + "has [";
            }


        }

        return "empty list";
    }


    public void dfs(ListGraph graph, String v) throws Exception {
        Integer finder = graph.finder(v);
        marked[finder] = true;
        id[finder] = count;

        String[] a = graph.neighborsArray(finder);
        for (int i = 0; i < a.length; i++) {

            if (!marked[graph.finder(a[i])]) {
                dfs(graph, a[graph.finder(i)]);
            }
        }
    }


}




