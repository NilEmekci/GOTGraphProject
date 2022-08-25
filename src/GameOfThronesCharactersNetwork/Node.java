
package GameOfThronesCharactersNetwork;


public class Node {

    Node next;
    String to;
    int weight;


    public Node(String to, int weight) {
        next = null;
        this.to = to;
        this.weight = weight;
    }

}
