package test.HttpRequest.Bean;

import java.util.List;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:31
 */
public class ApplyDate {
    List<Contents> contents;

    public List<Contents> getContents() {
        return contents;
    }

    public void setContents(List<Contents> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ApplyDate{" +
                "contents=" + contents +
                '}';
    }
}
