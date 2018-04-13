package com.budejie.www.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Navigation implements Serializable {
    public String name;
    public ArrayList<TopNavigation> submenus;
}
