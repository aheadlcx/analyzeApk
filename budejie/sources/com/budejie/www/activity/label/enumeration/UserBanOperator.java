package com.budejie.www.activity.label.enumeration;

import com.budejie.www.bean.CommentItem;

public class UserBanOperator {
    private CommentItem commentItem;
    private String operationType;

    public CommentItem getCommentItem() {
        return this.commentItem;
    }

    public void setCommentItem(CommentItem commentItem) {
        this.commentItem = commentItem;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String str) {
        this.operationType = str;
    }
}
