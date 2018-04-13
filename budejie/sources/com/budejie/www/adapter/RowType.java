package com.budejie.www.adapter;

import cn.v6.sixrooms.constants.CommonInts;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.budejie.www.R$styleable;

public enum RowType {
    IMAGE_ROW(10),
    TXT_ROW(29),
    TXT_LINK(28),
    SOUND_ROW(31),
    VIDEO_ROW(41),
    IMAGE_VOICE_VIDEO_ROW(82),
    RICH_ROW(51),
    REPOST_ROW(61),
    HISTORY_HOT_POST_ROW(71),
    AD_ROW(10001),
    AD_SDK_ROW(CommonInts.GT_ERROR),
    AD_LIVE_ROW(SystemMessageConstants.USER_CANCEL_CODE),
    SHENHE_OR_SUIJI_ROW(10),
    PINNERHEAD_ROW(10),
    COMMENT_IMAGE_ROW(110),
    COMMENT_TXT_ROW(129),
    COMMENT_RICH_TXT_ROW(151),
    COMMENT_SOUND_ROW(R$styleable.Theme_Custom_new_item_login_qq_bg),
    COMMENT_VIDEO_ROW(R$styleable.Theme_Custom_myinfo_setting_bg),
    COMMENT_REPORT_ROW(161),
    LOGIN_ROW(1),
    SHARE_ROW(2),
    RECOMMEND_ROW(3),
    INVITE_ROW(4),
    USER_INFO_COLLECT_ROW_AGE_GROUP(5),
    USER_INFO_COLLECT_ROW_GENDER(6),
    USER_INFO_COLLECT_ROW_EDUCATION(7),
    POSTS_ADV_ROW(10000),
    OPERATION_ROW(8),
    UPDATE_APP_ROW(9),
    POST_DETAIL_HEAD_ROW(11),
    SHARE_REPOST_ROW(62),
    SHARE_RICH_ROW(52),
    SHARE_IMAGE_VOICE_VIDEO_ROW(83),
    RECOMMEND_VIDEO_ROW(80),
    SHARE_TXT_ROW(30);
    
    private int type;

    private RowType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public static RowType valueOf(int i) {
        switch (i) {
            case 10:
                return IMAGE_ROW;
            case 29:
                return TXT_ROW;
            case 31:
                return SOUND_ROW;
            case 41:
                return VIDEO_ROW;
            case 51:
                return RICH_ROW;
            case 61:
                return REPOST_ROW;
            default:
                return IMAGE_ROW;
        }
    }
}
