package cn.xiaochuankeji.tieba.c.a;

public class g extends l {
    public g() {
        super("precision mediump float;\nuniform sampler2D sTexture;\nuniform sampler2D sTexture2;\nvarying vec2 vTextureCoord;\nvarying vec2 vTextureCoord2;\n uniform lowp float uMin;\n uniform lowp float uMax;\n\nvoid main(){\n   vec4 c2 = texture2D(sTexture,vTextureCoord);\n   vec4 c1 = texture2D(sTexture2,vTextureCoord2);\n   vec4 outputColor;\n   outputColor.r = c1.r + c2.r * c2.a * (1.0 - c1.a);\n   outputColor.g = c1.g + c2.g * c2.a * (1.0 - c1.a);\n   outputColor.b = c1.b + c2.b * c2.a * (1.0 - c1.a);\n   outputColor.a = c1.a + c2.a * (1.0 - c1.a);     if((uMin == 0.0 || vTextureCoord.x > uMin) && vTextureCoord.x <= uMax) {\n       gl_FragColor = outputColor;\n     } else {\n       gl_FragColor = c2;\n     }\n}\n");
    }
}
