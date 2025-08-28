import java.util.Scanner;

public class Octoplush {
    private static final String SEP = "    ____________________________________________________________";
    private static final String IND = "     ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greeting
        System.out.println(SEP);
        System.out.println(IND + "Hello! I'm Octoplush");
        System.out.println(IND + "What can I do for you?");
        System.out.println(SEP);

        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                // If user inputs "bye", exit
                System.out.println(SEP);
                System.out.println(IND + "Bye. Hope to see you again soon!");
                System.out.println(SEP);
                break;
            } else {
                // Echo user input
                System.out.println(SEP);
                System.out.println(IND + line);
                System.out.println(SEP);
            }
        }

        sc.close();
    }
}