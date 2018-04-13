package android.support.v4.speech.tts;

import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.speech.tts.UtteranceProgressListener;
import java.util.Locale;
import java.util.Set;

class TextToSpeechICSMR1 {
    public static final String KEY_FEATURE_EMBEDDED_SYNTHESIS = "embeddedTts";
    public static final String KEY_FEATURE_NETWORK_SYNTHESIS = "networkTts";

    interface UtteranceProgressListenerICSMR1 {
        void onDone(String str);

        void onError(String str);

        void onStart(String str);
    }

    TextToSpeechICSMR1() {
    }

    static Set<String> getFeatures(TextToSpeech textToSpeech, Locale locale) {
        if (VERSION.SDK_INT >= 15) {
            return textToSpeech.getFeatures(locale);
        }
        return null;
    }

    static void setUtteranceProgressListener(TextToSpeech textToSpeech, final UtteranceProgressListenerICSMR1 utteranceProgressListenerICSMR1) {
        if (VERSION.SDK_INT >= 15) {
            textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                public void onStart(String str) {
                    utteranceProgressListenerICSMR1.onStart(str);
                }

                public void onError(String str) {
                    utteranceProgressListenerICSMR1.onError(str);
                }

                public void onDone(String str) {
                    utteranceProgressListenerICSMR1.onDone(str);
                }
            });
        } else {
            textToSpeech.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {
                public void onUtteranceCompleted(String str) {
                    utteranceProgressListenerICSMR1.onStart(str);
                    utteranceProgressListenerICSMR1.onDone(str);
                }
            });
        }
    }
}
