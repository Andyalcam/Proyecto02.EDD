import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
/**
* Clase de implementa la interfaz de TDABinarySearchTree con los metodos necesarios para implementar el juego 20 preguntas
* @author Andrea Alvarado Camacho
* @author Alfonso Mondragón Segoviano
* @version 1.3
*/
public class BinarySearchTree <K extends Comparable,T> implements TDABinarySearchTree<K,T>, Serializable {

    private BinaryNode<K, T> root;
    int contador;


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

    /**
     * Metodo que deja el árbol vacio
     */
    public void clear(){
        root = null;
    }

    /**
     * Metodo que muestra el preorden de los nodos de un arbol excepto sus hojas
     */
    public void preOrdenQuestions(){
        Main.list.clear();
        preOrdenQuestions(root);
    }
    
    /**
     * Metodo auxiliar que muestra el preorden de los nodos de un arbol excepto sus hojas
     * @param node - Nodo a partir del cual se va a empezar el preorden
     */
    private void preOrdenQuestions(BinaryNode node){
        if(node == null)
            return;
        else if(!node.isLeaf())
            Main.list.add(Main.list.size(), (Element) node.getElement());

        preOrdenQuestions(node.getLeft());
        preOrdenQuestions(node.getRight());

    }

    /**
     * Metodo que muestra el preorden de los nodos hojas de un arbol
     */
    public void preOrdenCharacters(){
        Main.list.clear();
        preOrdenCharacters(root);
    }

    /**
     * Metodo auxiliar que muestra el preorden de los nodos hojas de un arbol
     * @param node - Nodo a partir del cual se va a empezar el preorden
     */
    private void preOrdenCharacters(BinaryNode node){
        if(node == null)
            return;
        else if(node.isLeaf())
            Main.list.add(Main.list.size(), (Element) node.getElement());

        preOrdenCharacters(node.getLeft());
        preOrdenCharacters(node.getRight());
    }

    /**
     * Metodo que regresa el contador con el numero de preguntas que se le ha hecho al usuario
     * @return int - entero con el valor
     */
    public int getContador(){
        return contador;
    }


    /**
     * Metodo que recorre el árbol según se decida, izq o derecha
     */
    public void rove(){
        contador = 0;
        Scanner in = new Scanner(System.in);
        String res,newItem, pregunta;

        BinaryNode actual;
        actual = root;
        Element nodo = (Element) actual.getElement();

        while (!actual.isLeaf()) {
            if (contador<=19) {
                System.out.println(nodo.getElement());
                res = in.nextLine().trim();
                if (res.equalsIgnoreCase("si") || res.equalsIgnoreCase("chi")) {
                    actual = actual.getLeft();
                    contador++;
                }else if (res.equalsIgnoreCase("no") || res.equalsIgnoreCase("ño")) {
                    actual = actual.getRight();
                    contador++;
                }else {
                    System.out.println("Debes escribir si o no");
                }
                nodo = (Element) actual.getElement();
            }else{
                break;
            }
        }
        boolean repe = true;
        boolean confirmacion = true;
        while (repe) {
            if (contador<=19) {
                System.out.println("¿Tu personaje es " + nodo.getElement() + "?");
                res = in.nextLine().trim();
                if (res.equalsIgnoreCase("si") || res.equalsIgnoreCase("chi")) {
                    System.out.println("He adivinado tu personaje :3");
                    contador++;
                    repe = false;
                }else if (res.equalsIgnoreCase("no") || res.equalsIgnoreCase("ño")) {
                    contador++;
                    do{
                        System.out.println("¿En que personaje estas pensando?");
                        newItem = in.nextLine().trim();
                        System.out.println("¿Ingresaste " + newItem + "?. Escribe Si para confirmar o No para reescribir.");
                        String respuesta = in.nextLine();
                        if(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("chi")){
                            confirmacion = false;
                        }else if(respuesta.equalsIgnoreCase("no") || respuesta.equalsIgnoreCase("ño")){
                            confirmacion = true;
                        }else{
                            System.out.println("Ingresa solamente si o no");
                            confirmacion = true;
                        }
                    }while(confirmacion);
                    Element personaje = new Element(newItem, new Date());
                    insert((T)personaje,(K)actual.getKey(),actual);
                    do{
                        System.out.println("Escribe la pregunta que ayudará a adivinar tu personaje. Supon que la respuesta debe ser si");
                        pregunta = in.nextLine().trim();
                        System.out.println("¿Ingresaste '" + pregunta + "'?. Escribe Si para confirmar o No para reescribir.");
                        String respuesta = in.nextLine();
                        if(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("chi")){
                            confirmacion = false;
                        }else if(respuesta.equalsIgnoreCase("no") || respuesta.equalsIgnoreCase("ño")){
                            confirmacion = true;
                        }else{
                            System.out.println("Ingresa solamente si o no");
                            confirmacion = true;
                        }
                    }while(confirmacion);
                    Integer key = ((Integer) actual.getKey())+1;

                    Element aux = (Element) actual.getElement();

                    actual.setElement((T) new Element(pregunta, new Date()));

                    insert((T)aux,(K)key,actual);
                    repe = false;
                }else {
                    System.out.println("Debes escribir si o no");
                }  
            }else{
                break;
            }
        }  
    }
}
