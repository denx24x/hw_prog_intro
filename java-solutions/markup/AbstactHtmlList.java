package markup;

import java.lang.StringBuilder;
import java.util.List;

public abstract class AbstactHtmlList implements HtmlCollectionInterface {
    private final List<ListItem> data;

    public AbstactHtmlList(final List<ListItem> data) {
        this.data = data;
    }

    protected void toHtml(final StringBuilder dest, final String ending) {
        dest.append("<").append(ending).append(">");
        for (final ListItem val : data) {
            val.toHtml(dest);
        }
        dest.append("</").append(ending).append(">");
    }
}