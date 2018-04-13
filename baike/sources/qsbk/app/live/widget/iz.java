package qsbk.app.live.widget;

import android.graphics.Color;
import android.os.AsyncTask;
import android.text.TextPaint;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAParser.ParseCompletion;
import com.opensource.svgaplayer.SVGAVideoEntity;
import org.jetbrains.annotations.NotNull;
import qsbk.app.core.model.MarketData;
import qsbk.app.live.model.LiveEnterMessage;

class iz implements ParseCompletion {
    final /* synthetic */ LiveEnterMessage a;
    final /* synthetic */ MarketData b;
    final /* synthetic */ SuperUserEnterAnimLayout c;

    iz(SuperUserEnterAnimLayout superUserEnterAnimLayout, LiveEnterMessage liveEnterMessage, MarketData marketData) {
        this.c = superUserEnterAnimLayout;
        this.a = liveEnterMessage;
        this.b = marketData;
    }

    public void onComplete(@NotNull SVGAVideoEntity sVGAVideoEntity) {
        SVGADynamicEntity sVGADynamicEntity = new SVGADynamicEntity();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new ja(this, sVGADynamicEntity));
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((float) this.b.s);
        textPaint.setFakeBoldText(true);
        textPaint.setColor(Color.parseColor(this.b.tc));
        String userName = this.a.getUserName();
        if (userName.length() > 4) {
            userName = userName.substring(0, 4) + "...";
        }
        sVGADynamicEntity.setDynamicText(userName + this.b.w, textPaint, this.b.nk);
        this.c.p.setImageDrawable(new SVGADrawable(sVGAVideoEntity, sVGADynamicEntity));
        this.c.p.startAnimation();
        this.c.c();
        this.c.p.setCallback(new jc(this));
    }

    public void onError() {
    }
}
