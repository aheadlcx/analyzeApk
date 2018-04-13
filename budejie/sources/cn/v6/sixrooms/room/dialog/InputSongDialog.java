package cn.v6.sixrooms.room.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.ToastUtils;

public class InputSongDialog extends Dialog implements TextWatcher, OnClickListener {
    private BaseRoomActivity a;
    private EditText b;
    private TextView c;
    private TextView d;

    public InputSongDialog(Context context) {
        super(context, R.style.song_dialog_style);
        this.a = (BaseRoomActivity) context;
        setContentView(R.layout.input_music_layout);
        setCancelable(true);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        window.setWindowAnimations(16973910);
        attributes.gravity = 80;
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        if (getContext().getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
        }
        this.b = (EditText) findViewById(R.id.music_input);
        this.c = (TextView) findViewById(R.id.tv_song_menu_ok);
        this.d = (TextView) findViewById(R.id.music_cancel);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.addTextChangedListener(this);
        this.b.setOnEditorActionListener(new b(this));
    }

    public void openKeybord() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.a.getSystemService("input_method");
        inputMethodManager.showSoftInput(this.b, 2);
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public void show() {
        super.show();
        new Handler().postDelayed(new c(this), 300);
    }

    public void dismiss() {
        closeKeybord();
        super.dismiss();
    }

    public void closeKeybord() {
        ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_song_menu_ok) {
            a();
        } else if (id == R.id.music_cancel) {
            closeKeybord();
            dismiss();
        }
    }

    private void a() {
        closeKeybord();
        Object obj = this.b.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastUtils.showToast("歌曲名不能为空");
            return;
        }
        this.a.sendSetSong(obj, "", "", "");
        dismiss();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().length() > 0) {
            this.c.setVisibility(0);
            this.d.setVisibility(8);
            return;
        }
        this.c.setVisibility(8);
        this.d.setVisibility(0);
    }

    public void afterTextChanged(Editable editable) {
    }
}
