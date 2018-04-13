package cn.tatagou.sdk.pojo;

public class AppCate extends CommonResponseResult {
    private String id;
    private String name;
    private int position;
    private String selectThumbnail;
    private String thumbnail;

    public AppCate(String str) {
        this.id = str;
    }

    public AppCate(String str, String str2) {
        this.id = str;
        this.name = str2;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String str) {
        this.thumbnail = str;
    }

    public String getSelectThumbnail() {
        return this.selectThumbnail;
    }

    public void setSelectThumbnail(String str) {
        this.selectThumbnail = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || this.id == null) {
            return false;
        }
        return this.id.equals(((AppCate) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
