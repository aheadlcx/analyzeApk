package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.tencent.wcdb.database.SQLiteDatabase;

public class TopicCoverActivity extends h {
    public static void a(Context context, TopicDetail topicDetail) {
        Intent intent = new Intent(context, TopicCoverActivity.class);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("topicDetail", topicDetail);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_memeber_profile;
    }

    protected void i_() {
        ((WebImageView) findViewById(R.id.niv_profile)).setWebImage(b.c(((TopicDetail) getIntent().getExtras().getSerializable("topicDetail"))._topicCoverID, true));
        findViewById(R.id.rootView).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopicCoverActivity a;

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
