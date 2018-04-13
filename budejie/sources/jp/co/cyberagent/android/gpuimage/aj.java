package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class aj extends ab {
    private int g;
    private float h;
    private int i;
    private float j;

    public aj() {
        this(0.0f, 1.0f);
    }

    public aj(float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n  \n uniform lowp float shadows;\n uniform lowp float highlights;\n \n const mediump vec3 luminanceWeighting = vec3(0.3, 0.3, 0.3);\n \n void main()\n {\n \tlowp vec4 source = texture2D(inputImageTexture, textureCoordinate);\n \tmediump float luminance = dot(source.rgb, luminanceWeighting);\n \n \tmediump float shadow = clamp((pow(luminance, 1.0/(shadows+1.0)) + (-0.76)*pow(luminance, 2.0/(shadows+1.0))) - luminance, 0.0, 1.0);\n \tmediump float highlight = clamp((1.0 - (pow(1.0-luminance, 1.0/(2.0-highlights)) + (-0.8)*pow(1.0-luminance, 2.0/(2.0-highlights)))) - luminance, -1.0, 0.0);\n \tlowp vec3 result = vec3(0.0, 0.0, 0.0) + ((luminance + shadow + highlight) - 0.0) * ((source.rgb - vec3(0.0, 0.0, 0.0))/(luminance - 0.0));\n \n \tgl_FragColor = vec4(result.rgb, source.a);\n }");
        this.j = f2;
        this.h = f;
    }

    public void a() {
        super.a();
        this.i = GLES20.glGetUniformLocation(l(), "highlights");
        this.g = GLES20.glGetUniformLocation(l(), "shadows");
    }

    public void c_() {
        super.c_();
        a(this.j);
        b(this.h);
    }

    public void a(float f) {
        this.j = f;
        a(this.i, this.j);
    }

    public void b(float f) {
        this.h = f;
        a(this.g, this.h);
    }
}
