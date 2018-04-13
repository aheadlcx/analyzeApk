package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.annotation.SuppressLint;
import android.arch.lifecycle.q;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.hollow.NickNameListJson.NickName;
import cn.xiaochuankeji.tieba.ui.base.l;
import com.jakewharton.a.b.a;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import java.util.concurrent.TimeUnit;
import rx.b.b;

public class FragmentCreateTextHollow extends l implements b {
    private a a;
    private a b;
    private AudioPublishModel c;
    @BindView
    View changeName;
    private NickName d;
    private int e;
    @BindView
    View editFunView;
    @BindView
    EditText editText;
    @BindView
    TextView editWarnInfo;
    private b f;
    private Unbinder g;
    @BindView
    TextView nicknameText;
    @BindView
    View refreshFun;
    @BindView
    View refreshView;
    @BindView
    DiscreteScrollView scrollView;

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_create_text_hollow, null);
        this.g = ButterKnife.a(this, inflate);
        k();
        l();
        m();
        n();
        return inflate;
    }

    private void k() {
        a.a(this.changeName).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                NickName b = this.a.c.b();
                if (b == null) {
                    this.a.c.a(this.a);
                    g.a("正在生成花名，请等待");
                    return;
                }
                this.a.b(b);
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    public void b(NickName nickName) {
        if (nickName == null) {
            g.a("没有获取到花名，请点击重新获取");
            return;
        }
        this.d = nickName;
        this.nicknameText.setText("花名：" + nickName.name);
    }

    @SuppressLint({"SetTextI18n"})
    private void l() {
        this.editWarnInfo.setText("0/200");
        this.editText.setFilters(new InputFilter[]{new LengthFilter(200)});
        this.editText.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() <= 200) {
                    this.a.editWarnInfo.setText(charSequence.length() + "/" + 200);
                } else {
                    g.a("树洞主题过长");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.editText.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return i == 1 || i == 0;
            }
        });
        this.editFunView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.htjyb.c.a.a(this.a.editText, this.a.getActivity());
            }
        });
    }

    private void m() {
        this.b = new a(getActivity(), this.scrollView);
        this.scrollView.setSlideOnFling(true);
        this.scrollView.setAdapter(this.b);
        this.scrollView.setOrientation(Orientation.HORIZONTAL);
        this.scrollView.setItemTransformer(new com.yarolegovich.discretescrollview.transform.b.a().a(0.6f).a());
        this.scrollView.setItemTransitionTimeMillis(100);
        this.scrollView.a(new DiscreteScrollView.a<a>(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public void a(@Nullable a aVar, int i) {
                if (this.a.a != null) {
                    this.a.a.a(false);
                }
                if (aVar != null) {
                    aVar.a(true);
                    this.a.a = aVar;
                    this.a.e = i;
                }
            }
        });
        this.scrollView.scrollToPosition(2);
    }

    private void n() {
        this.refreshView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o();
            }
        });
    }

    private void o() {
        this.c.a(new a(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.refreshView != null && this.a.refreshFun != null) {
                    this.a.refreshView.setVisibility(8);
                    this.a.refreshFun.setVisibility(0);
                }
            }

            public void b() {
                if (this.a.refreshView != null && this.a.refreshFun != null) {
                    this.a.refreshView.setVisibility(0);
                    this.a.refreshFun.setVisibility(8);
                }
            }
        });
    }

    protected void e() {
        this.c = (AudioPublishModel) q.a((Fragment) this).a(AudioPublishModel.class);
        this.c.a(this.b, getActivity());
        this.c.a((b) this);
        o();
    }

    public void a(NickName nickName) {
        b(nickName);
    }

    public void b(boolean z) {
        if (z && this.f != null) {
            this.f.a(OptionType.TEXT_PUBLISH);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.g.a();
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void i() {
        long a = this.b.a(this.e);
        if (this.editText.getText().length() < 5) {
            g.a("据说走心的树洞倾诉都多于5个字");
        } else if (a == -1) {
            g.a("表情信息获取失败");
        } else if (this.d == null || this.d.nameId <= 0) {
            g.a("未获取到花名，请重试");
        } else {
            this.c.a(this.editText.getText().toString(), this.b.a(this.e), this.d.nameId);
        }
    }

    public boolean j() {
        if (this.editText.getText().length() <= 0) {
            return false;
        }
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(getActivity(), "提示", "你要放弃发表吗？").b("继续编辑", null).a("放弃", new OnClickListener(this) {
            final /* synthetic */ FragmentCreateTextHollow a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().finish();
                }
            }
        }).a();
        return true;
    }
}
