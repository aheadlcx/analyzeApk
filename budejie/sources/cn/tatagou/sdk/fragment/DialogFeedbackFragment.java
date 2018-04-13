package cn.tatagou.sdk.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$style;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.pojo.FeedbackType;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.dialog.WheelView;
import cn.tatagou.sdk.view.dialog.d;
import cn.tatagou.sdk.view.dialog.f;
import java.util.ArrayList;
import java.util.List;

public class DialogFeedbackFragment extends DialogFragment implements OnClickListener {
    private WheelView a;
    private a b;
    private b c;
    private FeedbackType d;
    private List<FeedbackType> e;
    private List<String> f;

    private class a extends cn.tatagou.sdk.view.dialog.b {
        List<String> a;
        final /* synthetic */ DialogFeedbackFragment b;

        protected a(DialogFeedbackFragment dialogFeedbackFragment, Context context, List<String> list, int i) {
            this.b = dialogFeedbackFragment;
            super(context, R.layout.ttg_feedback_item, 0, i, 16, 14);
            this.a = list;
            setItemTextResource(R.id.tempValue);
        }

        public View getItem(int i, View view, ViewGroup viewGroup) {
            return super.getItem(i, view, viewGroup);
        }

        public int getItemsCount() {
            return this.a.size();
        }

        protected CharSequence a(int i) {
            return ((String) this.a.get(i)) + "";
        }
    }

    public interface b {
        void onClick(FeedbackType feedbackType);
    }

    public DialogFeedbackFragment(List<FeedbackType> list) {
        this.e = list;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.ttg_feedback_dialog, null);
        Dialog dialog = new Dialog(getActivity(), R$style.DialogType);
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(80);
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        a(inflate);
        a();
        return dialog;
    }

    private void a() {
        int i = 0;
        if (this.e != null && this.e.size() > 0) {
            this.f = new ArrayList();
            this.d = (FeedbackType) this.e.get(0);
            while (i < this.e.size()) {
                this.f.add(((FeedbackType) this.e.get(i)).getType());
                i++;
            }
            b();
        }
    }

    private void b() {
        this.b = new a(this, getActivity(), this.f, 0);
        this.a.setVisibleItems(5);
        this.a.setViewAdapter(this.b);
        this.a.setCyclic(false);
        this.a.setCurrentItem(0);
        this.a.addChangingListener(new d(this) {
            final /* synthetic */ DialogFeedbackFragment a;

            {
                this.a = r1;
            }

            public void onChanged(WheelView wheelView, int i, int i2) {
                this.a.d = (FeedbackType) this.a.e.get(wheelView.getCurrentItem());
                DialogFeedbackFragment.setTextviewSize(this.a.getActivity(), this.a.d.getType(), this.a.b);
            }
        });
        this.a.addScrollingListener(new f(this) {
            final /* synthetic */ DialogFeedbackFragment a;

            {
                this.a = r1;
            }

            public void onScrollingStarted(WheelView wheelView) {
            }

            public void onScrollingFinished(WheelView wheelView) {
                this.a.d = (FeedbackType) this.a.e.get(wheelView.getCurrentItem());
                DialogFeedbackFragment.setTextviewSize(this.a.getActivity(), this.a.d.getType(), this.a.b);
            }
        });
    }

    private void a(View view) {
        this.a = (WheelView) view.findViewById(R.id.wv_address_province);
        View findViewById = view.findViewById(R.id.btn_sure);
        TextView textView = (TextView) view.findViewById(R.id.btn_cancel);
        view.findViewById(R.id.ttg_ly_content).setOnClickListener(this);
        findViewById.setOnClickListener(this);
        textView.setOnClickListener(this);
        q.onResetTextViewThemeColor(textView);
        q.onResetShapeThemeColor(findViewById, 0, 0, TtgConfig.getInstance().getThemeColor());
        q.onResetShapeThemeColor(textView, 2, TtgConfig.getInstance().getThemeColor(), 0);
    }

    public void setCallBackListener(b bVar) {
        this.c = bVar;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_sure && this.c != null) {
            this.c.onClick(this.d);
        }
        dismiss();
    }

    public static void setTextviewSize(Context context, String str, cn.tatagou.sdk.view.dialog.b bVar) {
        ArrayList testViews = bVar.getTestViews();
        int size = testViews.size();
        for (int i = 0; i < size; i++) {
            TextView textView = (TextView) testViews.get(i);
            if (str.equals(textView.getText().toString())) {
                textView.setTextSize(16.0f);
            } else {
                textView.setTextSize(14.0f);
            }
        }
    }

    public static int getIndexItem(List<String> list, String str) {
        if (list == null) {
            return 0;
        }
        int size = list.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            if (str.equals(list.get(i))) {
                return i2;
            }
            i++;
            i2++;
        }
        return 0;
    }
}
