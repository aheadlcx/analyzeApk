package org.mozilla.javascript;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class ScriptRuntime$DefaultMessageProvider implements ScriptRuntime$MessageProvider {
    private ScriptRuntime$DefaultMessageProvider() {
    }

    public String getMessage(String str, Object[] objArr) {
        String str2 = "org.mozilla.javascript.resources.Messages";
        Context currentContext = Context.getCurrentContext();
        try {
            return new MessageFormat(ResourceBundle.getBundle("org.mozilla.javascript.resources.Messages", currentContext != null ? currentContext.getLocale() : Locale.getDefault()).getString(str)).format(objArr);
        } catch (MissingResourceException e) {
            throw new RuntimeException("no message resource found for message property " + str);
        }
    }
}
