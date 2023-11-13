package markup;
import java.lang.StringBuilder;
import java.util.List;

public abstract class AbstactMarkupElement implements MarkupElementInterface  {
    private final List<MarkupElementInterface> data;

    public AbstactMarkupElement(List<MarkupElementInterface> value){
        this.data = value;
    }

    protected void toHtml(StringBuilder dest, String ending){
        dest.append("<" + ending + ">");
        for(MarkupElementInterface val : this.data){
            val.toHtml(dest);
        }
        dest.append("</" + ending + ">");
    }

    protected void toMarkdown(StringBuilder dest, String ending){
        dest.append(ending);
        for(MarkupElementInterface val : this.data){
            val.toMarkdown(dest);
        }
        dest.append(ending);
    }
}