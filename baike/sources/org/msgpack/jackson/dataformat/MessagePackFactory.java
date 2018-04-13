package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.IOContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePack.PackerConfig;

public class MessagePackFactory extends JsonFactory {
    private static final long serialVersionUID = 2578263992015504347L;
    private ExtensionTypeCustomDeserializers extTypeCustomDesers;
    private final PackerConfig packerConfig;
    private boolean reuseResourceInGenerator;
    private boolean reuseResourceInParser;

    public MessagePackFactory() {
        this(MessagePack.DEFAULT_PACKER_CONFIG);
    }

    public MessagePackFactory(PackerConfig packerConfig) {
        this.reuseResourceInGenerator = true;
        this.reuseResourceInParser = true;
        this.packerConfig = packerConfig;
    }

    public MessagePackFactory setReuseResourceInGenerator(boolean z) {
        this.reuseResourceInGenerator = z;
        return this;
    }

    public MessagePackFactory setReuseResourceInParser(boolean z) {
        this.reuseResourceInParser = z;
        return this;
    }

    public MessagePackFactory setExtTypeCustomDesers(ExtensionTypeCustomDeserializers extensionTypeCustomDeserializers) {
        this.extTypeCustomDesers = extensionTypeCustomDeserializers;
        return this;
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        return new MessagePackGenerator(this._generatorFeatures, this._objectCodec, outputStream, this.packerConfig, this.reuseResourceInGenerator);
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(new FileOutputStream(file), jsonEncoding);
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        throw new UnsupportedOperationException();
    }

    public JsonParser createParser(byte[] bArr) throws IOException, JsonParseException {
        return _createParser(bArr, 0, bArr.length, _createContext(bArr, false));
    }

    public JsonParser createParser(InputStream inputStream) throws IOException, JsonParseException {
        return _createParser(inputStream, _createContext(inputStream, false));
    }

    protected MessagePackParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException {
        MessagePackParser messagePackParser = new MessagePackParser(iOContext, this._parserFeatures, this._objectCodec, inputStream, this.reuseResourceInParser);
        if (this.extTypeCustomDesers != null) {
            messagePackParser.setExtensionTypeCustomDeserializers(this.extTypeCustomDesers);
        }
        return messagePackParser;
    }

    protected JsonParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        byte[] bArr2;
        if (i == 0 && i2 == bArr.length) {
            bArr2 = bArr;
        } else {
            bArr2 = Arrays.copyOfRange(bArr, i, i + i2);
        }
        JsonParser messagePackParser = new MessagePackParser(iOContext, this._parserFeatures, this._objectCodec, bArr2, this.reuseResourceInParser);
        if (this.extTypeCustomDesers != null) {
            messagePackParser.setExtensionTypeCustomDeserializers(this.extTypeCustomDesers);
        }
        return messagePackParser;
    }
}
