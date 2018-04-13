package qsbk.app.model;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import org.json.JSONObject;

class m extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ l b;

    m(l lVar, JSONObject jSONObject) {
        this.b = lVar;
        this.a = jSONObject;
    }

    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        EventWindow.saveEventWindow(this.a.toString());
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
        EventWindow.saveEventWindow(this.a.toString());
    }
}
