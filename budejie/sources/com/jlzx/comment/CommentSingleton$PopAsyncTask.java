package com.jlzx.comment;

import android.os.AsyncTask;

class CommentSingleton$PopAsyncTask extends AsyncTask<Integer, Integer, String> {
    final /* synthetic */ CommentSingleton this$0;

    CommentSingleton$PopAsyncTask(CommentSingleton commentSingleton) {
        this.this$0 = commentSingleton;
    }

    protected String doInBackground(Integer... numArr) {
        try {
            Thread.sleep((long) numArr[0].intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected void onPostExecute(String str) {
        try {
            CommentSingleton.access$000(this.this$0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
