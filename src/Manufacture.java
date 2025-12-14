import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Manufacture implements Runnable {

    /*
     *se crean las variables para pasar al main por parametros
     */
    String tipus;
    int numPizzas;
    int tempsfabric;
    static ArrayList<String> arrPizza;
    static int contmarg, contpros, contcarb = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd_HHmmss");

    /*
     *se crea el constructor
     */
    public Manufacture(String tipus, int numPizzas, int tempsfabric, ArrayList<String> arrPizza) {
        this.tipus = tipus;
        this.numPizzas = numPizzas;
        this.tempsfabric = tempsfabric;
        this.arrPizza = arrPizza;
    }

    /*
     *metodo para que cada pizza espere el tiempo indicado
     */

    public static void processFabricacio(int tempsfabric) {
        long tempsInici = System.currentTimeMillis();
        long tempsFinal = tempsInici + tempsfabric;
        int iteracion = 0;

        while (System.currentTimeMillis() < tempsFinal) {
            iteracion++;
        }
    }

    /*
     *metodo run que ejecuta cada hilo, synchronized en la clase para que cada vez que se ejecute se bloquee la clase entera
     * contador con static para controlar que no se añadan pizzas extra
     */
    @Override
    public void run() {
        switch (tipus) {
            case "Margarida":
                if (contmarg < numPizzas) {
                    contmarg++;
                    processFabricacio(tempsfabric);
                    synchronized (arrPizza) {
                        arrPizza.add(tipus);
                    }
                } else {
                    System.out.println("Se han añadido todas las pizzas Margarida");
                }
                break;

            case "Proscuito":
                if (contpros < numPizzas) {
                    contpros++;
                    processFabricacio(tempsfabric);
                    synchronized (arrPizza) {
                        arrPizza.add(tipus);
                    }
                } else {
                    System.out.println("Se han añadido todas las pizzas Proscuito");
                }
                break;

            case "Carbonara":
                if (contcarb < numPizzas) {
                    contcarb++;
                    processFabricacio(tempsfabric);
                    synchronized (arrPizza) {
                        arrPizza.add(tipus);
                    }
                } else {
                    System.out.println("Se han añadido todas las pizzas Carbonara");
                }
                break;
        }

        System.out.println("Pizza Introducida: " + tipus + " a la hora " + simpleDateFormat.format(new Date()));
    }


    public static void main(String[] args) {
        /*
         *arraylist que guarda todas las pizzas
         */

        ArrayList<String> arrPizza = new ArrayList<>();


        Manufacture mf = new Manufacture(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), arrPizza);
        ArrayList<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            Thread t = new Thread(mf);
            hilos.add(t);
            t.start();

            /*
             *si hay 8 hilos en ejecucion, se hace un join de los hilos
             */
            if (hilos.size() == 8) {
                try {
                    for(Thread hil : hilos) {
                        hil.join();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                hilos.clear();
            }

        }

        for (int i = 0; i < arrPizza.size(); i++) {
            System.out.println(arrPizza.get(i));
        }
    }


}
