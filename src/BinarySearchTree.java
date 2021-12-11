import java.io.Serializable;
import java.util.Scanner;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class BinarySearchTree <K extends Comparable,T> implements TDABinarySearchTree<K,T>, Serializable {

    private BinaryNode<K, T> root;

    /**
     * Recupera el objeto con clave k.
     *
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    @Override
    public T retrieve(K k) {
        BinaryNode node = retrieve(root,k);
        if(node == null) //Si no existe el elemento.
            return null;
        return (T) node.getElement();
    }

    /**
     * Método auxiliar para retrieve y obtener un nodo con tener la clave
     *
     * @param node nodo por el que empezaremos
     * @param key llave del nodo que buscamos
     * @return El nodo con la clave que buscamos
     */
    private BinaryNode retrieve(BinaryNode node, K key){
        //Si no existe el nodo
        if(node == null)
            return null;

        // Si encontramos el elemento
        if(node.getKey().compareTo(key) == 0)
            return node;

        // Comparamos los elementos
        if(key.compareTo(node.getKey()) < 0){ // Verificamos en la izquierda
            return retrieve(node.getLeft(), key);
        } else { // Verificar en la derecha
            return retrieve(node.getRight(), key);
        }
    }

    public void setElement(BinaryNode node, T e){
        node.setElement(e);
    }

    /**
     * Inserta un nuevo elemento al árbol.
     *
     * @param e el elemento a ingresar.
     * @param k la clave del elemento a ingresar.
     */
    @Override
    public void insert(T e, K k) {
        if(isEmpty()){ //Si el árbol es vacío.
            root = new BinaryNode<>(e, k, null);
            return;
        }
        insert(e, k, root);
    }

    /**
     * Método auxiliar de insert para saber en que lugar irá el nodo que insertaremos.
     * @param e - elemento que ingresaremos.
     * @param key - llave del nodo.
     * @param node - nodo con el que compararemos.
     */

    private void insert(T e, K key, BinaryNode node){
        if(node.getKey().compareTo(key) >= 0){//Si la clave del nodo a insertar es menor o igual al nodo existente
            if(node.hasLeft()){
                insert(e, key, node.getLeft());
            }else{
                node.setLeft(new BinaryNode<>(e, key, node));
            }
        }else{//Si la clave del nodo a insertar es mayor al nodo existente.
            if(node.hasRight()){
                insert(e, key, node.getRight());
            }else{
                node.setRight(new BinaryNode<>(e, key, node));
            }
        }
    }

    /**
     * Elimina el nodo con clave k del árbol.
     *
     * @param k la clave perteneciente al nodo a eliminar.
     * @return el elemento almacenado en el nodo a eliminar.
     * null si el nodo con clave k no existe.
     */
    @Override
    public T delete(K k) {
        if(this.isEmpty()) //Si el árbol es vacío regresa null
            return null;
        else
            return delete(retrieve(root,k));
    }

    /**
     * Método auxiliar para delete.
     * @param node nodo que eliminaremos.
     * @return elemento del nodo que se borró.
     */

    private T delete(BinaryNode node){
        if(node == null){ //Si no existe el elemento regresará null.
            return null;
        }

        BinaryNode aux;

        T element = (T) node.getElement();

        if(node.hasLeft()){//Si el elemento a borrar tiene hijo izquierdo.
            aux = findMaxAux(node.getLeft());
            if(aux.isLeaf()){//Verificaremos si el elemento más grande es una hoja.
                if(aux.getParent().equals(node))//Si el nodo máximo es hijo del elemento a borrar.
                    node.setLeft(null);
                else//Por si hay más nodos entre el nodo a borrar y el máximo.
                    aux.getParent().setRight(null);
            }else{//Si el elemento máximo no es una hoja.
                aux.getLeft().setParent(aux.getParent());
                aux.getParent().setRight(aux.getLeft());
            }
        }else{//Verificaremos si tiene hijo derecho o no es una hoja.
            if(node.hasRight()){//Si hay hijo derecho.
                aux = findMaxAux(node.getRight());
                if(aux.isLeaf()){//Verificaremos si el elemento más grande es una hoja.
                    if(aux.getParent().equals(node)) //Si el nodo máximo es hijo del elemento a borrar.
                        node.setRight(null);
                    else //Por si hay más nodos entre el nodo a borrar y el máximo.
                        aux.getParent().setRight(null);
                }else{//Si el elemento máximo no es una hoja.
                    aux.getLeft().setParent(aux.getParent());
                    aux.getParent().setRight(aux.getLeft());
                }
            }else{//Si no tiene hijos
                if(node.getKey().compareTo(node.getParent().getKey()) < 0) //Si es hijo izquierdo.
                    node.getParent().setLeft(null);
                else //Si es hijo derecho.
                    node.getParent().setRight(null);
                return element;
            }
        }
        node.setElement(aux.getElement());
        node.setKey(aux.getKey());
        return element;
    }


    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     * @param node - nodo base del que encontraremos el mínimo.
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public T findMin(BinaryNode node) {
        if(this.isEmpty())
            return null;
        else{
            return (T) findMinAux(node).getElement();
        }
    }

    /**
     * Método que encuentra el elemento mínimo del árbol.
     * @return El elemento con la llave de peso mínimo.
     */

    public T findMin(){
       if(this.isEmpty())
           return null;
       else
           return (T) findMinAux(root).getElement();
    }

    /**
     * Método auxiliar de findMin para saber el nodo del elemento mínimo.
     * @param node - Nodo base del que encontraremos el mínimo.
     * @return Nodo del elemento mínimo.
     */

    private BinaryNode findMinAux(BinaryNode node){
        if(node.hasLeft())
            return findMinAux(node.getLeft());
        else
            return node;
    }


    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     *
     * @return el elemento con llave de peso máximo.
     */
    @Override
    public T findMax(BinaryNode node) {
        if(this.isEmpty())
            return null;
        else
            return (T) findMaxAux(node).getElement();
    }

    /**
     * Método que encuentra la clave k con valor o peso máximo del árbol.
     *
     * @return el elemento con llave de peso máximo
     */
    public T findMax(){
        if(this.isEmpty())
            return null;
        else
            return (T) findMaxAux(root).getElement();
    }


    /**
     * Método auxiliar de findMax para encontrar la clave con mayor peso.
     * @param node - nodo del que tomaremos como base para encontrar el máximo.
     * @return nodo del elemento máximo.
     */
    private BinaryNode findMaxAux(BinaryNode node){
        if(node.hasRight())
            return findMaxAux(node.getRight());
        else
            return node;
    }

    /**
     * Recorre el árbol en preorden.
     */
    @Override
    public void preorden() {
        preorden(root);
    }

    /**
     * Método auxiliar de preorden para hacer el recorrido del árbol.
     * @param node - nodo del que empezaremos el recorrido.
     */
    public void preorden(BinaryNode node){
         if(node == null)
            return;
        
        System.out.print(node.getElement() + " ");   
        preorden(node.getLeft());  
        preorden(node.getRight());
    }

    /**
     * Recorre el árbol en inorden.
     */
    @Override
    public void inorden() {
        inorden(root);
    }

    /**
     * Método auxiliar de inorden para recorrer el árbol.
     * @param node - nodo del que empezaremos.
     */
    public void inorden(BinaryNode node){
        if(node == null)
            return;
        
        inorden(node.getLeft());
        System.out.print(node.getElement() + " ");
        inorden(node.getRight());
    }


    /**
     * Recorre el árbol en postorden.
     */
    @Override
    public void postorden() {
        postorden(root);
    }

    /**
     * Método auxiliar de postorden para hacer recorrido del árbol.
     * @param node - nodo del que empezaremos.
     */
     public void postorden(BinaryNode node){
        if( node == null )
            return;
        postorden(node.getLeft());
        postorden(node.getRight());
        System.out.print(node.getElement() + " ");
    }

    /**
     * Verifica si el árbol es vacío.
     *
     * @return true si el árbol es vacío, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public void clear(){
        root = null;
    }

    public void addQ(){

    }

    /**
     * Metodo que recorre el árbol segun se decida, izq o derecha
     */
    public void rove(){
        Scanner in = new Scanner(System.in);
        String res,newItem, pregunta,aux;

        BinaryNode actual;
        actual = root;

        while (!actual.isLeaf()) {
            System.out.println(actual);
            res = in.nextLine().trim();
            if (res.equalsIgnoreCase("si")) {
                actual = actual.getLeft();
            }else if (res.equalsIgnoreCase("no")) {
                actual = actual.getRight();
            }else {
                System.out.println("Debes escribir si o no");
            }
        }
        boolean repe = true;
        while (repe) {
            System.out.println(actual);
            res = in.nextLine().trim();
            if (res.equalsIgnoreCase("si")) {
                System.out.println("He adivinado tu personaje :3");
                repe = false;
            }else if (res.equalsIgnoreCase("no")) {
                System.out.println("¿En que personaje estas pensando?");
                newItem = in.nextLine();
                newItem = ("¿Es " + newItem + "?");
                insert((T)newItem,(K)actual.getKey(),actual);
                System.out.println("Escribe la pregunta que ayudara a adivinar tu personaje");
                pregunta = in.nextLine();
                Integer key = ((Integer) actual.getKey())+1;
                aux = (String) actual.getElement();
                actual.setElement((T)pregunta);
                insert((T)aux,(K)key,actual);
                repe = false;
            }else {
                System.out.println("Debes escribir si o no");
            }    
        }  
    }
}
