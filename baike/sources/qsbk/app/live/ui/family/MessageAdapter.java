package qsbk.app.live.ui.family;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.DateUtil;
import qsbk.app.live.R;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.live.widget.FamilyLevelView;

public class MessageAdapter extends Adapter<ViewHolder> {
    private List<Message> a;
    private Context b;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public FamilyLevelView fl_level;
        public SimpleDraweeView ivImage;
        public TextView tvAction;
        public TextView tvLevel;
        public TextView tvMessageType;
        public TextView tvName;
        public TextView tvTime;

        public ViewHolder(View view) {
            super(view);
            this.ivImage = (SimpleDraweeView) view.findViewById(R.id.iv_image);
            this.tvName = (TextView) view.findViewById(R.id.tv_name);
            this.tvLevel = (TextView) view.findViewById(R.id.tv_user_lv);
            this.tvMessageType = (TextView) view.findViewById(R.id.tv_message_type);
            this.tvTime = (TextView) view.findViewById(R.id.tv_time);
            this.tvAction = (TextView) view.findViewById(R.id.tv_action);
            this.fl_level = (FamilyLevelView) view.findViewById(R.id.fl_level);
        }
    }

    public MessageAdapter(Context context, List<Message> list) {
        this.a = list;
        this.b = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_family_message, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Message message = (Message) this.a.get(i);
        viewHolder.tvName.setText(message.getUserName());
        LevelHelper.setLevelText(viewHolder.tvLevel, message.getUserLevel());
        int userFamilyLevel = message.getUserFamilyLevel();
        if (TextUtils.isEmpty(message.getUserBadge())) {
            viewHolder.fl_level.setVisibility(8);
        } else {
            viewHolder.fl_level.setVisibility(0);
            viewHolder.fl_level.setLevelAndName(userFamilyLevel, message.getUserBadge());
        }
        AppUtils.getInstance().getImageProvider().loadAvatar(viewHolder.ivImage, message.getUserAvatar());
        viewHolder.tvMessageType.setText(message.cont);
        viewHolder.tvTime.setText(DateUtil.getAccuracyTimePostStr(message.time));
        viewHolder.tvAction.setVisibility(0);
        viewHolder.tvAction.setBackgroundResource(0);
        viewHolder.tvAction.setTextColor(Color.parseColor("#C0BFC0"));
        switch (message.getStatus()) {
            case 0:
                viewHolder.tvAction.setText(this.b.getString(R.string.family_agree));
                viewHolder.tvAction.setBackgroundResource(R.drawable.btn_yellow_selector);
                viewHolder.tvAction.setTextColor(Color.parseColor("#7B4600"));
                viewHolder.tvAction.setOnClickListener(new bk(this, message));
                break;
            case 1:
                viewHolder.tvAction.setText(this.b.getString(R.string.family_agreed));
                break;
            case 2:
                viewHolder.tvAction.setText(this.b.getString(R.string.family_denied));
                break;
            case 3:
                viewHolder.tvAction.setText(this.b.getString(R.string.family_ignored));
                break;
            case 6:
                viewHolder.tvAction.setText(this.b.getString(R.string.family_applied));
                break;
            default:
                viewHolder.tvAction.setVisibility(8);
                break;
        }
        viewHolder.ivImage.setOnClickListener(new bl(this, message));
        viewHolder.itemView.setOnClickListener(new bm(this, message));
    }

    public int getItemCount() {
        return this.a != null ? this.a.size() : 0;
    }

    private void a(Message message) {
        a(message, 1);
    }

    private void b(Message message) {
        a(message, 2);
    }

    private void c(Message message) {
        a(message, 3);
    }

    private void a(Message message, int i) {
        NetRequest.getInstance().post(UrlConstants.FAMILY_PROCESS, new bo(this, message, i));
    }
}
