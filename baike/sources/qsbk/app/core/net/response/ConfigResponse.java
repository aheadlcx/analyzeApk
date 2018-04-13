package qsbk.app.core.net.response;

import java.util.List;
import java.util.Map;
import qsbk.app.core.model.BarrageData;
import qsbk.app.core.model.BarrageDecorData;
import qsbk.app.core.model.Bitrate;
import qsbk.app.core.model.Diamond;
import qsbk.app.core.model.GameConfig;
import qsbk.app.core.model.GiftCount;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.model.HitColorData;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.model.MarketData;
import qsbk.app.core.model.RedEnvelopesConfig;
import qsbk.app.core.model.RichUser;
import qsbk.app.core.model.TitleData;

public class ConfigResponse {
    public long ali_max;
    public Map<Long, GiftData> all_gift_data;
    public List<Diamond> android_price_data;
    public List<GiftData> anime_effect;
    public Map<String, BarrageDecorData> barrage_bg_map;
    public Map<String, BarrageData> barrage_map;
    public Map<String, Bitrate> bitrate;
    public List<Integer> blossom;
    public int config_version;
    public List<HitColorData> double_send_color;
    public int effect;
    public Map<String, GameConfig> games;
    public Map<Long, GiftCount> gift_counts;
    public List<Long> gift_data;
    public String help_msg;
    public String help_url;
    public int hl;
    public int jiamin_spec;
    public int qba;
    public RedEnvelopesConfig redpkg_conf;
    public List<RichUser> rich_spec_arr;
    public Map<Long, MarketData> scene_ani;
    public Map<String, LevelData> scene_data;
    public Map<String, String> taburl;
    public Map<String, String> template;
    public List<TitleData> user_title;
    public List<Long> video_gift_data;
    public long wx_max;
}
