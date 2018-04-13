package cn.xiaochuankeji.tieba.c.a;

import android.opengl.GLES20;

public class c extends j {
    private int i;

    public c() {
        super("precision mediump float;\n\nvarying mediump vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform vec4 mixColor;\n\n void main()\n {\n   vec4 color = texture2D(sTexture, vTextureCoord);\n\n     gl_FragColor = vec4(mix(color.rgb, mixColor.rgb, mixColor.a), color.a);\n }");
    }

    protected void b() {
        super.b();
        this.i = GLES20.glGetUniformLocation(f(), "mixColor");
    }

    public void a(float f, float f2, float f3, float f4) {
        final float[] fArr = new float[]{f, f2, f3, f4};
        a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                this.b.a(this.b.i, fArr);
            }
        });
    }
}
