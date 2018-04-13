package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;
import cn.v6.sixrooms.surfaceanim.util.FrescoUtil;
import cn.v6.sixrooms.utils.BitmapUtils;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class ViewerAdapter extends BaseAdapter {
    private List<UserInfoBean> a;
    private LayoutInflater b;
    private Context c;
    private int d = 0;
    private int e = 0;
    private int f = 0;

    static class a {
        SimpleDraweeView a;
        ImageView b;
        ImageView c;
        ImageView d;
        TextView e;
        ImageView f;
        SimpleDraweeView g;
        ImageView h;
        ImageView i;
        TextView j;
        ImageView k;
        ImageView l;
        ImageView m;
        ImageView n;
        ImageView o;

        a() {
        }
    }

    public ViewerAdapter(Context context, List<UserInfoBean> list) {
        this.c = context;
        this.a = list;
        this.b = (LayoutInflater) this.c.getSystemService("layout_inflater");
        this.e = (int) (context.getResources().getDimension(R.dimen.star_dispatch) / GlobleValue.density);
        this.f = (int) (context.getResources().getDimension(R.dimen.star_edge) / GlobleValue.density);
    }

    public void state(int i) {
        this.d = i;
    }

    public boolean hasStableIds() {
        return false;
    }

    private void a(ImageView imageView, String str, Bitmap bitmap) {
        Bitmap bitmap2 = null;
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("7116")) {
                bitmap2 = BitmapUtils.createBitmap(bitmap, BitmapFactory.decodeResource(this.c.getResources(), R.drawable.rooms_third_star), 1, this.e, this.f);
            } else if (str.contains("7117")) {
                bitmap2 = BitmapUtils.createBitmap(bitmap, BitmapFactory.decodeResource(this.c.getResources(), R.drawable.rooms_third_star), 2, this.e, this.f);
            } else if (str.contains("7118")) {
                bitmap2 = BitmapUtils.createBitmap(bitmap, BitmapFactory.decodeResource(this.c.getResources(), R.drawable.rooms_third_star), 3, this.e, this.f);
            } else if (str.contains("7119")) {
                bitmap2 = BitmapUtils.createBitmap(bitmap, BitmapFactory.decodeResource(this.c.getResources(), R.drawable.rooms_third_star), 4, this.e, this.f);
            } else if (str.contains("7120")) {
                bitmap2 = BitmapUtils.createBitmap(bitmap, BitmapFactory.decodeResource(this.c.getResources(), R.drawable.rooms_third_star), 5, this.e, this.f);
            }
        }
        if (bitmap2 != null) {
            bitmap = bitmap2;
        }
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(0);
    }

    private static void a(String str, a aVar, int i) {
        if (str.contains("7569")) {
            if (i == 0) {
                aVar.a.setImageResource(R.drawable.rooms_third_guard_offline);
            } else if (i == 1) {
                aVar.a.setImageResource(R.drawable.rooms_third_prop_silver_guard);
            }
        } else if (!str.contains("7570")) {
        } else {
            if (i == 0) {
                aVar.a.setImageResource(R.drawable.rooms_third_guard_offline_gold);
            } else if (i == 1) {
                aVar.a.setImageResource(R.drawable.rooms_third_prop_gold_guard);
            }
        }
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        UserInfoBean userInfoBean = (UserInfoBean) this.a.get(i);
        if (view != null) {
            aVar = (a) view.getTag();
        } else {
            view = this.b.inflate(R.layout.phone_room_spectator_list_item, null);
            a aVar2 = new a();
            aVar2.a = (SimpleDraweeView) view.findViewById(R.id.iv_identity);
            aVar2.b = (ImageView) view.findViewById(R.id.anchor_flag);
            aVar2.c = (ImageView) view.findViewById(R.id.iv_level);
            aVar2.d = (ImageView) view.findViewById(R.id.iv_vip);
            aVar2.e = (TextView) view.findViewById(R.id.tv_rid);
            aVar2.f = (ImageView) view.findViewById(R.id.iv_proxy_state);
            aVar2.g = (SimpleDraweeView) view.findViewById(R.id.iv_family);
            aVar2.h = (ImageView) view.findViewById(R.id.iv_green_card);
            aVar2.i = (ImageView) view.findViewById(R.id.iv_yellow_card);
            aVar2.j = (TextView) view.findViewById(R.id.tv_name);
            aVar2.k = (ImageView) view.findViewById(R.id.viewer_indicator);
            aVar2.l = (ImageView) view.findViewById(R.id.iv_love);
            aVar2.m = (ImageView) view.findViewById(R.id.iv_guard);
            aVar2.n = (ImageView) view.findViewById(R.id.iv_star);
            aVar2.o = (ImageView) view.findViewById(R.id.iv_song);
            view.setTag(aVar2);
            aVar = aVar2;
        }
        try {
            Integer valueOf = Integer.valueOf(Integer.parseInt(userInfoBean.getUrid()));
            String priv;
            int userIdentity;
            String flag;
            int intValue;
            boolean z;
            int proxyState;
            Object familyNum;
            if (valueOf.intValue() == 0 || ((valueOf.intValue() >= 30000000 && valueOf.intValue() < 60000000) || (valueOf.intValue() >= 200000000 && valueOf.intValue() < 900000000))) {
                aVar.e.setText("");
                priv = userInfoBean.getPriv();
                userIdentity = userInfoBean.getUserIdentity();
                aVar.j.setText(userInfoBean.getUname());
                flag = userInfoBean.getFlag();
                if (flag == null) {
                    intValue = Integer.valueOf(flag).intValue();
                } else {
                    intValue = -1;
                }
                if (this.d == 30) {
                    aVar.a.setImageURI(Uri.parse(userInfoBean.getUserpic()));
                    if (userIdentity != 5 || userIdentity == 9) {
                        aVar.b.setVisibility(0);
                        z = true;
                    } else {
                        aVar.b.setVisibility(8);
                        z = true;
                    }
                } else if (intValue != 1) {
                    a(priv, aVar, intValue);
                    z = true;
                } else {
                    if (intValue == 0) {
                        a(priv, aVar, intValue);
                        z = false;
                    }
                    z = true;
                }
                if (z) {
                    view.setClickable(true);
                    aVar.k.setVisibility(8);
                    aVar.j.setTextColor(-7829368);
                    aVar.e.setVisibility(8);
                    aVar.c.setVisibility(8);
                } else {
                    view.setClickable(false);
                    aVar.k.setVisibility(0);
                    aVar.j.setTextColor(-16777216);
                    aVar.e.setVisibility(0);
                    if (userIdentity != 5 || userIdentity == 9) {
                        aVar.c.setImageResource(DrawableResourceUtils.getStarLevelImageResource(userInfoBean.getAnchorLevel()));
                    } else {
                        int wealthLevel = userInfoBean.getWealthLevel();
                        if (wealthLevel < 27) {
                            wealthLevel = FortuneUtils.getFortuneLevelUrl(String.valueOf(wealthLevel));
                            if (wealthLevel == -1) {
                                aVar.c.setVisibility(4);
                            } else {
                                a(aVar.c, priv, BitmapFactory.decodeResource(this.c.getResources(), wealthLevel));
                            }
                        } else {
                            Object customWealthRankImg = DrawableResourceUtils.getCustomWealthRankImg(userInfoBean.getUid());
                            if (TextUtils.isEmpty(customWealthRankImg)) {
                                aVar.c.setVisibility(4);
                            } else {
                                FrescoUtil.asyncGetBitmap(customWealthRankImg, new l(this, aVar, priv));
                            }
                        }
                    }
                }
                if (TextUtils.isEmpty(priv)) {
                    if (priv.contains("7104")) {
                        aVar.d.setVisibility(0);
                        aVar.d.setBackgroundResource(R.drawable.rooms_third_violet_vip);
                    } else if (priv.contains("7105")) {
                        aVar.d.setVisibility(8);
                    } else {
                        aVar.d.setVisibility(0);
                        aVar.d.setBackgroundResource(R.drawable.rooms_third_yellow_vip);
                    }
                    if (priv.contains("7559")) {
                        aVar.h.setVisibility(8);
                    } else {
                        aVar.h.setVisibility(0);
                        aVar.h.setBackgroundResource(R.drawable.rooms_third_green_vip);
                    }
                    if (priv.contains("7859")) {
                        aVar.i.setVisibility(8);
                    } else {
                        aVar.i.setVisibility(0);
                        aVar.i.setBackgroundResource(R.drawable.rooms_third_yellow_card_vip);
                    }
                    if (priv.contains("7858")) {
                        aVar.o.setVisibility(8);
                    } else {
                        aVar.o.setVisibility(0);
                        aVar.o.setBackgroundResource(R.drawable.rooms_third_music_talent);
                    }
                    if (priv.contains("7122")) {
                        aVar.l.setVisibility(8);
                    } else {
                        aVar.l.setVisibility(0);
                        aVar.l.setBackgroundResource(R.drawable.rooms_third_love);
                    }
                    if (!priv.contains("7569") && intValue != 0) {
                        aVar.m.setVisibility(0);
                        aVar.m.setBackgroundResource(R.drawable.rooms_third_guard_silver);
                    } else if (priv.contains("7570") || intValue == 0) {
                        aVar.m.setVisibility(8);
                    } else {
                        aVar.m.setVisibility(0);
                        aVar.m.setBackgroundResource(R.drawable.rooms_third_guard_gold);
                    }
                    if (priv.contains("7827")) {
                        aVar.n.setVisibility(0);
                        aVar.n.setBackgroundResource(R.drawable.rooms_third_rob_star_yellow);
                    } else if (priv.contains("7828")) {
                        aVar.n.setVisibility(0);
                        aVar.n.setBackgroundResource(R.drawable.rooms_third_rob_star_pink);
                    } else if (priv.contains("7829")) {
                        aVar.n.setVisibility(8);
                    } else {
                        aVar.n.setVisibility(0);
                        aVar.n.setBackgroundResource(R.drawable.rooms_third_rob_star_yellow);
                    }
                } else {
                    aVar.d.setVisibility(8);
                    aVar.h.setVisibility(8);
                }
                proxyState = userInfoBean.getProxyState();
                if (proxyState == 1) {
                    aVar.f.setVisibility(0);
                    aVar.f.setBackgroundResource(R.drawable.rooms_third_sell);
                } else if (proxyState != 2) {
                    aVar.f.setVisibility(0);
                    aVar.f.setBackgroundResource(R.drawable.phone_room_selling_assistant);
                } else {
                    aVar.f.setVisibility(8);
                }
                familyNum = userInfoBean.getFamilyNum();
                if (!TextUtils.isEmpty(familyNum) || intValue == 0) {
                    aVar.g.setVisibility(8);
                } else {
                    aVar.g.setVisibility(0);
                    aVar.g.setImageURI(Uri.parse(familyNum));
                }
                aVar.k.setBackgroundResource(R.drawable.rooms_third_unfold);
                return view;
            }
            aVar.e.setText("(" + userInfoBean.getUrid() + ")");
            priv = userInfoBean.getPriv();
            userIdentity = userInfoBean.getUserIdentity();
            aVar.j.setText(userInfoBean.getUname());
            flag = userInfoBean.getFlag();
            if (flag == null) {
                intValue = -1;
            } else {
                intValue = Integer.valueOf(flag).intValue();
            }
            if (this.d == 30) {
                aVar.a.setImageURI(Uri.parse(userInfoBean.getUserpic()));
                if (userIdentity != 5) {
                }
                aVar.b.setVisibility(0);
                z = true;
            } else if (intValue != 1) {
                if (intValue == 0) {
                    a(priv, aVar, intValue);
                    z = false;
                }
                z = true;
            } else {
                a(priv, aVar, intValue);
                z = true;
            }
            if (z) {
                view.setClickable(true);
                aVar.k.setVisibility(8);
                aVar.j.setTextColor(-7829368);
                aVar.e.setVisibility(8);
                aVar.c.setVisibility(8);
            } else {
                view.setClickable(false);
                aVar.k.setVisibility(0);
                aVar.j.setTextColor(-16777216);
                aVar.e.setVisibility(0);
                if (userIdentity != 5) {
                }
                aVar.c.setImageResource(DrawableResourceUtils.getStarLevelImageResource(userInfoBean.getAnchorLevel()));
            }
            if (TextUtils.isEmpty(priv)) {
                aVar.d.setVisibility(8);
                aVar.h.setVisibility(8);
            } else {
                if (priv.contains("7104")) {
                    aVar.d.setVisibility(0);
                    aVar.d.setBackgroundResource(R.drawable.rooms_third_violet_vip);
                } else if (priv.contains("7105")) {
                    aVar.d.setVisibility(8);
                } else {
                    aVar.d.setVisibility(0);
                    aVar.d.setBackgroundResource(R.drawable.rooms_third_yellow_vip);
                }
                if (priv.contains("7559")) {
                    aVar.h.setVisibility(8);
                } else {
                    aVar.h.setVisibility(0);
                    aVar.h.setBackgroundResource(R.drawable.rooms_third_green_vip);
                }
                if (priv.contains("7859")) {
                    aVar.i.setVisibility(8);
                } else {
                    aVar.i.setVisibility(0);
                    aVar.i.setBackgroundResource(R.drawable.rooms_third_yellow_card_vip);
                }
                if (priv.contains("7858")) {
                    aVar.o.setVisibility(8);
                } else {
                    aVar.o.setVisibility(0);
                    aVar.o.setBackgroundResource(R.drawable.rooms_third_music_talent);
                }
                if (priv.contains("7122")) {
                    aVar.l.setVisibility(8);
                } else {
                    aVar.l.setVisibility(0);
                    aVar.l.setBackgroundResource(R.drawable.rooms_third_love);
                }
                if (!priv.contains("7569")) {
                }
                if (priv.contains("7570")) {
                }
                aVar.m.setVisibility(8);
                if (priv.contains("7827")) {
                    aVar.n.setVisibility(0);
                    aVar.n.setBackgroundResource(R.drawable.rooms_third_rob_star_yellow);
                } else if (priv.contains("7828")) {
                    aVar.n.setVisibility(0);
                    aVar.n.setBackgroundResource(R.drawable.rooms_third_rob_star_pink);
                } else if (priv.contains("7829")) {
                    aVar.n.setVisibility(8);
                } else {
                    aVar.n.setVisibility(0);
                    aVar.n.setBackgroundResource(R.drawable.rooms_third_rob_star_yellow);
                }
            }
            proxyState = userInfoBean.getProxyState();
            if (proxyState == 1) {
                aVar.f.setVisibility(0);
                aVar.f.setBackgroundResource(R.drawable.rooms_third_sell);
            } else if (proxyState != 2) {
                aVar.f.setVisibility(8);
            } else {
                aVar.f.setVisibility(0);
                aVar.f.setBackgroundResource(R.drawable.phone_room_selling_assistant);
            }
            familyNum = userInfoBean.getFamilyNum();
            if (TextUtils.isEmpty(familyNum)) {
            }
            aVar.g.setVisibility(8);
            aVar.k.setBackgroundResource(R.drawable.rooms_third_unfold);
            return view;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
