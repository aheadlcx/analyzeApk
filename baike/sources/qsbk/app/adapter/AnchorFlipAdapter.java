package qsbk.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.im.ChatMsg;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.LiveRoom;
import qsbk.app.model.LiveRoom.Author;

public class AnchorFlipAdapter {
    public static View getView(Context context, LiveRoom liveRoom) {
        Author author = liveRoom.author;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_follow_flip_item, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.live_avatar);
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.sub_title);
        textView2.setText(liveRoom.content);
        textView2.setVisibility(TextUtils.isEmpty(liveRoom.content) ? 8 : 0);
        if (author != null) {
            textView.setText(author.name + "开播了");
            FrescoImageloader.displayAvatar(imageView, author.headurl);
        }
        return inflate;
    }

    public static View getView(Context context, ChatMsg chatMsg) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_follow_flip_item, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.live_avatar);
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.sub_title);
        try {
            JSONObject jSONObject = new JSONObject(chatMsg.data);
            jSONObject.optString("content");
            jSONObject.optString("live_id");
            jSONObject.optLong("origin");
            String optString = jSONObject.optString("pic_url");
            CharSequence optString2 = jSONObject.optString("title");
            textView.setText("name");
            textView2.setText(optString2);
            textView2.setVisibility(TextUtils.isEmpty(optString2) ? 8 : 0);
            FrescoImageloader.displayAvatar(imageView, optString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
