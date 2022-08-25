
package GameOfThronesCharactersNetwork;

import java.io.IOException;
import java.util.Locale;

public class ListGraph {

    LinkedList[] edges;
    private int numV;
    private int numE;


    public ListGraph(int V) {
        this.numV = V;
        edges = new LinkedList[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList();
        }
    }

    public int getNumE() {
        return numE;
    }

    public int getNumV() {
        return numV;
    }

    public int hasher(String t) throws IOException {
        return ((t.hashCode() & 0x7ffffff) % edges.length);
    }


    public boolean checkIfExits(String character) throws IOException {

        int hashedValue = hasher(character.toLowerCase(Locale.ROOT));

        for (int i = 0; i < numV; i++) {
            if (edges[(hashedValue + i) % numV].nodeHolder.equals(character)) {
                return true;
            }
        }
        return false;

    }

    public boolean isConnected(String character1, String character2) throws Exception {
        Node tmp = edges[finder(character1)].first;

        if (!checkIfExits(character1) || !checkIfExits(character2)) {
            return false;
        }

        while (tmp != null) {

            if (tmp.to.equals(character2.toLowerCase(Locale.ROOT))) {
                return true;
            }
            tmp = tmp.next;

        }

        return false;
    }


    public void addEdge(String from, String to, int weight) throws IOException {


        int firstNameHashed = hasher(from);
        int secondNameHashed = hasher(to);


        if (edges[firstNameHashed].nodeHolder == null) {
            edges[firstNameHashed].insertLast(to, weight);
            edges[firstNameHashed].nodeHolder = from;

        } else {
            for (int i = 0; i < numV; i++) {

                if (edges[(firstNameHashed + i) % numV].nodeHolder == null) {
                    edges[(firstNameHashed + i) % numV].insertLast(to, weight);
                    edges[(firstNameHashed + i) % numV].nodeHolder = from;
                    break;
                }
                if (edges[(firstNameHashed + i) % numV].nodeHolder.equals(from)) {
                    edges[(firstNameHashed + i) % numV].insertLast(to, weight);
                    edges[(firstNameHashed + i) % numV].nodeHolder = from;
                    break;
                }


            }
        }
        if (edges[secondNameHashed].nodeHolder == null) {
            edges[secondNameHashed].insertLast(from, weight);
            edges[secondNameHashed].nodeHolder = to;

        } else {
            for (int i = 0; i < numV; i++) {

                if (edges[(secondNameHashed + i) % numV].nodeHolder == null) {
                    edges[(secondNameHashed + i) % numV].insertLast(from, weight);
                    edges[(secondNameHashed + i) % numV].nodeHolder = to;
                    break;
                }
                if (edges[(secondNameHashed + i) % numV].nodeHolder.equals(to)) {
                    edges[(secondNameHashed + i) % numV].insertLast(from, weight);
                    edges[(secondNameHashed + i) % numV].nodeHolder = to;
                    break;
                }


            }
        }
        numE++;
    }


    public String findMaximumConnections() {

        int max = edges[0].size;
        int maxIndex = 0;
        for (int i = 0; i < numV; i++) {
            if (edges[i].size > max) {
                max = edges[i].size;
                maxIndex = i;
            }
        }

        String s = "The " + edges[maxIndex].nodeHolder + " has maximum number of connections with size of " + edges[maxIndex].size + "\n[";
        Node tmp = edges[maxIndex].first;
        for (int i = 0; i < edges[maxIndex].size; i++) {
            if (i == edges[maxIndex].size - 1) {
                s += tmp.to + "]";
                return s;
            }
            s += tmp.to + ",";
            tmp = tmp.next;

        }

        return null;

    }


    public String printGivenCharactersConnectionsAndWeights(String character) throws Exception {

        int index = finder(character);


        String s = character + " has " + edges[index].size + " connections\n[";

        Node tmp = edges[index].first;
        while (tmp != null) {
            if (tmp.next == null) {
                s += tmp.to + " W:" + tmp.weight;
                break;
            }
            s += tmp.to + " W:" + tmp.weight + " -> ";
            tmp = tmp.next;
        }

        s += "]";

        return s;
    }

    public Integer finder(String character) throws Exception {

        Integer index;
        for (int i = 0; i < numV; i++) {
            if (edges[((hasher(character) + i) % numV)].nodeHolder.equals(character)) {
                index = ((hasher(character) + i) % numV);
                return index;
            }

        }
        throw new Exception(character + " is not in the list exception");
    }

    public Integer finder(int character) throws Exception {

        Integer index;
        for (int i = 0; i < numV; i++) {

            if (edges[((character + i) % numV)].nodeHolder.equals(edges[character].nodeHolder)) {
                index = ((character + i) % numV);
                return index;
            }

        }
        throw new Exception(character + " is not in the list exception");
    }

    public String finderString(int character) throws Exception {

        String index;
        for (int i = 0; i < numV; i++) {

            if (edges[((character + i) % numV)].nodeHolder.equals(edges[character].nodeHolder)) {
                index = edges[((character + i) % numV)].nodeHolder;
                return index;
            }

        }
        throw new Exception(character + " is not in the list exception");
    }

    public void removeEdge(String from, String to) throws Exception {
        Integer finderFrom = finder(from);
        Integer finderTo = finder(to);

        if ((finderTo != null && finderFrom != null) && edges[finderFrom].contains(to)) {
            edges[finderFrom].remove(to);
            edges[finderTo].remove(from);
        } else {
            System.out.println("Edge not found!");
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < numV; i++) {
            sb.append(i + " " + edges[i].toString() + "\n");
        }

        return sb.toString();
    }

    public LinkedList neighborsList(int from) throws Exception {
        return edges[finder(from)].clone();
    }


    public String[] neighborsArray(int from) throws Exception {

        String[] ar = new String[edges[from].size];
        ar = edges[from].toArray(ar);
        return ar;
    }

    public String[] neighborsArrayForConnectedComponents(String from) throws Exception {

        Integer finder = finder(from);
        String[] ar = new String[edges[finder].size];
        ar = edges[finder].toArray(ar);
        return ar;
    }

    public void changeWeight(String character1, String character2, int newWeight) throws Exception {
        Integer finderFrom = finder(character1);
        Integer finderTo = finder(character2);


        if (!edges[finderFrom].contains(character2) || !edges[finderTo].contains(character1)) {
            throw new Exception("Characters are not founded");
        }

        edges[finderFrom].changeWeight(character2, newWeight);
        edges[finderTo].changeWeight(character1, newWeight);


    }

    public int degree(String from) throws Exception {
        return edges[finder(from)].size;
    }


    public int maxDegree() {
        int max = edges[0].size;
        for (int i = 0; i < edges.length; i++) {

            if (edges[i].size > max) {
                max = edges[i].size;
            }
        }
        return max;
    }


}