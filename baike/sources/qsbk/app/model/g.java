package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class g implements Creator<Comment> {
    g() {
    }

    public Comment createFromParcel(Parcel parcel) {
        Comment comment = new Comment();
        comment.content = parcel.readString();
        comment.icon = parcel.readString();
        comment.id = parcel.readString();
        comment.role = parcel.readString();
        comment.uid = parcel.readString();
        comment.userName = parcel.readString();
        comment.floor = parcel.readInt();
        comment.likeCount = parcel.readInt();
        comment.liked = parcel.readInt() != 0;
        comment.createAt = parcel.readInt();
        comment.reply = (Comment) parcel.readParcelable(Comment.class.getClassLoader());
        return comment;
    }

    public Comment[] newArray(int i) {
        return new Comment[i];
    }
}
