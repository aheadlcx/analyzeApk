package cn.v6.sixrooms.surfaceanim.protocol;

import java.io.Serializable;

public class ProtocolBean implements Serializable {
    private String a;
    private int b;
    private SceneBean c;

    public String getAuthor() {
        return this.a;
    }

    public void setAuthor(String str) {
        this.a = str;
    }

    public int getVersion() {
        return this.b;
    }

    public void setVersion(int i) {
        this.b = i;
    }

    public SceneBean getScene() {
        return this.c;
    }

    public void setScene(SceneBean sceneBean) {
        this.c = sceneBean;
    }
}
