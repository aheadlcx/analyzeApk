package qsbk.app.nearby.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import antistatic.spinnerwheel.AbstractWheelView;
import antistatic.spinnerwheel.WheelVerticalView;
import antistatic.spinnerwheel.adapters.AbstractWheelTextAdapter;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;
import antistatic.spinnerwheel.adapters.WheelViewAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.nearby.api.NearbyEngine;

public class HometownDialogFragment extends DialogFragment {
    public static final String KEY_HOMETOWN = "hometown";
    private static Map<String, String[]> j;
    private WheelVerticalView k;
    private WheelVerticalView l;
    private List<String> m;
    private List<String[]> n;
    private int o = 0;
    private int p = 0;
    private Hometown q;
    private boolean r = false;
    private int s = 17;
    private OnHometownSelect t;

    public static final class Hometown implements Parcelable {
        public static final Creator<Hometown> CREATOR = new g();
        private String a;
        private String b;

        private Hometown(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }

        public Hometown(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String getProvince() {
            return this.a;
        }

        public void setProvince(String str) {
            this.a = str;
        }

        public String getCity() {
            return this.b;
        }

        public void setCity(String str) {
            this.b = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
        }

        public String toString() {
            return "Hometown [province=" + this.a + ", city=" + this.b + "]";
        }
    }

    public static final class ListWheelTextAdapter<T> extends AbstractWheelTextAdapter {
        private List<T> f;

        public ListWheelTextAdapter(Context context, List<T> list) {
            super(context);
            this.f = list;
        }

        public CharSequence getItemText(int i) {
            if (i < 0 || i >= this.f.size()) {
                return null;
            }
            Object obj = this.f.get(i);
            if (obj instanceof CharSequence) {
                return (CharSequence) obj;
            }
            return obj.toString();
        }

        public int getItemsCount() {
            return this.f.size();
        }
    }

    public interface OnHometownSelect {
        void onCancel(Hometown hometown);

        void onSelected(Hometown hometown, Hometown hometown2);
    }

    public static HometownDialogFragment newInstance(Hometown hometown) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_HOMETOWN, hometown);
        HometownDialogFragment hometownDialogFragment = new HometownDialogFragment();
        hometownDialogFragment.setArguments(bundle);
        return hometownDialogFragment;
    }

    private static final boolean a() {
        return VERSION.SDK_INT > 10;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnHometownSelect) {
            this.t = (OnHometownSelect) activity;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.q = (Hometown) arguments.getParcelable(KEY_HOMETOWN);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Context activity = getActivity();
        if (j == null) {
            j = NearbyEngine.instance().getLocalHometown(activity);
        }
        if (j == null) {
            new a(this, activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return ProgressDialog.show(getActivity(), null, getResources().getString(R.string.loading));
        }
        b();
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_hometown_selector, null);
        inflate.setMinimumHeight(400);
        this.k = (WheelVerticalView) inflate.findViewById(R.id.province);
        this.k.setVisibleItems(5);
        this.l = (WheelVerticalView) inflate.findViewById(R.id.city);
        this.l.setVisibleItems(5);
        this.l.addChangingListener(new b(this));
        a(this.l, this.o);
        if (this.p != 0) {
            this.l.setCurrentItem(this.p);
        }
        WheelViewAdapter listWheelTextAdapter = new ListWheelTextAdapter(activity, this.m);
        if (!a()) {
            listWheelTextAdapter.setTextColor(-1);
        }
        listWheelTextAdapter.setTextSize(this.s);
        this.k.setViewAdapter(listWheelTextAdapter);
        this.k.setCurrentItem(this.o);
        this.k.addChangingListener(new c(this));
        this.k.addScrollingListener(new d(this));
        return new Builder(activity).setTitle(R.string.select_hometown).setPositiveButton(R.string.app_ok, new f(this)).setNegativeButton(R.string.app_cancel, new e(this)).setView(inflate).create();
    }

    private void a(AbstractWheelView abstractWheelView, int i) {
        Context activity = getActivity();
        if (activity != null) {
            this.o = i;
            WheelViewAdapter arrayWheelAdapter = new ArrayWheelAdapter(activity, (Object[]) this.n.get(i));
            if (!a()) {
                arrayWheelAdapter.setTextColor(-1);
            }
            arrayWheelAdapter.setTextSize(this.s);
            abstractWheelView.setViewAdapter(arrayWheelAdapter);
            abstractWheelView.setCurrentItem(0, false);
        }
    }

    private void b() {
        int size = j.size();
        this.m = new LinkedList();
        this.n = new LinkedList();
        for (Entry entry : j.entrySet()) {
            this.m.add((String) entry.getKey());
            this.n.add((String[]) entry.getValue());
        }
        if (this.q != null) {
            String[] strArr = (String[]) j.get(this.q.a);
            if (strArr != null) {
                int i;
                int length = strArr.length;
                for (i = 0; i < length; i++) {
                    if (strArr[i].equals(this.q.b)) {
                        this.p = i;
                        break;
                    }
                }
                for (i = 0; i < size; i++) {
                    if (((String) this.m.get(i)).equals(this.q.a)) {
                        this.o = i;
                        return;
                    }
                }
            }
        }
    }
}
