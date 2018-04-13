package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;
import cn.v6.sixrooms.room.BecomeGodBean;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BecomeGodSceneBuilder;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.BecomeGodSceneParameter;
import cn.v6.sixrooms.surfaceanim.flybanner.becomegod.GodType;

public class BecomeGodSceneFactory implements IAnimSceneFactory {
    public AnimScene[] generateAnimScene(Object obj) {
        if (!(obj instanceof BecomeGodBean)) {
            return null;
        }
        BecomeGodBean becomeGodBean = (BecomeGodBean) obj;
        Object alias = becomeGodBean.getAlias();
        Object ralias = becomeGodBean.getRalias();
        String bg = becomeGodBean.getBg();
        String rank = becomeGodBean.getRank();
        Object rid = becomeGodBean.getRid();
        if (TextUtils.isEmpty(alias) || TextUtils.isEmpty(ralias) || TextUtils.isEmpty(rank) || TextUtils.isEmpty(rid)) {
            return null;
        }
        BecomeGodSceneParameter becomeGodSceneParameter = new BecomeGodSceneParameter();
        becomeGodSceneParameter.setFromUser(alias);
        becomeGodSceneParameter.setToUser(ralias);
        becomeGodSceneParameter.setBgUrl(bg);
        becomeGodSceneParameter.setToRoomUid(rid);
        if (rank.equals("25")) {
            becomeGodSceneParameter.setGodType(GodType.GOD);
        } else if (rank.equals("26")) {
            becomeGodSceneParameter.setGodType(GodType.GOD_OF_GODS);
        } else if (rank.equals("27")) {
            becomeGodSceneParameter.setGodType(GodType.CREATOR_GOD);
        }
        return new AnimScene[]{BecomeGodSceneBuilder.createScene(becomeGodSceneParameter)};
    }
}
