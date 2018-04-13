package com.budejie.www.bean;

public class MyNewsItem {
    private CmtMyTieziItem cmtMyTieziItem;
    public String ctime;
    private DingOrCaiCommentNewsItem dingOrCaiCommentNewsItem;
    private FriendItem friendItem;
    private HotComment hotComment;
    private HuodongItem huodongItem;
    private MyNewLabelItem labelItem;
    private MentionedItem mentionedItem;
    private ListItemObject postItemObject;
    private PraiseNewsItem praiseNewsItem;
    private ReplyNewsItem replyNewsItem;
    private SystemNewsItem systemNewsItem;
    private String type;

    public ListItemObject getListItemObject() {
        return this.postItemObject;
    }

    public void setListItemObject(ListItemObject listItemObject) {
        this.postItemObject = listItemObject;
    }

    public HotComment getHotCommentItem() {
        return this.hotComment;
    }

    public void setHotCommentItem(HotComment hotComment) {
        this.hotComment = hotComment;
    }

    public MentionedItem getMentionedItem() {
        return this.mentionedItem;
    }

    public void setMentionedItem(MentionedItem mentionedItem) {
        this.mentionedItem = mentionedItem;
    }

    public FriendItem getFriendItem() {
        return this.friendItem;
    }

    public void setFriendItem(FriendItem friendItem) {
        this.friendItem = friendItem;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public CmtMyTieziItem getCmtMyTieziItem() {
        return this.cmtMyTieziItem;
    }

    public void setCmtMyTieziItem(CmtMyTieziItem cmtMyTieziItem) {
        this.cmtMyTieziItem = cmtMyTieziItem;
    }

    public SystemNewsItem getSystemNewsItem() {
        return this.systemNewsItem;
    }

    public void setSystemNewsItem(SystemNewsItem systemNewsItem) {
        this.systemNewsItem = systemNewsItem;
    }

    public DingOrCaiCommentNewsItem getDingOrCaiCommentNewsItem() {
        return this.dingOrCaiCommentNewsItem;
    }

    public void setDingOrCaiCommentNewsItem(DingOrCaiCommentNewsItem dingOrCaiCommentNewsItem) {
        this.dingOrCaiCommentNewsItem = dingOrCaiCommentNewsItem;
    }

    public ReplyNewsItem getReplyNewsItem() {
        return this.replyNewsItem;
    }

    public void setReplyNewsItem(ReplyNewsItem replyNewsItem) {
        this.replyNewsItem = replyNewsItem;
    }

    public HuodongItem getHuodongItem() {
        return this.huodongItem;
    }

    public void setHuodongItem(HuodongItem huodongItem) {
        this.huodongItem = huodongItem;
    }

    public PraiseNewsItem getPraiseNewsItem() {
        return this.praiseNewsItem;
    }

    public void setPraiseNewsItem(PraiseNewsItem praiseNewsItem) {
        this.praiseNewsItem = praiseNewsItem;
    }

    public MyNewLabelItem getLabelItem() {
        return this.labelItem;
    }

    public void setLabelItem(MyNewLabelItem myNewLabelItem) {
        this.labelItem = myNewLabelItem;
    }
}
