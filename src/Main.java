import java.io.*;

public class Main implements Serializable {

    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert("Oa", 20);
        binarySearchTree.insert("Crayoa", 17);
        binarySearchTree.insert("Wofito", 21);

        //binarySearchTree.preorden();


        try {
            /*ObjectOutputStream bancoDePreguntas = new ObjectOutputStream(new FileOutputStream("src/BancoDePreguntas/BancoDePreguntas.dat"));
            bancoDePreguntas.writeObject(binarySearchTree);
            bancoDePreguntas.close();*/

            ObjectInputStream bancoDePreguntas = new ObjectInputStream(new FileInputStream("src/BancoDePreguntas/BancoDePreguntas.dat"));
            BinarySearchTree arbolDePreguntas = (BinarySearchTree) bancoDePreguntas.readObject();
            bancoDePreguntas.close();

            System.out.println("Banco de Preguntas PreOrden:");
            arbolDePreguntas.preorden();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
