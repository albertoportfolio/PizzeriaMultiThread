import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Order {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String runAgain = "y";

        while (runAgain.equalsIgnoreCase("y")) {

            boolean validOrder = false;
            String pizzaName = "";
            int quantity = 0;
            int fabricationTime = 0;

            /*
             * Loop to allow the user to enter pizza orders.
             * Variables will be used as arguments for the process.
             */
            while (!validOrder) {
                try {
                    System.out.print("Pizza name: ");
                    pizzaName = scanner.nextLine();

                    System.out.print("Number of pizzas: ");
                    quantity = Integer.parseInt(scanner.nextLine());

                    System.out.print("Fabrication time in ms: ");
                    fabricationTime = Integer.parseInt(scanner.nextLine());

                    validOrder = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    validOrder = false;
                }
            }

            String classFile = "src/Manufacture.java";
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
            String classpath = System.getProperty("java.class.path");
            String className = classFile;

            ArrayList<String> command = new ArrayList<>();
            command.add(javaBin);
            command.add("-cp");
            command.add(classpath);
            command.add(className);
            command.add(pizzaName);
            command.add(String.valueOf(quantity));
            command.add(String.valueOf(fabricationTime));

            ProcessBuilder builder = new ProcessBuilder(command);

            System.out.println("------ Pizzeria ----- ");
            System.out.println("1. Save output to file");
            System.out.println("2. Execute on screen");

            String outputOption = scanner.nextLine();
            Process process;

            /*
             * Option to save output to a file or display on screen
             */
            switch (outputOption) {
                case "1":
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    File logFile = new File(sdf.format(new Date()) + ".txt");
                    process = builder.redirectOutput(logFile).start();
                    process.waitFor();
                    break;

                case "2":
                    process = builder.inheritIO().start();
                    process.waitFor();
                    break;

                default:
                    break;
            }

            System.out.print("\nRun the application again (y/n)?: ");
            runAgain = scanner.nextLine();
        }

        scanner.close();
    }
}
