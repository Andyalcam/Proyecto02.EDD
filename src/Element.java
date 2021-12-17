import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
* Clase de Elementos de tipo string con fecha para saber cuando fueron creados
* @author Andrea Alvarado Camacho
* @author Alfonso Mondragón Segoviano
* @version 1.0
*/
public class Element implements Serializable {
    private String element;
    private Date date;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy - HH:mm");

    /**
     * Constructor para crear un objeto de tipo Element
     * @param element - cadena con el elemento a almacenar
     * @param date - objeto de tipo Date para guardar la fecha exacta de creaccion
     */
    public Element(String element, Date date) {
        this.element = element;
        this.date = date;
    }

    /**
     * Metodo que regresa el elemento de tipo string del objeto
     * @return element - cadena con el elemento
     */
    public String getElement() {
        return element;
    }

    /**
     * Metodo que regresa la fecha de tipo Date del objeto
     * @return date - objeto Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Metodo que compara las fechas de tipo Date de dos objetos de tipo Element
     * @return int - regresa un entero negativo si la fecha del objeto que llama al metodo es menor a la fecha del objeto que pasa como parametro.
     * Regresa un entero positivo si la fecha del objeto que llama al metodo es mayor a la fecha del objeto que pasa como parametro.
     * O regresa un cero si las fechas de ambos objetos son iguales.
     */
    public int compareTo(Element element){
        return this.getDate().compareTo(element.getDate());
    }

    /**
     * Metodo que compara los elementos de tipo String de dos objetos de tipo Element
     * @return int - regresa un entero negativo si la cadena del objeto que llama al metodo es menor a la cadena del objeto que pasa como parametro.
     * Regresa un entero positivo si la cadena del objeto que llama al metodo es mayor a la cadena del objeto que pasa como parametro.
     * O regresa un cero si las cadenas de ambos objetos son iguales.
     */
    public int compareToASC(Element element){
        return this.getElement().toLowerCase().compareTo(element.getElement().toLowerCase());
    }

    /**
     * Metodo para imprimir en terminal el objeto de tipo Element
     */
    @Override
    public String toString() {
        return element + " - " + simpleDateFormat.format(getDate()) ;
    }
}