package com.budejie.www.util;

import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.bean.Voted;
import com.budejie.www.c.l;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import java.util.Iterator;
import java.util.List;

public class ax {
    public static void a(List<ListItemObject> list, Context context) {
        List<Voted> a = new l(context).a();
        if (list != null && list.size() > 0 && a != null && a.size() > 0) {
            for (ListItemObject listItemObject : list) {
                try {
                    for (Voted voted : a) {
                        if (listItemObject.getWid().equals(voted.pid)) {
                            Iterator it = listItemObject.getVoteData().votes.iterator();
                            while (it.hasNext()) {
                                Vote vote = (Vote) it.next();
                                if (voted.vid.equals(vote.vid)) {
                                    vote.isVoted = true;
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void a(ListItemObject listItemObject, Context context) {
        try {
            for (Voted voted : new l(context).a()) {
                if (listItemObject.getWid().equals(voted.pid)) {
                    Iterator it = listItemObject.getVoteData().votes.iterator();
                    while (it.hasNext()) {
                        Vote vote = (Vote) it.next();
                        if (voted.vid.equals(vote.vid)) {
                            vote.isVoted = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(List<CommentItem> list, Context context) {
        List<Voted> a = new l(context).a();
        if (list != null && list.size() > 0 && a != null && a.size() > 0) {
            for (CommentItem commentItem : list) {
                try {
                    for (Voted voted : a) {
                        if (commentItem.getId().equals(voted.cid)) {
                            Iterator it = commentItem.getVoteData().votes.iterator();
                            while (it.hasNext()) {
                                Vote vote = (Vote) it.next();
                                if (voted.vid.equals(vote.vid)) {
                                    vote.isVoted = true;
                                    break;
                                }
                            }
                        }
                    }
                    List replyList = commentItem.getReplyList();
                    if (replyList != null && replyList.size() > 0) {
                        b(replyList, context);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void a(VoteData voteData, Context context) {
        if (voteData != null) {
            try {
                for (Voted voted : new l(context).a()) {
                    Iterator it = voteData.votes.iterator();
                    while (it.hasNext()) {
                        Vote vote = (Vote) it.next();
                        if (voted.vid.equals(vote.vid)) {
                            vote.isVoted = true;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String a(Context context, String str, String str2, String str3) {
        Object a = new l(context).a(str, str2, str3);
        if (TextUtils.isEmpty(a)) {
            BudejieApplication.a.a(RequstMethod.GET, j.f(str, str2, str3), new j(context), null);
        }
        return a;
    }
}
