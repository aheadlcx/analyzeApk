package cn.xiaochuankeji.tieba.ui.chat.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import cn.xiaochuankeji.tieba.api.hollow.HollowService;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.data.ChatRoom;
import cn.xiaochuankeji.tieba.push.data.ChatUser;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.chat.ChatActivity;
import cn.xiaochuankeji.tieba.ui.chat.FlowSessionActivity;
import cn.xiaochuankeji.tieba.ui.hollow.data.MemberDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wcdb.database.SQLiteDatabase;

public final class b {
    public static void a(Context context, @NonNull Member member, boolean z) {
        Member q = a.g().q();
        XSession a = e.a(1, member.getId());
        if (a == null) {
            a = new XSession();
            a.session_type = 1;
            ChatUser chatUser = new ChatUser();
            chatUser.id = q.getId();
            chatUser.avatar = q.getAvatarID();
            chatUser.gender = q.getGender();
            chatUser.name = q.getName();
            chatUser.official = q.getOfficial();
            a.x_mask = chatUser;
            a.x_room = new ChatRoom();
            a.unread = 0;
            a.weight = 0;
            a.time = System.currentTimeMillis() / 1000;
        }
        ChatUser chatUser2 = new ChatUser();
        chatUser2.id = member.getId();
        chatUser2.avatar = member.getAvatarID();
        chatUser2.gender = member.getGender();
        chatUser2.name = member.getName();
        chatUser2.official = member.getOfficial();
        a.x_other = chatUser2;
        a.x_sid = chatUser2.id;
        a(context, a, z);
    }

    public static void a(Context context, XSession xSession, boolean z) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("session", xSession);
        intent.putExtra("_show_keyboard", z);
        if (!(context instanceof Activity)) {
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        context.startActivity(intent);
    }

    public static void a(final Activity activity, RoomDataBean roomDataBean, MemberDataBean memberDataBean, MemberDataBean memberDataBean2) {
        if (roomDataBean != null && memberDataBean2 != null) {
            ChatRoom a = e.a(roomDataBean.id, 2);
            final ChatUser chatUser = new ChatUser();
            chatUser.id = memberDataBean2.xid;
            chatUser.avatar = memberDataBean2.avatar;
            chatUser.gender = memberDataBean2.gender;
            chatUser.name = memberDataBean2.name;
            if (a == null) {
                final ChatRoom chatRoom = new ChatRoom();
                chatRoom.room_id = roomDataBean.id;
                chatRoom.room_type = 2;
                chatRoom.room_name = roomDataBean.subject;
                chatRoom.room_data = JSON.parseObject(JSON.toJSONString(roomDataBean));
                if (memberDataBean != null) {
                    ChatUser chatUser2 = new ChatUser();
                    chatUser2.id = memberDataBean.xid;
                    chatUser2.avatar = memberDataBean.avatar;
                    chatUser2.gender = memberDataBean.gender;
                    chatUser2.name = memberDataBean.name;
                    chatRoom.room_mask = chatUser2;
                    e.a(chatRoom);
                    b(activity, chatRoom, chatUser);
                    return;
                }
                g.a(activity);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("xroom_id", Long.valueOf(roomDataBean.id));
                ((HollowService) c.b(HollowService.class)).createAccount(jSONObject).a(rx.a.b.a.a()).a(new rx.e<JSONObject>() {
                    public /* synthetic */ void onNext(Object obj) {
                        a((JSONObject) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        if (!(activity == null || activity.isFinishing())) {
                            g.c(activity);
                        }
                        cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                    }

                    public void a(JSONObject jSONObject) {
                        if (!(activity == null || activity.isFinishing())) {
                            g.c(activity);
                        }
                        if (jSONObject != null) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("xmember");
                            ChatUser chatUser = new ChatUser();
                            chatUser.id = jSONObject2.getLongValue("xid");
                            chatUser.avatar = jSONObject2.getLongValue("avatar");
                            chatUser.gender = jSONObject2.getIntValue("gender");
                            chatUser.name = jSONObject2.getString("name");
                            chatRoom.room_mask = chatUser;
                            e.a(chatRoom);
                            b.b(activity, chatRoom, chatUser);
                        }
                    }
                });
                return;
            }
            b(activity, a, chatUser);
        }
    }

    private static void b(Context context, ChatRoom chatRoom, ChatUser chatUser) {
        XSession a = e.a(chatUser.id);
        if (a == null) {
            a = new XSession();
            a.session_id = 0;
            a.unread = 0;
            a.time = System.currentTimeMillis() / 1000;
            a.status = 0;
            a.weight = 0;
        }
        a.session_type = 2;
        a.x_other = chatUser;
        a.x_room_id = chatRoom.room_id;
        a.x_room = chatRoom;
        a.x_mask = chatRoom.room_mask;
        a.x_sid = chatUser.id;
        a(context, a, true);
    }

    public static void a(Context context) {
        Intent intent = new Intent(context, FlowSessionActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        context.startActivity(intent);
    }
}
