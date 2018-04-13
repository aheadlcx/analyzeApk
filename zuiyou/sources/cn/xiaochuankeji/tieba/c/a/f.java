package cn.xiaochuankeji.tieba.c.a;

import android.opengl.GLES20;

public class f extends l {
    private float n;
    private int o;

    public f(float f) {
        super(" precision mediump float;\n varying mediump vec2 vTextureCoord;\n varying mediump vec2 vTextureCoord2; \n uniform sampler2D sTexture;\n uniform sampler2D sTexture2; uniform lowp float uMin;\n uniform lowp float uMax;\n \n uniform lowp float intensity;\n \n void main()\n {\n     mediump vec4 textureColor = texture2D(sTexture, vTextureCoord);\n     \n     mediump float blueColor = textureColor.b * 63.0;\n     \n     mediump vec2 quad1;\n     quad1.y = floor(floor(blueColor) / 8.0);\n     quad1.x = floor(blueColor) - (quad1.y * 8.0);\n     \n     mediump vec2 quad2;\n     quad2.y = floor(ceil(blueColor) / 8.0);\n     quad2.x = ceil(blueColor) - (quad2.y * 8.0);\n     \n     mediump vec2 texPos1;\n     texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);\n     texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);\n     \n     mediump vec2 texPos2;\n     texPos2.x = (quad2.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);\n     texPos2.y = (quad2.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);\n     \n     lowp vec4 newColor1 = texture2D(sTexture2, texPos1);\n     lowp vec4 newColor2 = texture2D(sTexture2, texPos2);\n     \n     lowp vec4 newColor = mix(newColor1, newColor2, fract(blueColor));\n     if((uMin == 0.0 || vTextureCoord.x > uMin) && vTextureCoord.x <= uMax) {\n       gl_FragColor = mix(textureColor, vec4(newColor.rgb, textureColor.w), intensity);\n     } else {\n       gl_FragColor = textureColor;\n     }\n }");
        this.n = f;
    }

    public void b() {
        super.b();
        this.o = GLES20.glGetUniformLocation(f(), "intensity");
        a(this.n);
    }

    public void a(float f) {
        this.n = f;
        a(this.o, this.n);
    }
}
