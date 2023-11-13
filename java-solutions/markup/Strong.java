package markup;

import java.lang.StringBuilder;
import java.util.List;

public class Strong extends AbstactMarkupElement {
    public Strong(final List<MarkupElementInterface> data) {
        super(data);
    }

    @Override
    public void toHtml(final StringBuilder dest) {
        toHtml(dest, "strong");
    }

    @Override
    public void toMarkdown(final StringBuilder dest) {
        toMarkdown(dest, "__");
    }
}