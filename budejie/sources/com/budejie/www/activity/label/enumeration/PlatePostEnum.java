package com.budejie.www.activity.label.enumeration;

import cn.v6.sixrooms.room.statistic.StatisticCodeTable;

public enum PlatePostEnum {
    ADD_ESSENCE("topic_jing_add"),
    REMOVE_ESSENCE("topic_jing_del"),
    REMOVE_FROM_PLATE("topic_del"),
    ADD_TO_PLATE("topic_add"),
    USER_BAN("user_ban"),
    CANCEL_USER_BAN("cancel_user_ban"),
    POST_TO_TOP("add"),
    POST_REMOVE_FROM_TOP("remove"),
    NEW_TYPE("new"),
    ESSENCE_TYPE("jingxuan"),
    HOT_TYPE(StatisticCodeTable.HOT),
    END("end"),
    VICE_MASTER_APPROVE("vice_master_approve"),
    VICE_MASTER_DENY("vice_master_deny"),
    ESSENCE_POST((String) 4),
    NO_ESSENCE_POST((String) 2),
    TOP_POST((String) 1),
    NO_TOP_POST((String) 0),
    TO_TOP_SUCCESS((String) 0),
    NEW_TAB_POSITION((String) 0),
    ESSENCE_TAB_POSITION((String) 1),
    MEMBER_TAB_POSITION((String) 3);
    
    private int code;
    private String value;

    private PlatePostEnum(int i) {
        this.code = i;
    }

    private PlatePostEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public int getCode() {
        return this.code;
    }
}
