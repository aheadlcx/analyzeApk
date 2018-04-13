package qsbk.app.activity.publish;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseGroupDialog;

class ad extends BaseGroupDialog {
    final /* synthetic */ CirclePublishActivity a;

    ad(CirclePublishActivity circlePublishActivity, Context context) {
        this.a = circlePublishActivity;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_circle_publish_tips);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.dialog_circle_publish);
        if (relativeLayout != null) {
            if (UIHelper.isNightTheme()) {
                relativeLayout.setBackgroundColor(this.a.getResources().getColor(R.color.main_bg_night));
            } else {
                relativeLayout.setBackgroundColor(this.a.getResources().getColor(R.color.main_bg));
            }
        }
        ((TextView) findViewById(R.id.tips)).setText(Html.fromHtml("1、涉及黄色，政治，广告及骚扰信息<br/>2、涉及人身攻击<br/>3、留联系方式，透露他人隐私<br/>一经核实将被<font color=\"#f3487f\">封禁</font>，情节严重者<font color=\"#f3487f\">永久封禁</font><br/><br/><font color=\"#f3487f\">温馨提示：糗友圈发动态(视频动态除外)不用审核哦~</font>"));
        findViewById(R.id.btn).setOnClickListener(new ae(this));
    }
}
