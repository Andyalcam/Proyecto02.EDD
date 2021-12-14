import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Element implements Serializable {
    private String element;
    private Date date;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy - HH:mm");

    public Element(String element, Date date) {
        this.element = element;
        this.date = date;
    }

    public String getElement() {
        return element;
    }

    public Date getDate() {
        return date;
    }

    public int compareTo(Element element){
        return this.getDate().compareTo(element.getDate());
    }

    public int compareToASC(Element element){
        return this.getElement().toLowerCase().compareTo(element.getElement().toLowerCase());
    }

    @Override
    public String toString() {
        return element + " - " + simpleDateFormat.format(getDate()) ;
    }

}