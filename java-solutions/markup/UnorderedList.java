package markup;
import java.lang.StringBuilder;
import java.util.List;

public class UnorderedList extends AbstactHtmlList  {
    public UnorderedList(final List<ListItem> data){
        super(data);
    }

    @Override
    public void toHtml(final StringBuilder dest){
        toHtml(dest, "ul");
    }
}