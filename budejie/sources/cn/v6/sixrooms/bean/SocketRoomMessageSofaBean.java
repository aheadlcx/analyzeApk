package cn.v6.sixrooms.bean;

public class SocketRoomMessageSofaBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private SofaBean content;

    public SofaBean getContent() {
        return this.content;
    }

    public void setContent(SofaBean sofaBean) {
        this.content = sofaBean;
    }
}
