package markup;
import java.lang.StringBuilder;
import java.util.List;

public class Strikeout extends AbstactMarkupElement  {
    public Strikeout(List<MarkupElementInterface> data){
        super(data);
    }

    public void toHtml(StringBuilder dest){
        super.toHtml(dest, "s");
    }
    
    @Override
    public void toMarkdown(StringBuilder dest){
        super.toMarkdown(dest, "~");
    }
}