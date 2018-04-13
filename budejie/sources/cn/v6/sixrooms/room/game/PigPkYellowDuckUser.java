package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.hall.BaseBean;

public class PigPkYellowDuckUser extends BaseBean {
    private Captain captain;
    private String num;

    public class Captain {
        private String alias;
        private String coin6rank;
        private String picuser;
        private String rid;
        private String uid;

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getRid() {
            return this.rid;
        }

        public void setRid(String str) {
            this.rid = str;
        }

        public String getAlias() {
            return this.alias;
        }

        public void setAlias(String str) {
            this.alias = str;
        }

        public String getCoin6rank() {
            return this.coin6rank;
        }

        public void setCoin6rank(String str) {
            this.coin6rank = str;
        }

        public String getPicuser() {
            return this.picuser;
        }

        public void setPicuser(String str) {
            this.picuser = str;
        }
    }

    public PigPkYellowDuckUser(String str) {
        this.num = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public Captain getCaptain() {
        return this.captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }
}
