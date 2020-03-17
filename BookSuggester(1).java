import java.util.*;
import java.io.*;

class BookSuggester {
    static TreeNode startnode;

    public static void main(String args[]) throws IOException {

        System.out.println("Welcome to the Book Club's Fantastic Book Suggester.");

        Scanner myScanner = new Scanner(System.in);

        File tempFile = new File("suggestions.txt");
        boolean exists = tempFile.exists();

        if (exists) {
            FileReader fr = new FileReader("suggestions.txt");
            BufferedReader br = new BufferedReader(fr);
            int counter = 0;
            fromPreOrder(br, counter);
        } else {
            TreeNode l2 = new TreeNode("Pride and Prejudice");
            TreeNode l1 = new TreeNode("Treasure Island");
            TreeNode l3 = new TreeNode("The Notebook");
            TreeNode n2 = new TreeNode("Are you in the mood for something modern?", l3, l2);
            TreeNode n1 = new TreeNode("Do you like romance?", n2, l1);
            startnode = n1;
        }


        TreeNode current = startnode;
        TreeNode questionBefore = current;
        Boolean repeat = true;
        Boolean ask = true;


        while (repeat == true) {

            while (!current.isLeaf()) {
                questionBefore = current;
                ask = askYesNo(current.value);
                if (ask) {

                    current = current.getLeft();

                } else {

                    current = current.getRight();
                }


            }

            System.out.println("Perhaps you would like " + current.value);


            System.out.println("Is this satisfactory? [y/n]");
            if (myScanner.nextLine().contains("n")) {

                String newName;
                String newQuestion;

                System.out.println("What would you prefer instead?");
                newName = myScanner.nextLine();
                System.out.println("Tell me a question that distinguishes " + current.value + " from " + newName);
                newQuestion = myScanner.nextLine();

                TreeNode tempBook = new TreeNode(newName);
                TreeNode tempQuestion = new TreeNode(newQuestion, tempBook, current);

                if (ask) {

                    current = current.getLeft();
                    questionBefore.left = tempQuestion;

                } else {
                    current = current.getRight();
                    questionBefore.right = tempQuestion;
                }
            }
            System.out.println("Do you want to go again? [y/n] ");
            String userInput2 = myScanner.nextLine();
            if (userInput2.contains("y")) {
                repeat = true;
                current = startnode;
            } else if (userInput2.contains("n")) {
                repeat = false;
            }

        }

        System.out.println("Do you want to save the current tree? [y/n]");
        if (myScanner.nextLine().contains("y")) {
            FileWriter myWriter = new FileWriter("suggestions.txt");
            BufferedWriter myBuffer = new BufferedWriter(myWriter);
            preOrder(startnode, myBuffer);
            myBuffer.close();

        }
    }


    public static void preOrder(TreeNode node, BufferedWriter myBuffer) throws IOException {
        if (node == null) {
            return;
        }
        if (node.isLeaf() == true) {

            myBuffer.write(node.value + " #");

        } else {

            myBuffer.write(node.value);

        }
        myBuffer.newLine();
        preOrder(node.left, myBuffer);
        preOrder(node.right, myBuffer);
    }

    public static TreeNode fromPreOrder(BufferedReader br, int counter) throws IOException {

        String currentstring = br.readLine();
        if (currentstring.contains("#")) {
            String cleanString = currentstring.replaceFirst("#", "");
            TreeNode node = new TreeNode(cleanString);
            node.setLeft(null);
            node.setRight(null);
            return node;

        }
        if (counter == 0) {

            TreeNode node = new TreeNode(currentstring);
            counter++;
            node.setLeft(fromPreOrder(br, counter));
            node.setRight(fromPreOrder(br, counter));
            startnode = node;
            return node;

        } else {

            TreeNode node = new TreeNode(currentstring);
            counter++;
            node.setLeft(fromPreOrder(br, counter));
            node.setRight(fromPreOrder(br, counter));


            return node;
        }

    }


    public static boolean askYesNo(String question) {

        Scanner myScanner = new Scanner(System.in);

        System.out.println(question + " [y/n]");

        String userInput = myScanner.nextLine();


        if (userInput.contains("n")) {
            return false;
        } else {
            return true;
        }

    }


}
