package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

class Gson$2 extends TypeAdapter<Number> {
    final /* synthetic */ Gson this$0;

    Gson$2(Gson gson) {
        this.this$0 = gson;
    }

    public Double read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return Double.valueOf(jsonReader.nextDouble());
        }
        jsonReader.nextNull();
        return null;
    }

    public void write(JsonWriter jsonWriter, Number number) throws IOException {
        if (number == null) {
            jsonWriter.nullValue();
            return;
        }
        Gson.checkValidFloatingPoint(number.doubleValue());
        jsonWriter.value(number);
    }
}
