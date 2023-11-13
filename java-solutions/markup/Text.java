package markup;
import java.lang.StringBuilder;

public class Text implements MarkupElementInterface  {
    private final String data;

    public Text(String value){
        this.data = value;
    }

    public void toHtml(StringBuilder dest){
        dest.append(this.data);
    }

    @Override
    public void toMarkdown(StringBuilder dest){
        dest.append(this.data);
    }
}