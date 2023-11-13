package markup;
import java.lang.StringBuilder;
import java.util.List;

public class OrderedList extends AbstactHtmlList  {
    public OrderedList(List<ListItem> data){
        super(data);
    }

    public void toHtml(StringBuilder dest){
        super.toHtml(dest, "ol");
    }
}