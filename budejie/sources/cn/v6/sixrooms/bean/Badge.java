package cn.v6.sixrooms.bean;

public class Badge {
    private Bpic bpic;
    private String id;
    private Spic spic;
    private String title;
    private String type;

    public static class Bpic {
        private String height;
        private String img;
        private String width;

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }

        public String getWidth() {
            return this.width;
        }

        public void setWidth(String str) {
            this.width = str;
        }

        public String getHeight() {
            return this.height;
        }

        public void setHeight(String str) {
            this.height = str;
        }
    }

    public static class Spic {
        private String height;
        private String img;
        private String width;

        public String getHeight() {
            return this.height;
        }

        public void setHeight(String str) {
            this.height = str;
        }

        public String getWidth() {
            return this.width;
        }

        public void setWidth(String str) {
            this.width = str;
        }

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public Spic getSpic() {
        return this.spic;
    }

    public void setSpic(Spic spic) {
        this.spic = spic;
    }

    public Bpic getBpic() {
        return this.bpic;
    }

    public void setBpic(Bpic bpic) {
        this.bpic = bpic;
    }
}
