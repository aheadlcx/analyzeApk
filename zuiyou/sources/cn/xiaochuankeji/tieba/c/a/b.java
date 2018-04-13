package cn.xiaochuankeji.tieba.c.a;

public class b extends l {
    public b() {
        super("varying highp vec2 vTextureCoord;\n varying highp vec2 vTextureCoord2;\n\n uniform sampler2D sTexture;\n uniform sampler2D sTexture2;\n uniform lowp float uMin;\n uniform lowp float uMax;\n \n void main()\n {\n     mediump vec4 textureColor = texture2D(sTexture, vTextureCoord);\n     mediump vec4 textureColor2 = texture2D(sTexture2, vTextureCoord2);\n     mediump vec4 whiteColor = vec4(1.0);\n     if((uMin == 0.0 || vTextureCoord.x > uMin) && vTextureCoord.x <= uMax) {\n       gl_FragColor = whiteColor - ((whiteColor - textureColor2) * (whiteColor - textureColor));\n     } else {\n       gl_FragColor = textureColor;\n     }\n }");
    }
}
