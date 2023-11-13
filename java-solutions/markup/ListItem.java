package markup;
import java.lang.StringBuilder;
import java.util.List;

public class ListItem implements HtmlElementInterface  {
    private final List<HtmlCollectionInterface> data;

    public ListItem(final List<HtmlCollectionInterface> value){
        this.data = value;
    }

    public void toHtml(final StringBuilder dest) {
        dest.append("<li>");
        for(final HtmlCollectionInterface val : data){
            val.toHtml(dest);
        }
        dest.append("</li>");
    }
}