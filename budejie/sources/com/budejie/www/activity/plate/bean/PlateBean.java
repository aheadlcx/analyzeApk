package com.budejie.www.activity.plate.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PlateBean implements Serializable {
    public int colum_set;
    public int display_level;
    public int forum_sort;
    public int forum_status;
    public String image_detail;
    public String image_list;
    public String info;
    public int is_sub;
    @SerializedName(alternate = {"post_number"}, value = "post_num")
    public int post_num;
    public int read_count;
    public int read_time;
    public int sub_number;
    public String tail;
    @SerializedName(alternate = {"id"}, value = "theme_id")
    public String theme_id;
    @SerializedName(alternate = {"name"}, value = "theme_name")
    public String theme_name;
    public int today_topic_num;
    public int visit;
}
