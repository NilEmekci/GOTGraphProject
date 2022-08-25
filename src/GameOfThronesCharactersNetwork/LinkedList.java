
package GameOfThronesCharactersNetwork;



public class LinkedList {
    Node first, last;
    int size = 0;
    String nodeHolder;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
        nodeHolder = null;
    }

    public boolean contains(String to) {
        Node tmp = first;
        while (tmp != null) {
            if (tmp.to.equals(to))
                return true;
            tmp = tmp.next;
        }
        return false;
    }


    public LinkedList clone() {
        Node tmp = first;

        LinkedList linkedList = new LinkedList();
        while (tmp != null) {

            linkedList.insertNode(tmp);
            tmp = tmp.next;
        }

        return linkedList;

    }

    public String[] toArray(String[] array) {

        String[] newArray = new String[array.length];
        Node tmp = first;

        for (int i = 0; i < size; i++) {
            newArray[i] = tmp.to;
            tmp = tmp.next;
        }


        return newArray;

    }

    public Node search(String value) {

        Node tmp = first;

        while (tmp != null) {
            if (tmp.to.equals(value)) {
                return tmp;
            } else {
                tmp = tmp.next;
            }
        }
        return null;
    }

    public void remove(String value) {


        Node tmp = first;


        if (tmp == null) {
            return;
        }


        if (tmp.to.equals(value)) {
            removeFirst();
            return;
        }


        while (tmp.next != null) {
            Node tmpNext = tmp.next;
            if (tmpNext == last) {
                removeLast();
                return;
            }
            if (tmpNext.to.equals(value) && tmpNext.next != null) {
                tmp.next = tmp.next.next;
                size--;
                return;
            } else {
                tmp = tmp.next;
            }
        }


    }

    public void removeLast() {
        Node tmp = first;
        while (tmp.next.next != null) {
            tmp = tmp.next;
        }
        tmp.next = null;
        last = tmp;
        size--;
    }

    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        first = first.next;
        size--;
    }


    public void changeWeight(String to, int newWeight) {
        if (isEmpty()) {
            return;
        }
        Node tmp = search(to);
        if (tmp == null) {
            return;
        }
        int prevWeight = tmp.weight;
        tmp.weight = newWeight;
        return;
    }

    public Node[] giveElementsInArray() {

        Node tmp = first;


        Node[] array = new Node[size];

        for (int i = 0; tmp != null; i++) {

            array[i] = tmp;
            tmp = tmp.next;


        }


        return array;
    }


    // prints the list
    public String toString() {
        Node tmp = first;
        String str = "List of " + nodeHolder + " with " + size + " connected to: ";

        while (tmp != null) {
            str += tmp.to + " with weight " + tmp.weight + "->";
            tmp = tmp.next;
        }
        str += ".";
        return str;
    }

    public String displayAll() {
        Node tmp = first;
        String str = "";

        while (tmp != null) {
            str += tmp.to + "\n";
            tmp = tmp.next;
        }

        return str;
    }


    public void insertNode(Node node) {
        Node newNode = node;
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }


    public void insertFirst(String to, int weight) {
        Node newNode = new Node(to, weight);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }


    public void insertAfter(Node p, String to, int weight) {
        Node newNode = new Node(to, weight);
        if (p == null || size == 0) {

            return;
        }
        if (p == last) {
            insertLast(to, weight);
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
        size++;
    }

    public void insertLast(String to, int weight) {


        Node newNode = new Node(to, weight);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }


        size++;
    }

    public void insertFirstPart(String from, int weight) {


        Node newNode = new Node(from, weight);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }


        size++;
    }


    public boolean isEmpty() {
        return size == 0;
    }
}