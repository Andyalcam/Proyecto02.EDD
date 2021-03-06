import java.io.Serializable;
import java.util.Iterator;

public class List<T> implements TDAList<T>, Serializable {

    private Node<T> head;
    private int size;

    /**
     * Inserta un nuevo elemento <i>e</i> en la posición <i>i</i>.
     *
     * @param i la posición donde agregar el elemento.
     * @param e el elemento a insertar.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {
        if(i < 0 || i > size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<>(e);

        if(head == null){//Si la lista es vacia
            head = newNode;
        }else if(i == 0){//Si se va agregar al inicio
            newNode.setNext(head);
            head = newNode;
        }else{//Cuando se agrega en cualquier otra posición
            Node<T> iterator = head;
            for(int j = 1; j < i; j++){
                iterator = iterator.getNext();
            }
            newNode.setNext(iterator.getNext());
            iterator.setNext(newNode);
        }
        size++;
    }

    /**
     * Limpia la lista. Elimina todos los elementos.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Verifica si un elemento está contenido en la lista.
     *
     * @param e el elemento a verificar si está contenido.
     * @return true si el elemento está contenid, false en otro caso.
     */
    @Override
    public boolean contains(T e) {
        Node<T> aux = head;
        if(isEmpty()){
            return false;
        }

        for(int i = 0; i < size; i++){
            if(aux.getElement().equals(e)){
                return true;
            }
            aux = aux.getNext();
        }

        return false;
    }

    /**
     * Obtiene el elemento en la posición <i>i</i>.
     *
     * @param i el índice a obtener elemento.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        if( i < 0 || i > size){
            throw new IndexOutOfBoundsException();
        }else if(isEmpty()){
            return null;
        }else if(i == 0){
            return head.getElement();
        }else{
            Node<T> aux = head;
            for(int j = 0; j < i; j++){
                aux = aux.getNext();
            }
            return aux.getElement();
        }
    }

    public Node<T> getNode(int i) throws IndexOutOfBoundsException {
        if( i < 0 || i > size){
            throw new IndexOutOfBoundsException();
        }else if(isEmpty()){
            return null;
        }else if(i == 0){
            return head;
        }else{
            Node<T> aux = head;
            for(int j = 0; j < i; j++){
                aux = aux.getNext();
            }
            return aux;
        }
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Elimina el elemento en la posición <i>i</i>.
     *
     * @param i el índice del elemento a eliminar.
     * @return el elemento eliminado.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i > size ){
            throw new IndexOutOfBoundsException();
        }
        // Eliminar la cabeza
        if(i == 0){
            T element = head.getElement();
            if(size == 1){
                head = null;
            }else{
                head = head.getNext();
            }
            size--;
            return element;
        }else{
            Node<T> aux = head;
            for(int j = 0; j < i-1; j++){
                aux = aux.getNext();
            }

            aux.setNext(aux.getNext().getNext());

            size--;
            return aux.getElement();
        }
    }

    /**
     * Regresa la cantidad de elementos contenidos en la lista.
     *
     * @return la cantidad de elementos contenidos.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Revierte los elementos de la lista. Esto es, da la reversa de la lista.
     */
    @Override
    public void revert() {
    }

    /**
     * Da la mitad derecha o izquierda de una lista.
     *
     * @param side la mitad que recortar de la lista original.
     *             true - mitad derecha.
     *             false - mitad izquierda.
     * @return una nueva lista con la mitad de los elementos.
     */
    @Override
    public TDAList<T> cut(boolean side) {
        return null;
    }

    /**
     * Da un iterador para la lista.
     *
     * @return un iterador para la estructura.
     */
    @Override
    public Iterator<T> listIterador() {
        return null;
    }

    public List<T> orderList(String order){

        for(int i = 0; i < this.size(); i++){
            for(int j = 0; j < this.size()-1; j++){

                Element elem1 = (Element) get(j);
                Element elem2 = (Element) get(j+1);

                if(order.equals("ASC")){
                    if(elem1.compareToASC(elem2) > 0){
                        getNode(j+1).setElement((T)elem1);
                        getNode(j).setElement((T) elem2);
                    }
                }else{
                    if(elem2.compareTo(elem1) < 0){
                        getNode(j+1).setElement((T)elem1);
                        getNode(j).setElement((T)elem2);
                    }
                }
            }
        }
        return this;
    }

    @Override
    public String toString() {
        if(!isEmpty()) {
            StringBuilder result = new StringBuilder("Lista: \n");
            Node<T> aux = head;
            while (aux != null) {
                result.append(aux.getElement()).append("\n");
                aux = aux.getNext();
            }
            return result.substring(0, result.length() - 1);
        }
        return "La lista es vacía";
    }

    public static void main(String[] args) {


    }

}
