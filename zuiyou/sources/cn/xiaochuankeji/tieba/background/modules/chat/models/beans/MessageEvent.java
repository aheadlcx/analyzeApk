package cn.xiaochuankeji.tieba.background.modules.chat.models.beans;

public class MessageEvent {
    public static MessageEvent MESSAGE_POST_INSERT = new MessageEvent(MessageEventType.MESSAGE_POST_INSERT);
    private Object data;
    private MessageEventType eventType;
    private Object extraData;

    public enum MessageEventType {
        MESSAGE_WRAPPER_RECEIVE,
        MESSAGE_WRAPPER_SEND,
        MESSAGE_WRAPPERS_UPDATE,
        MESSAGE_ADD,
        MESSAGE_UPDATE,
        MESSAGE_LIST_SYNCED,
        MESSAGE_RELOAD,
        MESSAGE_ACCOUNT_LOGIN,
        MESSAGE_ACCOUNT_LOGOUT,
        MESSAGE_POST_INSERT,
        MESSAGE_POST_DELETE,
        MESSAGE_POST_HAS_DELETED,
        MESSAGE_POST_CANCEL_FAVORED,
        MESSAGE_POST_SHARE,
        MESSAGE_POST_CANCEL_LIKE,
        MESSAGE_UGC_CANCEL_LIKE,
        MESSAGE_DISABLE_RECOMMEND_CRUMB,
        MESSAGE_SHOW_RECOMMEND_CRUMB,
        MESSAGE_TOPIC_POST_UPDATE,
        MESSAGE_TOPIC_FOLLOWED_USERS,
        MESSAGE_TOPIC_TOGGLE_FOLLOW_ACTION,
        MESSAGE_SYSTEM_INSERT,
        MESSAGE_SYSTEM_UPDATE,
        MESSAGE_ACTION_CLICK_MESSAGE_TAB,
        MESSAGE_ACTION_NOTIFY_SCROLL_TO_TOP,
        MESSAGE_ACTION_NOTIFY_UPDATE_MSGS,
        MESSAGE_ILLEGAL_MESSAGE,
        MESSAGE_APP_UPDATE_STATE,
        MESSAGE_VOTE_AFTER_LOGIN,
        MESSAGE_LISTVIEW_SCROLL,
        MESSAGE_TEXT_VIEW_COLLAPSE,
        MESSAGE_COMMENT_TEXT_VIEW_COLLAPSE,
        MESSAGE_NIGHT_MODE_ON,
        MESSAGE_NIGHT_MODE_OFF,
        MESSAGE_TOPIC_TOGGLE,
        MESSAGE_EVENT_TOPIC_MODIFY,
        MESSAGE_FINISH_BIG_PIC,
        MESSAGE_BIGPIC_CONFIRM,
        MESSAGE_BIG_PIC_BACK_PRESSED,
        MESSAGE_EDIT_FINISH_WHEN_SELECTED_PIC,
        MESSAGE_EDIR_FINISH_IN_BIGPIC,
        MESSAGE_SELECT_PIC_ON_CONFIRM,
        MESSAGE_DELETE_PIC_PATH,
        MESSAGE_BE_FOLLOWED,
        MESSAGE_DUMMY_PROGRESS_OVER,
        MESSAGE_UPLOAD_PROGRESS,
        MESSAGE_SHOW_NEXT,
        MESSAGE_SHOW_DUMMY_PROGRESS_AND_SHOW_NEXT,
        MESSAGE_DAN_NOTIFY_VIDEO_REPLAY,
        MESSAGE_CANCEL_DANMU_LIKE_STATE,
        MESSAGE_INSERT_DANMU,
        MESSAGE_CLICK_COMMENT_PIC,
        MESSAGE_LONG_CLICK_COMMENT,
        MESSAGE_TOPIC_REFRESH,
        MESSAGE_TOPIC_REFRESH_FINISH,
        MESSAGE_TOPIC_FOLLOW_EMPTY;

        public String toString() {
            return "MessageEventType{" + name() + "}";
        }
    }

    public MessageEvent(MessageEventType messageEventType) {
        this.eventType = messageEventType;
    }

    public MessageEventType getEventType() {
        return this.eventType;
    }

    public void setEventType(MessageEventType messageEventType) {
        this.eventType = messageEventType;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setExtraData(Object obj) {
        this.extraData = obj;
    }

    public Object getExtraData() {
        return this.extraData;
    }
}
