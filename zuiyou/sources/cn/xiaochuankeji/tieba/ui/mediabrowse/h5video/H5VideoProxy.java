package cn.xiaochuankeji.tieba.ui.mediabrowse.h5video;

import com.alibaba.fastjson.annotation.JSONField;

public class H5VideoProxy {
    private final b a;
    private c<SrcResult> b;

    public static class SrcResult {
        @JSONField(name = "src")
        public String src;
    }

    public H5VideoProxy(b bVar) {
        this.a = bVar;
    }

    private void c() {
        this.a.a("returnSrc", new c<SrcResult>(this) {
            final /* synthetic */ H5VideoProxy a;

            {
                this.a = r1;
            }

            public void a(SrcResult srcResult) {
                if (this.a.b != null) {
                    this.a.b.a(srcResult);
                }
            }
        }, SrcResult.class);
    }

    public void a() {
        c();
        this.a.a("HTMLMediaElement.prototype.play = function() {var videoTag = document.getElementsByTagName('video')[0];if(videoTag === undefined) {   window.jsBridge.returnSrc({src:''});} else {   var sourceTag = videoTag.getElementsByTagName('source')[0];   if(sourceTag === undefined) {       window.jsBridge.returnSrc({src:videoTag.src});   } else {       window.jsBridge.returnSrc({src:sourceTag.src});   }}};");
    }

    public void a(c<SrcResult> cVar) {
        this.b = cVar;
    }

    public void b() {
        this.a.a("var videoTag = document.getElementsByTagName('video')[0];if(videoTag === undefined) {   window.jsBridge.returnSrc({src:''});} else {   var sourceTag = videoTag.getElementsByTagName('source')[0];   if(sourceTag === undefined) {       window.jsBridge.returnSrc({src:videoTag.src});   } else {       window.jsBridge.returnSrc({src:sourceTag.src});   }}");
    }
}
