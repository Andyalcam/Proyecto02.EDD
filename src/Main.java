import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {

        static List<Element> list = new List<>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);

        int opc;
        boolean volver = true, excep;
        BinarySearchTree<Integer, Element> binarySearchTree;
        Utilidades util = new Utilidades();
        binarySearchTree = util.leerObjetoArchivo("src/BancoDePreguntas/BancoDePreguntas.dat");

        try {
            System.out.println("\t*** BIENVENIDO A 20 PREGUNTAS ***");
            System.out.println("Versión Super Héroes :3");
            do {
                System.out.println("\nPresiona enter para jugar");
                in.nextLine();

                binarySearchTree.rove();
                util.escribirObjetosArchivo("src/BancoDePreguntas/BancoDePreguntas.dat", binarySearchTree);

                if (binarySearchTree.getContador() > 19) {
                    System.out.println("No he podido adivinar tu personaje en 20 preguntas :c");
                }
                System.out.println("\nEl juego terminó...\t¿Qué deseas hacer?\n");

                excep = true;
                while (excep) {
                    try {
                        System.out.println("1. Volver a jugar");
                        System.out.println("2. Mostrar el listado de las preguntas en orden alfabético");
                        System.out.println("3. Mostrar el listado de las preguntas en el orden en que fueron agregadas");
                        System.out.println("4. Mostrar el listado de los entes en orden alfabético");
                        System.out.println("5. Mostrar el listado de los entes en el orden en que fueron agregados");
                        System.out.println("6. Salir");
                        System.out.println("Ingresa una opción");

                        opc = on.nextInt();

                        switch (opc) {
                            case 1:
                                volver = true;
                                excep = false;
                                list.clear();
                                break;
                            case 2:
                                binarySearchTree.preOrdenQuestions();
                                System.out.println(list.orderList("ASC") + "\n");
                                break;
                            case 3:
                                binarySearchTree.preOrdenQuestions();
                                System.out.println(list.orderList("ADD") + "\n");
                                break;
                            case 4:
                                binarySearchTree.preOrdenCharacters();
                                System.out.println(list.orderList("ASC") + "\n");
                                break;
                            case 5:
                                binarySearchTree.preOrdenCharacters();
                                list.orderList("ADD");
                                System.out.println(list + "\n");
                                break;
                            case 6:
                                util.escribirObjetosArchivo("src/BancoDePreguntas/BancoDePreguntas.dat", binarySearchTree);
                                volver = false;
                                excep = false;
                                break;
                            default:
                                System.out.println("Elige una opción del menu plis :c\n");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("\tDebes ingresar un número\tIntentalo de nuevo\n");
                        on.next();
                        excep = true;
                    }
                }
            } while (volver);
        }catch (Exception e){
            System.out.println("Ingresa un banco de preguntas válido");
        }
    }
}
