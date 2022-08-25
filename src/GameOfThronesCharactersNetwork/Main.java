
package GameOfThronesCharactersNetwork;



import java.util.Locale;
import java.util.Scanner;

public class Main {


    public static NetworkComponents networkComponents;

    public static void main(String[] args) throws Exception {

     

        networkComponents = new NetworkComponents();
        Scanner input = new Scanner(System.in);

        networkComponents.reader();


        System.out.println("\nHello Welcome to Game Of Thrones Network Project\n\n" +
                " Press 1) to Print Closest Characters\n" +
                " Press 2) to Print Farther Characters\n" +
                " Press 3) to Control Characters Connected or Not \n" +
                " Press 4) to Remove connection between Characters\n" +
                " Press 5) to See shortest path between two characters\n" +
                " Press 6) to Change weights of the nodes\n" +
                " Press 7) to Print Components of the lists\n" +
                " Press 8) to Find maximum number of connections\n" +
                " Press 9) to Print connections of the weights and characters for the given character\n" +
                " Press 0) to Exit from system");
        String option = input.nextLine();


        while (!option.equals("0")) {


            switch (option) {


                case "1":
                    System.out.println("Please Enter name");
                    String name = input.nextLine();
                    System.out.println("Please Enter threshot value");
                    String threshot = input.nextLine();
                    System.out.println(networkComponents.printClosestCharacters(name.toLowerCase(Locale.ROOT), Integer.parseInt(threshot)));

                    break;
                case "2":
                    System.out.println("Please Enter name");
                    String name2 = input.nextLine();
                    System.out.println("Please Enter threshot value");
                    String threshot2 = input.nextLine();
                    System.out.println(networkComponents.printFartherCharacters(name2.toLowerCase(Locale.ROOT), Integer.parseInt(threshot2)));
                    break;
                case "3":
                    System.out.println("Please Enter first name");
                    String nameForIsConnected1 = input.nextLine();
                    System.out.println("Please Enter second name");
                    String nameForIsConnected2 = input.nextLine();
                    System.out.println(networkComponents.isConnected(nameForIsConnected1.toLowerCase(Locale.ROOT), nameForIsConnected2.toLowerCase(Locale.ROOT)));

                    break;
                case "4":
                    System.out.println("Please Enter first name to delete");
                    String nameDelete1 = input.nextLine();
                    System.out.println("Please Enter second name to delete");
                    String nameDelete2 = input.nextLine();
                    networkComponents.removeConnections(nameDelete1.toLowerCase(Locale.ROOT), nameDelete2.toLowerCase(Locale.ROOT));

                    break;
                case "5":
                    System.out.println("Please Enter the source name");
                    String nameBFSSource = input.nextLine();
                    System.out.println("Please Enter the final destination name");
                    String nameBFSDestination = input.nextLine();
                    networkComponents.breadthFirstSearchShortestPart(nameBFSSource, nameBFSDestination);

                    break;
                case "6":
                    System.out.println("Please enter first character");
                    String nameWeightChange1 = input.nextLine();
                    System.out.println("Please enter second character");
                    String nameWeightChange2 = input.nextLine();
                    System.out.println("Please enter new weight value");
                    String newWeight = input.nextLine();
                    networkComponents.changeWeightsOfCharacters(nameWeightChange1.toLowerCase(Locale.ROOT), nameWeightChange2.toLowerCase(Locale.ROOT), Integer.parseInt(newWeight));

                    break;
                case "7":
                    System.out.println("Press enter to print components");
                    String componenets = input.nextLine();
                    networkComponents.printDifferentConponents();

                    break;
                case "8":
                    System.out.println("Press enter to Find the vertex that has maximum connections");// custom 1
                    String findConnection = input.nextLine();
                    System.out.println(networkComponents.printTheLargest());

                    break;
                case "9":
                    System.out.println("Please enter character name");            // custom 2
                    String getCharacter = input.nextLine();
                    System.out.println(networkComponents.printTheConnectionsOfGivenCharacters(getCharacter.toLowerCase(Locale.ROOT)));
                    break;
                default:
                    throw new Exception("YouEnteredWrongNumberException " + option);
            }
            System.out.println("\n\nPlease continue with following options");
            System.out.println("\nHello Welcome to Game Of Thrones Network Project\n\n" +
                    " Press 1) to Print Closest Characters\n" +
                    " Press 2) to Print Farther Characters\n" +
                    " Press 3) to Control Characters Connected or Not \n" +
                    " Press 4) to Remove connection between Characters\n" +
                    " Press 5) to See shortest path between two characters\n" +
                    " Press 6) to Change weights of the nodes\n" +
                    " Press 7) to Print Components of the lists\n" +
                    " Press 8) to Find maximum number of connections\n" +
                    " Press 9) to Print connections of the weights and characters for the given character\n" +
                    " Press 0) to Exit from system");
            System.out.println();
            option = input.nextLine();

        }


    }
}

