package cn.v6.sixrooms.room.dialog;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.interfaces.RoomLiveCallBack;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.ToastUtils;

public class MoreDialog extends Dialog implements OnClickListener {
    private static final String b = MoreDialog.class.getSimpleName();
    MoreItemClickListener a;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private View g;
    private BaseRoomActivity h;
    private WrapRoomInfo i;
    private boolean j;
    private int k;
    private RoomLiveCallBack l;

    public interface MoreItemClickListener {
        void chooseSong();

        void definition();

        void fullScreen();

        void headLine();
    }

    public MoreDialog(BaseRoomActivity baseRoomActivity, WrapRoomInfo wrapRoomInfo, boolean z, int i) {
        super(baseRoomActivity, R.style.Transparent_OutClose_NoTitle);
        if (z) {
            this.g = View.inflate(baseRoomActivity, R.layout.dialog_more_live, null);
        } else {
            this.g = View.inflate(baseRoomActivity, R.layout.dialog_more, null);
        }
        setContentView(this.g);
        this.j = z;
        this.k = i;
        setLayout();
        this.h = baseRoomActivity;
        this.i = wrapRoomInfo;
        this.c = (TextView) this.g.findViewById(R.id.tv_choose_song);
        this.d = (TextView) this.g.findViewById(R.id.tv_head_line);
        if (!this.j) {
            this.e = (TextView) this.g.findViewById(R.id.tv_definition);
        }
        if (!this.j && getContext().getResources().getConfiguration().orientation == 1) {
            this.f = (TextView) this.g.findViewById(R.id.tv_full_screen);
            if (this.k != 0) {
                this.f.setVisibility(4);
            }
        }
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        if (!this.j) {
            this.e.setOnClickListener(this);
        }
        if (!this.j && getContext().getResources().getConfiguration().orientation == 1 && this.k == 0) {
            this.f.setOnClickListener(this);
        }
    }

    public void addLiveCallBack(RoomLiveCallBack roomLiveCallBack) {
        this.l = roomLiveCallBack;
    }

    public void show() {
        super.show();
        if (this.l != null) {
            Drawable drawable = this.h.getResources().getDrawable(R.drawable.dialog_more_only_hd);
            CharSequence charSequence = "高清";
            this.e.setTextColor(this.h.getResources().getColor(R.color.button_disable));
            if (this.l.getDefinitionStatus() != -1) {
                if (this.l.isChangeable()) {
                    this.e.setClickable(true);
                    if (this.l.getDefinitionStatus() == 0) {
                        drawable = this.h.getResources().getDrawable(R.drawable.dialog_more_hd_selector);
                    } else if (this.l.getDefinitionStatus() == 1) {
                        drawable = this.h.getResources().getDrawable(R.drawable.dialog_more_ld_selector);
                        charSequence = "流畅";
                    }
                    this.e.setTextColor(this.h.getResources().getColor(R.color.dialog_open_guard_text_selector));
                } else {
                    this.e.setClickable(false);
                    if (this.l.getDefinitionStatus() == 0) {
                        drawable = this.h.getResources().getDrawable(R.drawable.dialog_more_only_hd);
                    } else if (this.l.getDefinitionStatus() == 1) {
                        drawable = this.h.getResources().getDrawable(R.drawable.dialog_more_only_ld);
                        charSequence = "流畅";
                    }
                }
            }
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.e.setCompoundDrawables(null, drawable, null, null);
            this.e.setText(charSequence);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_choose_song) {
            this.a.chooseSong();
        } else if (id == R.id.tv_head_line) {
            this.a.headLine();
        } else if (id == R.id.tv_definition) {
            if (this.l != null && this.l.isChangeable()) {
                if (this.l.isChangeing()) {
                    ToastUtils.showToast("切换中，请稍等");
                } else {
                    if (this.l.getDefinitionStatus() == 0) {
                        ToastUtils.showToast("已切换为流畅");
                    } else {
                        ToastUtils.showToast("已切换为高清");
                    }
                    this.a.definition();
                }
            }
        } else if (id == R.id.tv_full_screen) {
            this.a.fullScreen();
        }
        dismiss();
    }

    public void setOnMoreItemClickListener(MoreItemClickListener moreItemClickListener) {
        this.a = moreItemClickListener;
    }

    public void setLayout() {
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        if (getContext().getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
            attributes.gravity = 5;
            attributes.width = DensityUtil.dip2px(150.0f);
            attributes.height = -1;
        } else {
            attributes.gravity = 80;
            attributes.width = -1;
            if (this.j) {
                attributes.height = DensityUtil.dip2px(144.0f);
            } else {
                attributes.height = DensityUtil.dip2px(232.0f);
            }
            if (!this.j) {
                if (this.k != 0) {
                    window.setBackgroundDrawableResource(R.color.dialog_land_bg);
                } else {
                    window.setBackgroundDrawableResource(R.drawable.room_chat_common_backgroud);
                }
            } else {
                return;
            }
        }
        window.setAttributes(attributes);
    }
}
