package cz.msebera.android.httpclient.client.entity;

import cz.msebera.android.httpclient.HttpEntity;

public class DeflateDecompressingEntity extends DecompressingEntity {
    public DeflateDecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity, new a());
    }
}
