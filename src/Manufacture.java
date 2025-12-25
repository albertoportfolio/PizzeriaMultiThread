import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Manufacture implements Runnable {

    /*
     * Variables to pass parameters from main
     */
    String pizzaType;
    int numPizzas;
    int fabricationTime;
    static ArrayList<String> pizzaList;
    static int margCount, prosCount, carbCount = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    /*
     * Constructor
     */
    public Manufacture(String pizzaType, int numPizzas, int fabricationTime, ArrayList<String> pizzaList) {
        this.pizzaType = pizzaType;
        this.numPizzas = numPizzas;
        this.fabricationTime = fabricationTime;
        this.pizzaList = pizzaList;
    }

    /*
     * Method to simulate fabrication time for each pizza
     */
    public static void simulateFabrication(int fabricationTime) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + fabricationTime;
        int iteration = 0;

        while (System.currentTimeMillis() < endTime) {
            iteration++;
        }
    }

    /*
     * Run method executes each thread.
     * Synchronized on pizzaList to avoid adding extra pizzas concurrently.
     */
    @Override
    public void run() {
        switch (pizzaType) {
            case "Margarita":
                if (margCount < numPizzas) {
                    margCount++;
                    simulateFabrication(fabricationTime);
                    synchronized (pizzaList) {
                        pizzaList.add(pizzaType);
                    }
                    System.out.println("Pizza added: " + pizzaType + " at " + sdf.format(new Date()));
                } else {
                    System.out.println("All Margarita pizzas have been added");
                }
                break;

            case "Prosciutto":
                if (prosCount < numPizzas) {
                    prosCount++;
                    simulateFabrication(fabricationTime);
                    synchronized (pizzaList) {
                        pizzaList.add(pizzaType);
                    }
                    System.out.println("Pizza added: " + pizzaType + " at " + sdf.format(new Date()));
                } else {
                    System.out.println("All Prosciutto pizzas have been added");
                }
                break;

            case "Carbonara":
                if (carbCount < numPizzas) {
                    carbCount++;
                    simulateFabrication(fabricationTime);
                    synchronized (pizzaList) {
                        pizzaList.add(pizzaType);
                    }
                    System.out.println("Pizza added: " + pizzaType + " at " + sdf.format(new Date()));
                } else {
                    System.out.println("All Carbonara pizzas have been added");
                }
                break;
        }
    }

    public static void main(String[] args) {
        /*
         * List to store all pizzas
         */
        ArrayList<String> pizzaList = new ArrayList<>();

        Manufacture manufacture = new Manufacture(
                args[0],
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                pizzaList
        );

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            Thread t = new Thread(manufacture);
            threads.add(t);
            t.start();

            /*
             * If there are 8 threads running, join them
             */
            if (threads.size() == 8) {
                try {
                    for (Thread thread : threads) {
                        thread.join();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                threads.clear();
            }

            try {
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (String pizza : pizzaList) {
            System.out.println(pizza);
        }
    }
}

