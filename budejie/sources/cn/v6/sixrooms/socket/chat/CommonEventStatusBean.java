package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.bean.MessageBean;
import java.io.Serializable;
import java.util.List;

public class CommonEventStatusBean extends MessageBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String freeVoteMsg;
    private CommonEventStatusBean$GiftVoteMsg giftVoteMsg;
    private String goldInfo;
    private CommonEventStatusBean$TutorInfo tutorInfo;
    private List<CommonEventStatusBean$VoteMsgBean> voteMsg;

    public String getFreeVoteMsg() {
        return this.freeVoteMsg;
    }

    public void setFreeVoteMsg(String str) {
        this.freeVoteMsg = str;
    }

    public CommonEventStatusBean$GiftVoteMsg getGiftVoteMsg() {
        return this.giftVoteMsg;
    }

    public void setGiftVoteMsg(CommonEventStatusBean$GiftVoteMsg commonEventStatusBean$GiftVoteMsg) {
        this.giftVoteMsg = commonEventStatusBean$GiftVoteMsg;
    }

    public String getGoldInfo() {
        return this.goldInfo;
    }

    public void setGoldInfo(String str) {
        this.goldInfo = str;
    }

    public static long getSerialVersionUID() {
        return 1;
    }

    public CommonEventStatusBean$TutorInfo getTutorInfo() {
        return this.tutorInfo;
    }

    public void setTutorInfo(CommonEventStatusBean$TutorInfo commonEventStatusBean$TutorInfo) {
        this.tutorInfo = commonEventStatusBean$TutorInfo;
    }

    public List<CommonEventStatusBean$VoteMsgBean> getVoteMsg() {
        return this.voteMsg;
    }

    public void setVoteMsg(List<CommonEventStatusBean$VoteMsgBean> list) {
        this.voteMsg = list;
    }
}
