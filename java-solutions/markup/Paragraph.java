package markup;
import java.lang.StringBuilder;
import java.util.List;

public class Paragraph implements HtmlCollectionInterface, MarkdownElementInterface {
    private final List<MarkupElementInterface> data;
    public Paragraph(List<MarkupElementInterface> value){
        this.data = value;
    }

    public void toHtml(StringBuilder dest){
        for(MarkupElementInterface val : this.data){ val.toHtml(dest); }
    }
    
    @Override
    public void toMarkdown(StringBuilder dest){
        for(MarkupElementInterface val : this.data){ val.toMarkdown(dest); }
    }
}