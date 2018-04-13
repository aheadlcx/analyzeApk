package com.budejie.www.busevent;

import com.budejie.www.bean.VoteData;

public class VoteEvent {
    public VoteData a;
    public VoteAction b;

    public enum VoteAction {
        VOTE
    }

    public VoteEvent(VoteAction voteAction, VoteData voteData) {
        this.b = voteAction;
        this.a = voteData;
    }
}
