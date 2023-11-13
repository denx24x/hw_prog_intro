package markup;
import java.lang.StringBuilder;
import java.util.List;

public class Emphasis extends AbstactMarkupElement  {
    public Emphasis(List<MarkupElementInterface> data){
        super(data);
    }

    public void toHtml(StringBuilder dest){
        super.toHtml(dest, "em");
    }
    
    @Override
    public void toMarkdown(StringBuilder dest){
        super.toMarkdown(dest, "*");
    }
}