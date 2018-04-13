package cn.xiaochuankeji.tieba.network.custom.a;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.network.custom.exception.VerifyErrorException;
import com.sina.weibo.sdk.constant.WBConstants;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.u;
import okhttp3.y;
import okio.BufferedSource;
import okio.ByteString;
import org.json.JSONException;
import org.json.JSONObject;

public class d implements t {
    private static final Charset a = Charset.forName("UTF-8");
    private static final byte[] b = new byte[]{(byte) -1, (byte) -40, (byte) -1};
    private static final byte[] c = new byte[]{(byte) -119, (byte) 80, (byte) 78, (byte) 71};
    private static final byte[] d = new byte[]{(byte) 71, (byte) 73, (byte) 70, (byte) 56};
    private static final byte[] e = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 32, (byte) 102, (byte) 116, (byte) 121, (byte) 112};
    private static final byte[] f = new byte[]{(byte) 82, (byte) 73, (byte) 70, (byte) 70};

    public aa intercept(a aVar) throws IOException {
        y a = aVar.a();
        aa a2 = aVar.a(a);
        if (a2.c()) {
            a(a, a2);
            if (a.b().equalsIgnoreCase("post")) {
                ab g = a2.g();
                u contentType = g.contentType();
                if (!(g == null || contentType == null || !contentType.toString().contains("text/plain"))) {
                    BufferedSource source = g.source();
                    source.request(Long.MAX_VALUE);
                    try {
                        JSONObject jSONObject = new JSONObject(source.buffer().clone().readString(a));
                        int optInt = jSONObject.optInt("ret");
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optInt != 1) {
                            throw new ClientErrorException(optInt, jSONObject.optString("msg"), optJSONObject);
                        }
                        String str;
                        u contentType2 = g.contentType();
                        if (optJSONObject == null) {
                            str = "";
                        } else {
                            str = optJSONObject.toString();
                        }
                        a2 = a2.h().a(ab.create(contentType2, str)).a();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                        source.close();
                    }
                }
            }
        }
        return a2;
    }

    private void a(aa aaVar) throws IOException {
        BufferedSource source = aaVar.g().source();
        try {
            if (source.rangeEquals(0, ByteString.of(b)) || source.rangeEquals(0, ByteString.of(c)) || source.rangeEquals(0, ByteString.of(d)) || source.rangeEquals(0, ByteString.of(e)) || source.rangeEquals(0, ByteString.of(f))) {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new VerifyErrorException("not image");
    }

    private void b(aa aaVar) throws IOException {
        BufferedSource source = aaVar.g().source();
        source.request(Long.MAX_VALUE);
        try {
            if (new JSONObject(source.buffer().clone().readString(a)).has("ret")) {
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new VerifyErrorException("not json");
    }

    private void a(y yVar, aa aaVar) throws IOException {
        String a = yVar.a("Request-Type");
        if (!TextUtils.isEmpty(a)) {
            if (a.contains(WBConstants.GAME_PARAMS_GAME_IMAGE_URL)) {
                a(aaVar);
            } else if (a.contains("json")) {
                b(aaVar);
            }
        }
    }
}
