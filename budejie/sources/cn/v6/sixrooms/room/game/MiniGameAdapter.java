package cn.v6.sixrooms.room.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class MiniGameAdapter extends BaseAdapter {
    private int clickTemp = 0;
    private List<MiniGameBean> mGameList;
    private String mGameid = "";
    private LayoutInflater mInflater;

    static class ViewHolder {
        SimpleDraweeView game_icon;
        ImageView game_icon_cover;
        TextView game_name;
        RelativeLayout item_bg;
        ImageView iv_tag;

        ViewHolder() {
        }
    }

    public void setmGameid(String str) {
        this.mGameid = str;
    }

    public MiniGameAdapter(Context context, List<MiniGameBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mGameList = list;
    }

    public void setSeclection(int i) {
        this.clickTemp = i;
    }

    public int getSeclection() {
        return this.clickTemp;
    }

    public int getCount() {
        return this.mGameList.size();
    }

    public Object getItem(int i) {
        return this.mGameList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.item_mini_game, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.item_bg = (RelativeLayout) view.findViewById(R.id.item_bg);
            viewHolder.iv_tag = (ImageView) view.findViewById(R.id.iv_tag);
            viewHolder.game_icon = (SimpleDraweeView) view.findViewById(R.id.game_icon);
            viewHolder.game_icon_cover = (ImageView) view.findViewById(R.id.game_icon_cover);
            viewHolder.game_name = (TextView) view.findViewById(R.id.game_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if ("".equals(this.mGameid) || this.clickTemp == i) {
            viewHolder.game_icon_cover.setVisibility(8);
        } else {
            viewHolder.game_icon_cover.setVisibility(0);
        }
        if (this.clickTemp == i) {
            viewHolder.item_bg.setBackgroundResource(R.drawable.giftbox_gift_select_bg);
            viewHolder.iv_tag.setVisibility(0);
        } else {
            viewHolder.item_bg.setBackgroundColor(0);
            viewHolder.iv_tag.setVisibility(8);
        }
        viewHolder.game_icon.setImageURI(((MiniGameBean) this.mGameList.get(i)).getIcon());
        viewHolder.game_name.setText(((MiniGameBean) this.mGameList.get(i)).getTitle());
        return view;
    }
}
