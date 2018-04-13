package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;
    @Deprecated
    protected boolean _includeImmediateParent;
    protected boolean _includePath;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonParser);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._includePath = z;
        this._allowMultipleMatches = z2;
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public final int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? 0 : jsonToken.id();
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            if (i == 0) {
                return true;
            }
            return false;
        } else if (jsonToken.id() != i) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    public String getCurrentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return _filterContext.getCurrentName();
        }
        _filterContext = _filterContext.getParent();
        return _filterContext == null ? null : _filterContext.getCurrentName();
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public void overrideCurrentName(String str) {
        throw new UnsupportedOperationException("Can not currently override name during filtering read");
    }

    public JsonToken nextToken() throws IOException {
        JsonToken nextTokenToRead;
        TokenFilterContext tokenFilterContext = this._exposedContext;
        if (tokenFilterContext != null) {
            do {
                nextTokenToRead = tokenFilterContext.nextTokenToRead();
                if (nextTokenToRead != null) {
                    this._currToken = nextTokenToRead;
                    return nextTokenToRead;
                } else if (tokenFilterContext == this._headContext) {
                    this._exposedContext = null;
                    if (tokenFilterContext.inArray()) {
                        nextTokenToRead = this.delegate.getCurrentToken();
                        this._currToken = nextTokenToRead;
                        return nextTokenToRead;
                    }
                } else {
                    tokenFilterContext = this._headContext.findChildOf(tokenFilterContext);
                    this._exposedContext = tokenFilterContext;
                }
            } while (tokenFilterContext != null);
            throw _constructError("Unexpected problem: chain of filtered context broken");
        }
        nextTokenToRead = this.delegate.nextToken();
        if (nextTokenToRead == null) {
            this._currToken = nextTokenToRead;
            return nextTokenToRead;
        }
        TokenFilter tokenFilter;
        TokenFilter filter;
        switch (nextTokenToRead.id()) {
            case 1:
                tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    this._currToken = nextTokenToRead;
                    return nextTokenToRead;
                } else if (tokenFilter == null) {
                    this.delegate.skipChildren();
                    break;
                } else {
                    tokenFilter = this._headContext.checkValue(tokenFilter);
                    if (tokenFilter == null) {
                        this.delegate.skipChildren();
                        break;
                    }
                    if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                        tokenFilter = tokenFilter.filterStartObject();
                    }
                    this._itemFilter = tokenFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                        this._currToken = nextTokenToRead;
                        return nextTokenToRead;
                    }
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, false);
                    if (this._includePath) {
                        nextTokenToRead = _nextTokenWithBuffering(this._headContext);
                        if (nextTokenToRead != null) {
                            this._currToken = nextTokenToRead;
                            return nextTokenToRead;
                        }
                    }
                }
                break;
            case 2:
            case 4:
                boolean isStartHandled = this._headContext.isStartHandled();
                filter = this._headContext.getFilter();
                if (!(filter == null || filter == TokenFilter.INCLUDE_ALL)) {
                    filter.filterFinishArray();
                }
                this._headContext = this._headContext.getParent();
                this._itemFilter = this._headContext.getFilter();
                if (isStartHandled) {
                    this._currToken = nextTokenToRead;
                    return nextTokenToRead;
                }
                break;
            case 3:
                tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildArrayContext(tokenFilter, true);
                    this._currToken = nextTokenToRead;
                    return nextTokenToRead;
                } else if (tokenFilter == null) {
                    this.delegate.skipChildren();
                    break;
                } else {
                    tokenFilter = this._headContext.checkValue(tokenFilter);
                    if (tokenFilter == null) {
                        this.delegate.skipChildren();
                        break;
                    }
                    if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                        tokenFilter = tokenFilter.filterStartArray();
                    }
                    this._itemFilter = tokenFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildArrayContext(tokenFilter, true);
                        this._currToken = nextTokenToRead;
                        return nextTokenToRead;
                    }
                    this._headContext = this._headContext.createChildArrayContext(tokenFilter, false);
                    if (this._includePath) {
                        nextTokenToRead = _nextTokenWithBuffering(this._headContext);
                        if (nextTokenToRead != null) {
                            this._currToken = nextTokenToRead;
                            return nextTokenToRead;
                        }
                    }
                }
                break;
            case 5:
                String currentName = this.delegate.getCurrentName();
                filter = this._headContext.setFieldName(currentName);
                if (filter == TokenFilter.INCLUDE_ALL) {
                    JsonToken jsonToken;
                    this._itemFilter = filter;
                    if (this._includePath || !this._includeImmediateParent || this._headContext.isStartHandled()) {
                        jsonToken = nextTokenToRead;
                    } else {
                        jsonToken = this._headContext.nextTokenToRead();
                        this._exposedContext = this._headContext;
                    }
                    this._currToken = jsonToken;
                    return jsonToken;
                } else if (filter == null) {
                    this.delegate.nextToken();
                    this.delegate.skipChildren();
                    break;
                } else {
                    tokenFilter = filter.includeProperty(currentName);
                    if (tokenFilter == null) {
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                        break;
                    }
                    this._itemFilter = tokenFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL && this._includePath) {
                        this._currToken = nextTokenToRead;
                        return nextTokenToRead;
                    } else if (this._includePath) {
                        nextTokenToRead = _nextTokenWithBuffering(this._headContext);
                        if (nextTokenToRead != null) {
                            this._currToken = nextTokenToRead;
                            return nextTokenToRead;
                        }
                    }
                }
                break;
            default:
                tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._currToken = nextTokenToRead;
                    return nextTokenToRead;
                } else if (tokenFilter != null) {
                    tokenFilter = this._headContext.checkValue(tokenFilter);
                    if (tokenFilter == TokenFilter.INCLUDE_ALL || (tokenFilter != null && tokenFilter.includeValue(this.delegate))) {
                        this._currToken = nextTokenToRead;
                        return nextTokenToRead;
                    }
                }
                break;
        }
        return _nextToken2();
    }

    protected final JsonToken _nextToken2() throws IOException {
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                this._currToken = nextToken;
                return nextToken;
            }
            TokenFilter tokenFilter;
            JsonToken _nextTokenWithBuffering;
            TokenFilter filter;
            switch (nextToken.id()) {
                case 1:
                    tokenFilter = this._itemFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                        this._currToken = nextToken;
                        return nextToken;
                    } else if (tokenFilter == null) {
                        this.delegate.skipChildren();
                        break;
                    } else {
                        tokenFilter = this._headContext.checkValue(tokenFilter);
                        if (tokenFilter == null) {
                            this.delegate.skipChildren();
                            break;
                        }
                        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                            tokenFilter = tokenFilter.filterStartObject();
                        }
                        this._itemFilter = tokenFilter;
                        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        this._headContext = this._headContext.createChildObjectContext(tokenFilter, false);
                        if (this._includePath) {
                            _nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext);
                            if (_nextTokenWithBuffering == null) {
                                break;
                            }
                            this._currToken = _nextTokenWithBuffering;
                            return _nextTokenWithBuffering;
                        }
                        continue;
                    }
                case 2:
                case 4:
                    boolean isStartHandled = this._headContext.isStartHandled();
                    filter = this._headContext.getFilter();
                    if (!(filter == null || filter == TokenFilter.INCLUDE_ALL)) {
                        filter.filterFinishArray();
                    }
                    this._headContext = this._headContext.getParent();
                    this._itemFilter = this._headContext.getFilter();
                    if (!isStartHandled) {
                        break;
                    }
                    this._currToken = nextToken;
                    return nextToken;
                case 3:
                    tokenFilter = this._itemFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildArrayContext(tokenFilter, true);
                        this._currToken = nextToken;
                        return nextToken;
                    } else if (tokenFilter == null) {
                        this.delegate.skipChildren();
                        break;
                    } else {
                        tokenFilter = this._headContext.checkValue(tokenFilter);
                        if (tokenFilter == null) {
                            this.delegate.skipChildren();
                            break;
                        }
                        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                            tokenFilter = tokenFilter.filterStartArray();
                        }
                        this._itemFilter = tokenFilter;
                        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        this._headContext = this._headContext.createChildArrayContext(tokenFilter, false);
                        if (this._includePath) {
                            _nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext);
                            if (_nextTokenWithBuffering == null) {
                                break;
                            }
                            this._currToken = _nextTokenWithBuffering;
                            return _nextTokenWithBuffering;
                        }
                        continue;
                    }
                case 5:
                    String currentName = this.delegate.getCurrentName();
                    filter = this._headContext.setFieldName(currentName);
                    if (filter == TokenFilter.INCLUDE_ALL) {
                        this._itemFilter = filter;
                        this._currToken = nextToken;
                        return nextToken;
                    } else if (filter == null) {
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                        break;
                    } else {
                        tokenFilter = filter.includeProperty(currentName);
                        if (tokenFilter == null) {
                            this.delegate.nextToken();
                            this.delegate.skipChildren();
                            break;
                        }
                        this._itemFilter = tokenFilter;
                        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                            if (!this._includePath) {
                                break;
                            }
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (this._includePath) {
                            _nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext);
                            if (_nextTokenWithBuffering == null) {
                                break;
                            }
                            this._currToken = _nextTokenWithBuffering;
                            return _nextTokenWithBuffering;
                        } else {
                            continue;
                        }
                    }
                default:
                    tokenFilter = this._itemFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        this._currToken = nextToken;
                        return nextToken;
                    } else if (tokenFilter != null) {
                        tokenFilter = this._headContext.checkValue(tokenFilter);
                        if (tokenFilter == TokenFilter.INCLUDE_ALL || (tokenFilter != null && tokenFilter.includeValue(this.delegate))) {
                            this._currToken = nextToken;
                            return nextToken;
                        }
                    } else {
                        continue;
                    }
            }
        }
    }

    protected final JsonToken _nextTokenWithBuffering(TokenFilterContext tokenFilterContext) throws IOException {
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                return nextToken;
            }
            TokenFilter tokenFilter;
            switch (nextToken.id()) {
                case 1:
                    tokenFilter = this._itemFilter;
                    if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                        if (tokenFilter != null) {
                            tokenFilter = this._headContext.checkValue(tokenFilter);
                            if (tokenFilter != null) {
                                if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                                    tokenFilter = tokenFilter.filterStartObject();
                                }
                                this._itemFilter = tokenFilter;
                                if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, false);
                                    break;
                                }
                                this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                                return _nextBuffered(tokenFilterContext);
                            }
                            this.delegate.skipChildren();
                            break;
                        }
                        this.delegate.skipChildren();
                        break;
                    }
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    return nextToken;
                case 2:
                case 4:
                    boolean z;
                    tokenFilter = this._headContext.getFilter();
                    if (!(tokenFilter == null || tokenFilter == TokenFilter.INCLUDE_ALL)) {
                        tokenFilter.filterFinishArray();
                    }
                    boolean z2 = this._headContext == tokenFilterContext;
                    if (z2 && this._headContext.isStartHandled()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this._headContext = this._headContext.getParent();
                    this._itemFilter = this._headContext.getFilter();
                    if (!z) {
                        if (!z2 && this._headContext != tokenFilterContext) {
                            break;
                        }
                        return null;
                    }
                    return nextToken;
                case 3:
                    tokenFilter = this._headContext.checkValue(this._itemFilter);
                    if (tokenFilter != null) {
                        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                            tokenFilter = tokenFilter.filterStartArray();
                        }
                        this._itemFilter = tokenFilter;
                        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter, false);
                            break;
                        }
                        this._headContext = this._headContext.createChildArrayContext(tokenFilter, true);
                        return _nextBuffered(tokenFilterContext);
                    }
                    this.delegate.skipChildren();
                    break;
                case 5:
                    String currentName = this.delegate.getCurrentName();
                    TokenFilter fieldName = this._headContext.setFieldName(currentName);
                    if (fieldName != TokenFilter.INCLUDE_ALL) {
                        if (fieldName != null) {
                            tokenFilter = fieldName.includeProperty(currentName);
                            if (tokenFilter != null) {
                                this._itemFilter = tokenFilter;
                                if (tokenFilter != TokenFilter.INCLUDE_ALL) {
                                    break;
                                }
                                return _nextBuffered(tokenFilterContext);
                            }
                            this.delegate.nextToken();
                            this.delegate.skipChildren();
                            break;
                        }
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                        break;
                    }
                    this._itemFilter = fieldName;
                    return _nextBuffered(tokenFilterContext);
                default:
                    tokenFilter = this._itemFilter;
                    if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                        return _nextBuffered(tokenFilterContext);
                    }
                    if (tokenFilter != null) {
                        tokenFilter = this._headContext.checkValue(tokenFilter);
                        if (tokenFilter == TokenFilter.INCLUDE_ALL || (tokenFilter != null && tokenFilter.includeValue(this.delegate))) {
                            return _nextBuffered(tokenFilterContext);
                        }
                    }
                    continue;
            }
        }
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead == null) {
            while (tokenFilterContext != this._headContext) {
                tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
                this._exposedContext = tokenFilterContext;
                if (tokenFilterContext == null) {
                    throw _constructError("Unexpected problem: chain of filtered context broken");
                }
                nextTokenToRead = this._exposedContext.nextTokenToRead();
                if (nextTokenToRead != null) {
                }
            }
            throw _constructError("Internal error: failed to locate expected buffered tokens");
        }
        return nextTokenToRead;
    }

    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        if (nextToken == JsonToken.FIELD_NAME) {
            return nextToken();
        }
        return nextToken;
    }

    public JsonParser skipChildren() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken == null) {
                    break;
                } else if (nextToken.isStructStart()) {
                    i++;
                } else if (nextToken.isStructEnd()) {
                    i--;
                    if (i == 0) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return this;
    }

    public String getText() throws IOException {
        return this.delegate.getText();
    }

    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    public NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    public int getValueAsInt(int i) throws IOException {
        return this.delegate.getValueAsInt(i);
    }

    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    public long getValueAsLong(long j) throws IOException {
        return this.delegate.getValueAsLong(j);
    }

    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    public double getValueAsDouble(double d) throws IOException {
        return this.delegate.getValueAsDouble(d);
    }

    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    public boolean getValueAsBoolean(boolean z) throws IOException {
        return this.delegate.getValueAsBoolean(z);
    }

    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    public String getValueAsString(String str) throws IOException {
        return this.delegate.getValueAsString(str);
    }

    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    protected JsonStreamContext _filterContext() {
        if (this._exposedContext != null) {
            return this._exposedContext;
        }
        return this._headContext;
    }
}
