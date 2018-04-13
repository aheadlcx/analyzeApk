package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.bean.MessageBean;
import java.util.Map;

public class GamePlaneEndBean extends MessageBean {
    private static final long serialVersionUID = 9118129592272569844L;
    private String gid;
    private String outer;
    private Map<String, GamePlaneUser> users;

    public class GamePlaneUser {
        private String name;
        private String uid;
        private Map<String, String> win;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public Map<String, String> getWin() {
            return this.win;
        }

        public void setWin(Map<String, String> map) {
            this.win = map;
        }
    }

    public GamePlaneEndBean(String str, String str2, Map<String, GamePlaneUser> map) {
        this.gid = str;
        this.outer = str2;
        this.users = map;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public String getOuter() {
        return this.outer;
    }

    public void setOuter(String str) {
        this.outer = str;
    }

    public Map<String, GamePlaneUser> getUsers() {
        return this.users;
    }

    public void setUsers(Map<String, GamePlaneUser> map) {
        this.users = map;
    }
}
