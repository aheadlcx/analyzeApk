package com.budejie.www.bean;

import java.util.ArrayList;

public class VotedData {
    public Info info;
    public ArrayList<Voted> list;

    public class Info {
        public int code;
        public String msg;
    }
}
