package com.budejie.www.bean;

public class RuleBean {
    public float money;
    public String name;
    public float up_limit;

    public RuleBean(String str, float f, float f2) {
        this.name = str;
        this.money = f;
        this.up_limit = f2;
    }
}
