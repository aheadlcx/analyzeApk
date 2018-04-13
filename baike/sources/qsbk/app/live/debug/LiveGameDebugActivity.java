package qsbk.app.live.debug;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.GameResult;
import qsbk.app.live.widget.PokerGroup;

public class LiveGameDebugActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest2"));
        UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest2"));
        UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test2"));
        UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
        ToastUtil.Short("切到测试环境2");
    }

    protected int getLayoutId() {
        return R.layout.live_game_debug_activity;
    }

    protected void initView() {
        findViewById(R.id.btn_enter_live).setOnClickListener(new i(this));
    }

    protected void initData() {
        int i;
        int i2 = 0;
        List arrayList = new ArrayList();
        for (i = 0; i < 52; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        List arrayList2 = new ArrayList();
        while (i2 < 5) {
            Integer num = (Integer) arrayList.get(Math.abs(new Random().nextInt() % arrayList.size()));
            if (arrayList2.contains(num)) {
                i = i2;
            } else {
                arrayList2.add(num);
                i = i2 + 1;
            }
            i2 = i;
        }
        GameResult gameResult = new GameResult();
        gameResult.p = arrayList2;
        ((PokerGroup) findViewById(R.id.poker_group)).loadPokers(gameResult);
    }
}
