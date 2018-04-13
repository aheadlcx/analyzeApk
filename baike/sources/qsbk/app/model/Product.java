package qsbk.app.model;

public class Product extends QbBean {
    public int count;
    public String id;
    public String name;
    public String totalMoney;

    public Product(String str, String str2, int i, String str3) {
        this.name = str;
        this.id = str2;
        this.count = i;
        this.totalMoney = str3;
    }

    public double totalMoney() {
        return Double.parseDouble(this.totalMoney);
    }
}
