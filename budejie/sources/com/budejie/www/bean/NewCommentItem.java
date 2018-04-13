package com.budejie.www.bean;

import java.io.Serializable;

public class NewCommentItem implements Serializable {
    public String content;
    public String ctime;
    public String data_id;
    public String id;
    public boolean isHot;
    public boolean isLiked;
    public boolean isNew;
    public String like_count;
    public String precid;
    public NewCommentItem precmt;
    public String preuid;
    public int status;
    public User user;
    public String voicetime;
    public String voiceuri;
}
