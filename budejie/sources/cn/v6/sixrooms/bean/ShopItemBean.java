package cn.v6.sixrooms.bean;

import java.util.List;

public class ShopItemBean {
    private Item g;
    private Item h;
    private Item z;

    public class Item {
        private String desc;
        private List<ShopItemBean$Item$ItemDetails> list;
        private String prop;

        public List<ShopItemBean$Item$ItemDetails> getList() {
            return this.list;
        }

        public void setList(List<ShopItemBean$Item$ItemDetails> list) {
            this.list = list;
        }

        public String getProp() {
            return this.prop;
        }

        public void setProp(String str) {
            this.prop = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }
    }

    public Item getZ() {
        return this.z;
    }

    public void setZ(Item item) {
        this.z = item;
    }

    public Item getH() {
        return this.h;
    }

    public void setH(Item item) {
        this.h = item;
    }

    public Item getG() {
        return this.g;
    }

    public void setG(Item item) {
        this.g = item;
    }
}
