package qsbk.app.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

final class c extends SimpleTask {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ File b;

    c(JSONObject jSONObject, File file) {
        this.a = jSONObject;
        this.b = file;
    }

    public Object proccess() throws QiushibaikeException {
        if (this.a == null) {
            this.b.delete();
        } else {
            try {
                FileWriter fileWriter = new FileWriter(this.b);
                fileWriter.write(this.a.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
