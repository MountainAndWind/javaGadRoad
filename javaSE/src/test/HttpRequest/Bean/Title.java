package test.HttpRequest.Bean;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:34
 */
public class Title {
    private String text;

    private String lang;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Title{" +
                "text='" + text + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
