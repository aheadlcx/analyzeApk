package cn.xiaochuankeji.tieba.ui.member;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.tencent.wcdb.database.SQLiteDatabase;

public class MemberAvatarActivity extends a {
    public static void a(Context context, Member member) {
        Intent intent = new Intent(context, MemberAvatarActivity.class);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("member", member);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_memeber_profile;
    }

    protected void i_() {
        Member member = (Member) getIntent().getExtras().getSerializable("member");
        ((WebImageView) findViewById(R.id.niv_profile)).setWebImage(b.a(member.getId(), member.getAvatarID()));
        findViewById(R.id.rootView).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MemberAvatarActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        com.android.a.a.b.a(getWindow(), false);
    }
}
