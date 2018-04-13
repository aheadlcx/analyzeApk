package cn.xiaochuankeji.tieba.c.a;

public class h extends j {
    public h() {
        super("#extension GL_OES_EGL_image_external : require\n\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\n\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
    }

    protected int i() {
        return 36197;
    }
}
