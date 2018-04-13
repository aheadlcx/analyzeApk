package com.sprite.ads.third.book;

import com.sprite.ads.nati.NativeAdData;
import java.io.Serializable;

public class BookAdData extends NativeAdData implements Serializable {
    public String book_author;
    public int book_id;
    public int id;
    public String image;
    public String intro;
    public String name;

    public int getActionType() {
        return 0;
    }

    public String getBookAuthor() {
        return this.book_author;
    }

    public String getDesc() {
        return this.intro == null ? "" : this.intro.replace("&nbsp;", " ").replace("\r", "").replace("\n", "").trim();
    }

    public String getMovie() {
        return null;
    }

    public String getPic() {
        return this.image;
    }

    public String getResType() {
        return "book";
    }

    public String getTitle() {
        return this.name == null ? "" : this.name;
    }

    public String getUrl() {
        return null;
    }
}
