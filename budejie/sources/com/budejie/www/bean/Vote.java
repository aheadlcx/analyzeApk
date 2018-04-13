package com.budejie.www.bean;

import java.io.Serializable;

public class Vote implements Serializable {
    public boolean isVoted;
    public String name;
    public String vid;
    public int vote_num;

    public String getVoteNumStr() {
        String str = this.vote_num + "";
        if (this.vote_num < 1000) {
            return str;
        }
        return ((((double) Math.round((((double) this.vote_num) / 1000.0d) * 10.0d)) / 10.0d) + "K").replace(".0", "");
    }
}
