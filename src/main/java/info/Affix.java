package info;

/**
 * Created by User on 13.12.2018.
 */
public class Affix {
    String content;
    String type;

    public Affix(String content, String type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Affix{" +
                "content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
