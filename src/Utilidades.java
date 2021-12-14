import java.io.*;

/**
* Clase de utilidades para mantener consistencia de los objetos guardados
* @author Andrea Alvarado Camacho
* @author Alfonso Mondragón Segoviano
* @version 1.2
*/
public class Utilidades implements Serializable {

	BinarySearchTree<Integer, Element> bst = new BinarySearchTree<>();

	/*/**
	* Metodo para agregar elementos a un arbol
	* @param elem - elemento que se va a almacenar
	* @return arbol ya con elementos
	*/
	/*public BinarySearchTree insertar(T elem, int clave){
		bst.insert(elem,clave);
		return bst;
	}*/

	/**
	* Metodo para leer objetos de tipo BST
	* @param ruta_del_archivo - nombre del archivo
	* @return Binary Search Tree
	*/
	public BinarySearchTree<Integer, Element> leerObjetoArchivo(String ruta_del_archivo){
		ObjectInputStream lect = null;

		try{
			lect = new ObjectInputStream(new FileInputStream(ruta_del_archivo));
			bst = (BinarySearchTree<Integer, Element>) lect.readObject();
		}catch(IOException | ClassNotFoundException e){
			System.out.println("Lectura fallida: "+e);
		}finally{
			if(lect != null){
				try{
					lect.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bst;
	}

	/**
	* Metodo para escribir objetos de cualquier tipo
	* @param ruta_del_archivo - nombre del archivo
	* @param bts1 - arbol
	*/
	public void escribirObjetosArchivo(String ruta_del_archivo, BinarySearchTree<Integer, Element> bts1){
		try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(ruta_del_archivo))) {
			escritor.writeObject(bts1);
		} catch (IOException e) {
			System.out.println("Error en la grabacion: " + e);
		}
	}
}