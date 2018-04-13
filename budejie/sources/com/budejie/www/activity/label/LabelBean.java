package com.budejie.www.activity.label;

import com.budejie.www.bean.Topics;
import java.io.Serializable;
import java.util.List;

public class LabelBean implements Serializable {
    public String content_type;
    public String ctime;
    public String end_time;
    public String hot_post;
    public String image_detail;
    public String image_list;
    public String info;
    public String info_more;
    public String info_url;
    private boolean isSelected;
    public String is_default;
    public String is_sub;
    private String post_number;
    public String prize;
    public String result;
    public String rule;
    public String share;
    public String square_number;
    public String square_show;
    public String start_time;
    public int status;
    public String sub_number;
    public int theme_id;
    public String theme_name;
    public String theme_type;
    public List<Topics> topics;
    public String total_users;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getIs_default() {
        return this.is_default;
    }

    public void setIs_default(String str) {
        this.is_default = str;
    }

    public String getPost_number() {
        return this.post_number;
    }

    public void setPost_number(String str) {
        this.post_number = str;
    }

    public int getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(int i) {
        this.theme_id = i;
    }

    public String getTheme_name() {
        return this.theme_name;
    }

    public void setTheme_name(String str) {
        this.theme_name = str;
    }

    public String getTheme_type() {
        return this.theme_type;
    }

    public void setTheme_type(String str) {
        this.theme_type = str;
    }

    public String getContent_type() {
        return this.content_type;
    }

    public void setContent_type(String str) {
        this.content_type = str;
    }

    public String getImage_detail() {
        return this.image_detail;
    }

    public void setImage_detail(String str) {
        this.image_detail = str;
    }

    public String getImage_list() {
        return this.image_list;
    }

    public void setImage_list(String str) {
        this.image_list = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getInfo_url() {
        return this.info_url;
    }

    public void setInfo_url(String str) {
        this.info_url = str;
    }

    public String getInfo_more() {
        return this.info_more;
    }

    public void setInfo_more(String str) {
        this.info_more = str;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String str) {
        this.rule = str;
    }

    public String getPrize() {
        return this.prize;
    }

    public void setPrize(String str) {
        this.prize = str;
    }

    public String getHot_post() {
        return this.hot_post;
    }

    public void setHot_post(String str) {
        this.hot_post = str;
    }

    public String getStart_time() {
        return this.start_time;
    }

    public void setStart_time(String str) {
        this.start_time = str;
    }

    public String getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(String str) {
        this.end_time = str;
    }

    public String getTotal_users() {
        return this.total_users;
    }

    public void setTotal_users(String str) {
        this.total_users = str;
    }

    public String getSquare_show() {
        return this.square_show;
    }

    public void setSquare_show(String str) {
        this.square_show = str;
    }

    public String getSquare_number() {
        return this.square_number;
    }

    public void setSquare_number(String str) {
        this.square_number = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getCtime() {
        return this.ctime;
    }

    public void setCtime(String str) {
        this.ctime = str;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getShare() {
        return this.share;
    }

    public void setShare(String str) {
        this.share = str;
    }

    public List<Topics> getTopics() {
        return this.topics;
    }

    public void setTopics(List<Topics> list) {
        this.topics = list;
    }

    public String getSub_number() {
        return this.sub_number;
    }

    public void setSub_number(String str) {
        this.sub_number = str;
    }

    public String getIs_sub() {
        return this.is_sub;
    }

    public void setIs_sub(String str) {
        this.is_sub = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String toString() {
        return "LabelBean [theme_id=" + this.theme_id + ", theme_name=" + this.theme_name + ", theme_type=" + this.theme_type + ", content_type=" + this.content_type + ", image_detail=" + this.image_detail + ", image_list=" + this.image_list + ", info=" + this.info + ", info_url=" + this.info_url + ", info_more=" + this.info_more + ", rule=" + this.rule + ", prize=" + this.prize + ", hot_post=" + this.hot_post + ", start_time=" + this.start_time + ", end_time=" + this.end_time + ", total_users=" + this.total_users + ", square_show=" + this.square_show + ", square_number=" + this.square_number + ", status=" + this.status + ", ctime=" + this.ctime + ", result=" + this.result + ", share=" + this.share + ", sub_number=" + this.sub_number + ", is_sub=" + this.is_sub + ", isSelected=" + this.isSelected + "]";
    }
}
