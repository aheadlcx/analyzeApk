package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

class Gson$5 extends TypeAdapter<AtomicLong> {
    final /* synthetic */ TypeAdapter val$longAdapter;

    Gson$5(TypeAdapter typeAdapter) {
        this.val$longAdapter = typeAdapter;
    }

    public void write(JsonWriter jsonWriter, AtomicLong atomicLong) throws IOException {
        this.val$longAdapter.write(jsonWriter, Long.valueOf(atomicLong.get()));
    }

    public AtomicLong read(JsonReader jsonReader) throws IOException {
        return new AtomicLong(((Number) this.val$longAdapter.read(jsonReader)).longValue());
    }
}
