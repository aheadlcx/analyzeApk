package com.budejie.www.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class VoteData implements Serializable {
    public String cid;
    public String pid;
    public ArrayList<Vote> votes;

    public boolean isVoted() {
        Iterator it = this.votes.iterator();
        while (it.hasNext()) {
            if (((Vote) it.next()).isVoted) {
                return true;
            }
        }
        return false;
    }

    public int getTotalCount() {
        Iterator it = this.votes.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((Vote) it.next()).vote_num + i;
        }
        return i;
    }
}
