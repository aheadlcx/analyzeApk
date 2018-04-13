package qsbk.app.fragments;

import qsbk.app.model.FoundFragementItem.FoundChicken;
import qsbk.app.model.FoundFragementItem.FoundGame;
import qsbk.app.model.FoundFragementItem.FoundStore;

@Deprecated
public class FoundFragment extends StatisticFragment {
    public static final String FOUND_CHICKEN_HAS_TIPS = "found_chicken_has_tips";
    public static final String FOUND_GAME_HAS_TIPS = "found_game_has_tips";
    public static boolean foundChickenHasTips = false;
    public static boolean foundGameHasTips = false;
    public static FoundChicken foundStaticChicken = null;
    public static FoundGame foundStaticGame = null;
    public static FoundStore foundStaticStore = null;
}
