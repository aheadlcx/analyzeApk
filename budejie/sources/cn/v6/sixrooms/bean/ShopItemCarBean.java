package cn.v6.sixrooms.bean;

import java.util.List;

public class ShopItemCarBean {
    private List<Item> h;
    private List<Item> p;
    private List<Item> s;
    private List<Item> z;

    public class Item {
        private CarBean car;
        private String desc;
        private List<ShopItemCarBean$Item$ItemDetails> list;
        private String prop;
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public CarBean getCar() {
            return this.car;
        }

        public void setCar(CarBean carBean) {
            this.car = carBean;
        }

        public List<ShopItemCarBean$Item$ItemDetails> getList() {
            return this.list;
        }

        public void setList(List<ShopItemCarBean$Item$ItemDetails> list) {
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

        public String toString() {
            return "Item [type=" + this.type + ", prop=" + this.prop + ", list=" + this.list + ", desc=" + this.desc + ", car=" + this.car + "]";
        }
    }

    public List<Item> getS() {
        return this.s;
    }

    public void setS(List<Item> list) {
        this.s = list;
    }

    public List<Item> getH() {
        return this.h;
    }

    public void setH(List<Item> list) {
        this.h = list;
    }

    public List<Item> getZ() {
        return this.z;
    }

    public void setZ(List<Item> list) {
        this.z = list;
    }

    public List<Item> getP() {
        return this.p;
    }

    public void setP(List<Item> list) {
        this.p = list;
    }
}
