package cn.xiaochuankeji.tieba.ui.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.h.d;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.data.XMessage;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.g;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;
import com.alibaba.fastjson.JSON;
import com.jakewharton.a.b.a;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MessageAdapter extends Adapter<MessageHolder> {
    private LinkedList<XSession> a = new LinkedList();

    static class MessageHolder extends ViewHolder {
        @BindView
        WebImageView avatar;
        @BindView
        AppCompatTextView content;
        @BindView
        BadgeTextView crumb;
        @BindView
        AppCompatTextView name;
        @BindView
        AppCompatTextView time;

        MessageHolder(View view) {
            super(view);
            ButterKnife.a(this, view);
        }
    }

    public class MessageHolder_ViewBinding implements Unbinder {
        private MessageHolder b;

        @UiThread
        public MessageHolder_ViewBinding(MessageHolder messageHolder, View view) {
            this.b = messageHolder;
            messageHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
            messageHolder.time = (AppCompatTextView) b.b(view, R.id.time, "field 'time'", AppCompatTextView.class);
            messageHolder.name = (AppCompatTextView) b.b(view, R.id.name, "field 'name'", AppCompatTextView.class);
            messageHolder.crumb = (BadgeTextView) b.b(view, R.id.crumb, "field 'crumb'", BadgeTextView.class);
            messageHolder.content = (AppCompatTextView) b.b(view, R.id.content, "field 'content'", AppCompatTextView.class);
        }

        @CallSuper
        public void a() {
            MessageHolder messageHolder = this.b;
            if (messageHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            messageHolder.avatar = null;
            messageHolder.time = null;
            messageHolder.name = null;
            messageHolder.crumb = null;
            messageHolder.content = null;
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((MessageHolder) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public MessageHolder a(ViewGroup viewGroup, int i) {
        return new MessageHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false));
    }

    public void a(final MessageHolder messageHolder, int i) {
        final Context context = messageHolder.itemView.getContext();
        final XSession xSession = (XSession) this.a.get(i);
        String str;
        if (xSession.session_type == 4) {
            a.a(messageHolder.itemView).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
                final /* synthetic */ MessageAdapter b;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    e.d();
                    cn.xiaochuankeji.tieba.ui.chat.a.b.a(context);
                    d.a().d();
                    h.a(context, "zy_event_message_tab_letter", "点击私信列表");
                }
            });
            messageHolder.time.setText(cn.xiaochuankeji.tieba.d.h.a(xSession.time * 1000));
            messageHolder.name.setText("树洞消息");
            messageHolder.crumb.setBadgeCount(xSession.unread);
            str = xSession.isSelfMsg() ? "我：" : "";
            if (xSession.x_msg.msg_type == 2) {
                messageHolder.content.setText(String.format("%s[图片]", new Object[]{str}));
            } else if (XMessage.isSupport(xSession.x_msg.msg_type)) {
                messageHolder.content.setText(String.format("%s%s", new Object[]{str, a(xSession.x_msg)}));
            } else {
                messageHolder.content.setText("该类型消息暂不支持，请升级到最新版本");
            }
            messageHolder.avatar.setImageResource(R.drawable.default_tree);
            c.a.b.a(messageHolder.name, 0, 0, 0, 0);
            a.b(messageHolder.itemView).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
                final /* synthetic */ MessageAdapter d;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    PopupMenu popupMenu = new PopupMenu(context, messageHolder.name);
                    popupMenu.getMenu().add(0, 1, 0, "清空全部树洞消息");
                    popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public boolean onMenuItemClick(MenuItem menuItem) {
                            f.a("确定清空树洞消息?", "清空树洞消息后，你将失去所有已经收到的树洞消息。", (Activity) context, new f.a(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                    if (z) {
                                        e.h(xSession);
                                        e.c();
                                        d.a().d();
                                        cn.xiaochuankeji.tieba.push.d.e(xSession);
                                        this.a.a.d.a(1);
                                    }
                                }
                            }, true);
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
            return;
        }
        if (xSession.x_msg != null) {
            messageHolder.avatar.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(xSession.x_other.id, xSession.x_other.avatar));
            a.a(messageHolder.avatar).d(200, TimeUnit.MILLISECONDS).a(new rx.b.b<Void>(this) {
                final /* synthetic */ MessageAdapter c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    if (xSession.session_type == 1 && xSession.x_other.id > 1999 && xSession.x_sid != cn.xiaochuankeji.tieba.background.a.g().c()) {
                        Member member = new Member(xSession.x_other.id);
                        member.setAvatarID(xSession.x_other.avatar);
                        member.setName(xSession.x_other.name);
                        MemberDetailActivity.a(context, xSession.x_sid, 0, 3, 0);
                    }
                }
            });
            messageHolder.name.setText(xSession.x_other.name);
            if (xSession.x_other.official == 1) {
                c.a.b.a(messageHolder.name, 0, 0, R.drawable.personal_guanfang, 0);
            } else {
                c.a.b.a(messageHolder.name, 0, 0, 0, 0);
            }
        } else {
            c.a.b.a(messageHolder.name, 0, 0, 0, 0);
        }
        messageHolder.time.setText(cn.xiaochuankeji.tieba.d.h.a(xSession.time * 1000));
        messageHolder.crumb.setBadgeCount(xSession.unread);
        str = xSession.isSelfMsg() ? "我：" : "";
        if (xSession.x_msg.msg_type == 2) {
            messageHolder.content.setText(String.format("%s[图片]", new Object[]{str}));
        } else if (XMessage.isSupport(xSession.x_msg.msg_type)) {
            messageHolder.content.setText(String.format("%s%s", new Object[]{str, a(xSession.x_msg)}));
        } else {
            messageHolder.content.setText("该类型消息暂不支持，请升级到最新版本");
        }
        a.a(messageHolder.itemView).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
            final /* synthetic */ MessageAdapter d;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                e.j(xSession);
                xSession.unread = 0;
                messageHolder.crumb.setBadgeCount(0);
                cn.xiaochuankeji.tieba.ui.chat.a.b.a(context, xSession, false);
                d.a().d();
                h.a(context, "zy_event_message_tab_letter", "点击私信列表");
            }
        });
        a.b(messageHolder.itemView).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
            final /* synthetic */ MessageAdapter d;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                new cn.xiaochuankeji.tieba.ui.chat.a.a((Activity) context, new cn.xiaochuankeji.tieba.ui.chat.a.a.a(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        g.a((Activity) context);
                    }

                    public void b() {
                        g.c((Activity) context);
                    }

                    public void c() {
                        e.h(xSession);
                        d.a().d();
                        cn.xiaochuankeji.tieba.push.d.e(xSession);
                        this.a.d.a(xSession.session_type);
                    }

                    public void d() {
                        e.b(xSession);
                        cn.xiaochuankeji.tieba.push.d.d(xSession);
                        d.a().d();
                        this.a.d.a(xSession.session_type);
                    }
                }).a(xSession, messageHolder.name, false);
            }
        });
    }

    private String a(XMessage xMessage) {
        if (xMessage == null) {
            return "";
        }
        String str = xMessage.content;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (xMessage.msg_type == 1) {
            return str;
        }
        if (xMessage.msg_type == 3) {
            return "[语音]";
        }
        try {
            return JSON.parseObject(str).getString("msg");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public void a() {
        this.a.clear();
        notifyDataSetChanged();
    }

    public void a(int i) {
        Collection b = e.b(i);
        this.a.clear();
        this.a.addAll(b);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.a.size();
    }
}
