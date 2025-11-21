package model;

import org.springframework.util.StringUtils;

public class TitleProvider {
    private String title = "Default title from";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static TitleProvider instance(final String title) {
        var childProvider = new TitleProvider();
        if (StringUtils.hasText(title)) {
            childProvider.setTitle(title);
        }
        return childProvider;
    }
}
