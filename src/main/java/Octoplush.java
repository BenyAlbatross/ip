import java.util.Scanner;

public class Octoplush {
    private static final String SEP = "    ____________________________________________________________";
    private static final String IND = "     ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[100];
        int count = 0;

        // Greeting
        System.out.println(SEP);
        System.out.println(IND + "Hello! I'm Octoplush");
        System.out.println(IND + "What can I do for you?");
        System.out.println(SEP);

        while (true) {
            String line = sc.nextLine().trim();

            if (line.equals("bye")) {
                System.out.println(SEP);
                System.out.println(IND + "Bye. Hope to see you again soon!");
                System.out.println(SEP);
                break;
            } else if (line.equals("list")) {
                System.out.println(SEP);
                for (int i = 0; i < count; i++) {
                    System.out.println(IND + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(SEP);
            } else if (!line.isEmpty()) {
                // Add new task (assume <= 100)
                tasks[count++] = line;
                System.out.println(SEP);
                System.out.println(IND + "added: " + line);
                System.out.println(SEP);
            } else {
                // If input is empty, echo empty block
                System.out.println(SEP);
                System.out.println(IND);
                System.out.println(SEP);
            }
        }

        sc.close();
    }
}