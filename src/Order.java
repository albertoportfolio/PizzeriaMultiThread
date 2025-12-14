
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Order {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner teclado = new Scanner(System.in);

        String ejecutar = "s";

        while (ejecutar.equals("s")) {

            boolean pedidoCorrecto = false;
            String nombre = "";
            int cantidad = 0;
            int tiempo = 0;

            /*
             * bucle para que el usuario introduzcapizzas, se
             * le pasan las variables para usarla en los args
             *
             * */
            while (!pedidoCorrecto) {
                try {
                    System.out.print("nombre de Pizza : ");
                    nombre = (teclado.nextLine());
                    System.out.print("Numero de Pizzas: ");
                    cantidad = Integer.parseInt(teclado.nextLine());
                    System.out.print("Temps de Fabricacio en ms: ");
                    tiempo = Integer.parseInt(teclado.nextLine());
                    pedidoCorrecto = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    pedidoCorrecto = false;
                }
            }

            String clase = "C:\\Users\\alpen\\OneDrive\\Documentos\\Pizzeria\\src\\Manufacture.java";
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
            String classpath = System.getProperty("java.class.path");
            String className = clase;
            ArrayList<String> command = new ArrayList<>();
            command.add(javaBin);
            command.add("-cp");
            command.add(classpath);
            command.add(className);
            command.add(nombre);
            command.add(String.valueOf(cantidad));
            command.add(String.valueOf(tiempo));
            ProcessBuilder builder = new ProcessBuilder(command);
            System.out.println("------ Pizzeria ----- ");
            System.out.println("1. para guardar en archivo");
            System.out.println("2. para ejecutar en pantalla");

            String opcionSalida = teclado.nextLine();
            Process p;
            switch (opcionSalida) {
                /*
                 * opcion para guardar la salida de pantalla en archivo o que se ejecute en pantalla
                 * */
                case "1":
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd_HHmmss");
                    File ficheroLog = new File(simpleDateFormat.format(new Date()));
                    p = builder.redirectOutput(ficheroLog).start();
                    p.waitFor();
                    break;
                case "2":
                    p = builder.inheritIO().start();
                    p.waitFor();
                    break;
                default:
                    break;

            }

            System.out.print("\nEjecutar de nuevo la aplicacion (s/n)?: ");
            ejecutar = teclado.nextLine();

        }
        teclado.close();
    }

}
