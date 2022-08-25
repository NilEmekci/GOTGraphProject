
package GameOfThronesCharactersNetwork;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;

public class NetworkComponents {
    ListGraph lg = new ListGraph(counterForVertexes());

    public NetworkComponents() throws IOException {
    }

    public int counter() throws IOException {

        FileReader fileReader = new FileReader("got-edges.csv");
        BufferedReader bf = new BufferedReader(fileReader);


        int counter = 0;
        while (bf.readLine() != null) {
            counter++;
        }
        bf.close();
        fileReader.close();

        return counter;
    }


    public int counterForVertexes() throws IOException {

        String fileName = "got-edges.csv";

        FileReader fr = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(fr);

        LinkedList<String> nameList = new LinkedList();
        bf.readLine();

        int i = 0;
        for (int j = 0; j < counter() - 1; j++) {
            String line = bf.readLine().toLowerCase(Locale.ROOT);
            String[] array = line.split(",");
            String firstName = array[0];
            String secondName = array[1];


            if ((firstName.contains("source") && secondName.contains("target"))) {
                continue;
            }

            if ((!nameList.contains(firstName))) {
                nameList.add(firstName);
            }
            if (!nameList.contains(secondName)) {
                nameList.add(secondName);
            }
        }


        return nameList.size();


    }

    public String printClosestCharacters(String toCharacter, int threShold) throws Exception {

        if (!lg.checkIfExits(toCharacter)) {
            return "This character not exist";
        }
        String s = toCharacter + " is farther with [";

        Node tmp = lg.edges[lg.finder(toCharacter)].first;


        String[] characters = new String[lg.edges[lg.finder(toCharacter)].size];
        int count = 0;
        while (tmp != null) {

            if (tmp.weight <= threShold) {
                characters[count] = tmp.to;
                count++;
            }
            tmp = tmp.next;
        }


        for (int i = 0; i < count; i++) {

            if (i == count - 1) {
                s += characters[i];
            } else {
                s += characters[i] + ",";
            }


        }
        s += "] Characters\n";

        return s;
    }


    public String printFartherCharacters(String toCharacter, int threShold) throws Exception {

        if (!lg.checkIfExits(toCharacter)) {
            return "This character not exist";
        }


        String s = toCharacter + " is close with [";


        Node tmp = lg.edges[lg.finder(toCharacter)].first;


        String[] characters = new String[lg.edges[lg.finder(toCharacter)].size];
        int count = 0;
        while (tmp != null) {

            if (tmp.weight >= threShold) {
                characters[count] = tmp.to;
                count++;
            }
            tmp = tmp.next;
        }


        for (int i = 0; i < count; i++) {

            if (i == count - 1) {
                s += characters[i];
            } else {
                s += characters[i] + ",";
            }


        }
        s += "] Characters\n";

        return s;
    }


    public boolean isConnected(String character1, String character2) throws Exception {

        return lg.isConnected(character1, character2);
    }


    public void reader() throws Exception {



        FileReader fileReader = new FileReader("got-edges.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String s = bufferedReader.readLine();

        for (int i = 0; i < counter() - 1; i++) {
            String[] strArray = bufferedReader.readLine().split(",");

            lg.addEdge(strArray[0].toLowerCase(Locale.ROOT), strArray[1].toLowerCase(Locale.ROOT), Integer.parseInt(strArray[2]));
        }

    }

    public void changeWeightsOfCharacters(String character1, String character2, int newWeight) throws Exception {

        lg.changeWeight(character1, character2, newWeight);

    }

    public void breadthFirstSearchShortestPart(String sourceName, String destinationName) throws Exception {

        BreadthFirstSearch bf = new BreadthFirstSearch(lg, sourceName.toLowerCase(Locale.ROOT));

        System.out.println(bf.printPathTo(lg, destinationName.toLowerCase(Locale.ROOT)));

    }

    public void removeConnections(String character1, String character2) throws Exception {

        lg.removeEdge(character1, character2);
        System.out.println("deleted");


    }

    public String printTheLargest() {

        return lg.findMaximumConnections();

    }

    public String printTheConnectionsOfGivenCharacters(String character) throws Exception {

        return (lg.printGivenCharactersConnectionsAndWeights(character));


    }

    public void printDifferentConponents() throws Exception {

        ConnectedComponents connectedComponents = new ConnectedComponents(lg);
        System.out.println(connectedComponents.printComponents(lg));

    }


    public int hasher(String t) throws IOException {
        return ((t.hashCode() & 0x7ffffff) % counterForVertexes());
    }
}


