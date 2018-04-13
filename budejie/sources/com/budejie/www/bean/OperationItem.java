package com.budejie.www.bean;

import java.io.Serializable;
import java.util.List;

public class OperationItem implements Serializable {
    private static final long serialVersionUID = 1;
    public String android;
    public String backgroud_pic;
    public String buttons;
    public String end_time;
    public String font_color;
    public String id;
    public String ipad;
    public String iphone;
    public String is_show;
    public List<OperationButton> operationButtonList;
    public String order_id;
    public String recommend_targets;
    public String show_num;
    public String title;
    public String words;
}
