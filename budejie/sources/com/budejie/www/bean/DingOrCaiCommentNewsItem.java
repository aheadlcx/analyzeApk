package com.budejie.www.bean;

import java.io.Serializable;

public class DingOrCaiCommentNewsItem extends BaseCommentItem implements Serializable {
    public boolean isCai;
    public CommentItemForNews r_cinfo;
    public User user;
}
