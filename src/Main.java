import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);

        int opc,b;
        boolean salir = false;
        String tupla = "";
        String a = "";

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Utilidades util = new Utilidades();
        binarySearchTree = (BinarySearchTree)util.leerObjetoArchivo("src/BancoDePreguntas/BancoDePreguntas.dat");

        System.out.println("\t*** BIENVENIDO A 20 PREGUNTAS ***");
        System.out.println("\nTeclea ");

        /*while (salir == false) {
            System.out.println("1. Insertar");
            System.out.println("2. Salir");
            System.out.println("3. Mostrar Preorden");
            System.out.println("4. limpiar el arbol");
            System.out.println("5. recorrer el arbol");
            System.out.println("ingrese la opcion: ");
            opc = in.nextInt();

            switch (opc) {
                case 1:
                        System.out.println("Ingresa el elemento que quieres insertar y su clave. Ej: 2,3");
                        boolean repe = true;
                        while (repe) {
                            try {
                                tupla = on.nextLine().trim();
                                a = (tupla.split(",")[0]).trim();
                                b = Integer.parseInt(tupla.split(",")[1]);
                                binarySearchTree = util.insertar(a,b);
                                repe = false;
                            } catch (Exception e) {
                                System.out.println("\t Intentalo de nuevo. Sigue el ejemplo :)");
                            }
                        }
                    break;
                case 2:
                        util.escribirObjetosArchivo("src/BancoDePreguntas/BancoDePreguntas.dat",binarySearchTree);
                        salir = true;
                    break;
                case 3:
                        if (!binarySearchTree.isEmpty()) {
                            System.out.print("El preorden es: ");
                            binarySearchTree.preorden();
                            System.out.println();
                        } else {
                            System.out.println("\tEl árbol es vacío");
                        }
                    break;
                case 4:
                        binarySearchTree.clear();
                    break;
                case 5:
                        binarySearchTree.rove();
                    break;
                default:
                    break;
                
            }
        }
        
        /*binarySearchTree.insert("Oa", 20);
        binarySearchTree.insert("Crayoa", 17);
        binarySearchTree.insert("Wofito", 21);

        //binarySearchTree.preorden();


        try {
            /*ObjectOutputStream bancoDePreguntas = new ObjectOutputStream(new FileOutputStream("src/BancoDePreguntas/BancoDePreguntas.dat"));
            bancoDePreguntas.writeObject(binarySearchTree);
            bancoDePreguntas.close();

            ObjectInputStream bancoDePreguntas = new ObjectInputStream(new FileInputStream("src/BancoDePreguntas/BancoDePreguntas.dat"));
            BinarySearchTree arbolDePreguntas = (BinarySearchTree) bancoDePreguntas.readObject();
            bancoDePreguntas.close();

            System.out.println("Banco de Preguntas PreOrden:");
            arbolDePreguntas.preorden();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

}
