package qsbk.app.activity.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.R;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;
import qsbk.app.im.emotion.EmotionViewPager;
import qsbk.app.widget.DotView;
import qsbk.app.widget.QiushiEmotionHandler;
import qsbk.app.widget.QiushiEmotionHandler$EmotionData;

public abstract class BaseArticleEmotionActivity extends BaseEmotionActivity implements OnEmotionItemClickListener {
    protected EmotionViewPager E;
    protected DotView F;

    private static Map<String, List<ChatMsgEmotionData>> f() {
        Collection<QiushiEmotionHandler$EmotionData> values = QiushiEmotionHandler.getInstance().getAll().values();
        Map<String, List<ChatMsgEmotionData>> linkedHashMap = new LinkedHashMap(1);
        List arrayList = new ArrayList();
        int i = 0;
        for (QiushiEmotionHandler$EmotionData qiushiEmotionHandler$EmotionData : values) {
            int i2 = i + 1;
            ChatMsgEmotionData chatMsgEmotionData = new ChatMsgEmotionData();
            if (i2 % 28 == 0) {
                chatMsgEmotionData.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
            } else {
                chatMsgEmotionData.name = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.key = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.localResource = qiushiEmotionHandler$EmotionData.getResId();
            }
            arrayList.add(chatMsgEmotionData);
            i = i2;
        }
        ChatMsgEmotionData chatMsgEmotionData2 = new ChatMsgEmotionData();
        chatMsgEmotionData2.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
        arrayList.add(chatMsgEmotionData2);
        linkedHashMap.put("emotion_small", arrayList);
        return linkedHashMap;
    }

    protected void l() {
        super.l();
        this.E = (EmotionViewPager) findViewById(R.id.emotion_viewpager);
        this.F = (DotView) findViewById(R.id.emotion_dotview);
        this.E.setOnEmotionClickListener(this);
        this.E.setEmotionType(1);
        this.E.setPerPageCount(28);
        this.E.setRowCount(4);
        this.E.setDatas(f());
        this.E.setDotView(this.F);
    }
}
