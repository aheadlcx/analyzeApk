package qsbk.app.widget;

import android.widget.TextView;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpCallBack;
import qsbk.app.http.HttpTask;
import qsbk.app.model.PunchInfo;
import qsbk.app.utils.Util;

public class PunchCardCell extends BaseCell {
    public TextView mAmountView;
    public TextView mPunchButton;

    static class a extends HttpTask {
        public a(HttpCallBack httpCallBack) {
            super(Constants.CIRCLE_PUNCH_INFO, httpCallBack);
        }
    }

    public static void getPunchCard(HttpCallBack httpCallBack) {
        new a(httpCallBack).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onCreate() {
        setCellView((int) R.layout.layout_punch_cell);
        this.mAmountView = (TextView) findViewById(R.id.amount);
        this.mPunchButton = (TextView) findViewById(R.id.punch_btn);
    }

    public void onUpdate() {
        PunchInfo punchInfo = (PunchInfo) getItem();
        if (punchInfo != null) {
            this.mPunchButton.setText(punchInfo.content);
            this.mAmountView.setText(Util.formatMoneyWithCommas(punchInfo.amount));
            getCellView().setOnClickListener(new di(this));
        }
    }

    public void goCardWeb() {
        SimpleWebActivity.launch(getContext(), Constants.CIRCLE_PUNCH_INFO);
    }
}
