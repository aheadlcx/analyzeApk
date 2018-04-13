package qsbk.app.live.ui.helper;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.regex.Pattern;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;

public class InspectHelper {
    public static void buildInspectMenu(Context context, User user, User user2) {
        Object obj = (user.getOriginId() == user2.getOriginId() && user.getOrigin() == user2.getOrigin()) ? 1 : null;
        if (obj != null) {
            buildLiveAnchorInspectMenu(context, user, user2);
        } else {
            buildLiveUserInspectMenu(context, user, user2);
        }
    }

    public static void buildLiveAnchorInspectMenu(Context context, User user, User user2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("修改权重");
        arrayList.add("封禁用户");
        arrayList.add("禁言用户");
        arrayList.add("关闭直播间");
        arrayList.add("弹窗（仅主播）");
        arrayList.add("系统消息（仅主播）");
        new Builder(context).setTitle("超管操作").setItems((String[]) arrayList.toArray(new String[0]), new a(context, user2, user)).show();
    }

    public static void buildLiveUserInspectMenu(Context context, User user, User user2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("封禁用户");
        arrayList.add("禁言用户");
        new Builder(context).setTitle("超管操作").setItems((String[]) arrayList.toArray(new String[0]), new b(context, user, user2)).show();
    }

    private static void b(Context context, User user, User user2) {
        new Builder(context).setTitle("封禁用户").setItems(new String[]{"一级封禁", "二级封禁", "三级封禁", "四级封禁", "取消"}, new c(user, user2)).show();
    }

    private static void b(User user, User user2) {
        NetRequest.getInstance().post(UrlConstants.ADMIN_MUTE, new d(user, user2));
    }

    private static void b(User user, User user2, String str) {
        NetRequest.getInstance().post(UrlConstants.ADMIN_BLOCK, new e(user, user2, str));
    }

    private static void b(User user) {
        NetRequest.getInstance().post(UrlConstants.ADMIN_CLOSE, new f(user));
    }

    private static void b(Context context, User user) {
        View editText = new EditText(context);
        editText.setGravity(17);
        editText.setHint("权重");
        editText.requestFocus();
        new Builder(context).setTitle("修改权重").setView(editText).setNegativeButton("取消", null).setPositiveButton("确定", new g(editText, user)).show();
    }

    public static boolean isInteger(String str) {
        return Pattern.compile("^[-\\+]?[\\d]*$").matcher(str).matches();
    }

    private static void b(Context context, User user, String str) {
        View editText = new EditText(context);
        editText.setGravity(17);
        editText.setHint("警告");
        editText.requestFocus();
        Object obj = str.equals("1") ? "弹窗（仅主播）" : "系统消息（仅主播）";
        new Builder(context).setTitle(obj).setView(editText).setNegativeButton("取消", null).setPositiveButton("提交", new i(editText, user, str, obj)).show();
    }
}
