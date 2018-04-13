package com.budejie.www.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class BannerResult implements Serializable {
    public Map<String, ArrayList<Topic>> jingxuan;
    public Map<String, ArrayList<Topic>> zuixin;
}
