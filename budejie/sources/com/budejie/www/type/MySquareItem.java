package com.budejie.www.type;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MySquareItem {
    @SerializedName("tag_list")
    private List<MySquareTag> mySquareTags;
    @SerializedName("square_list")
    private List<MySquareIcon> mySquares;
    @SerializedName("list")
    private List<MySquareIcon> mySquaresMoreIcon;

    public List<MySquareIcon> getMySquaresMoreIcon() {
        return this.mySquaresMoreIcon;
    }

    public void setMySquaresMoreIcon(List<MySquareIcon> list) {
        this.mySquaresMoreIcon = list;
    }

    public List<MySquareTag> getMySquareTags() {
        return this.mySquareTags;
    }

    public void setMySquareTags(List<MySquareTag> list) {
        this.mySquareTags = list;
    }

    public List<MySquareIcon> getMySquares() {
        return this.mySquares;
    }

    public void setMySquares(List<MySquareIcon> list) {
        this.mySquares = list;
    }
}
