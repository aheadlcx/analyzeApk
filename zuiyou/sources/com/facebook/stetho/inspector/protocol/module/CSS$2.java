package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Origin;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.elements.StyleRuleNameAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSRule;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSStyle;
import com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeRequest;
import com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeResult;
import com.facebook.stetho.inspector.protocol.module.CSS.RuleMatch;
import com.facebook.stetho.inspector.protocol.module.CSS.Selector;
import com.facebook.stetho.inspector.protocol.module.CSS.SelectorList;
import java.util.ArrayList;
import java.util.Collections;

class CSS$2 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ GetMatchedStylesForNodeRequest val$request;
    final /* synthetic */ GetMatchedStylesForNodeResult val$result;

    CSS$2(CSS css, GetMatchedStylesForNodeRequest getMatchedStylesForNodeRequest, GetMatchedStylesForNodeResult getMatchedStylesForNodeResult) {
        this.this$0 = css;
        this.val$request = getMatchedStylesForNodeRequest;
        this.val$result = getMatchedStylesForNodeResult;
    }

    public void run() {
        final Object elementForNodeId = CSS.access$200(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (elementForNodeId == null) {
            LogUtil.w("Failed to get style of an element that does not exist, nodeid=" + this.val$request.nodeId);
        } else {
            CSS.access$200(this.this$0).getElementStyleRuleNames(elementForNodeId, new StyleRuleNameAccumulator() {
                public void store(String str, boolean z) {
                    final Object arrayList = new ArrayList();
                    RuleMatch ruleMatch = new RuleMatch(null);
                    ruleMatch.matchingSelectors = ListUtil.newImmutableList(Integer.valueOf(0));
                    Selector selector = new Selector(null);
                    selector.value = str;
                    CSSRule cSSRule = new CSSRule(null);
                    cSSRule.origin = Origin.REGULAR;
                    cSSRule.selectorList = new SelectorList(null);
                    cSSRule.selectorList.selectors = ListUtil.newImmutableList(selector);
                    cSSRule.style = new CSSStyle(null);
                    cSSRule.style.cssProperties = arrayList;
                    cSSRule.style.shorthandEntries = Collections.emptyList();
                    if (z) {
                        cSSRule.style.styleSheetId = String.format("%s.%s", new Object[]{Integer.toString(CSS$2.this.val$request.nodeId), selector.value});
                    }
                    CSS.access$200(CSS$2.this.this$0).getElementStyles(elementForNodeId, str, new StyleAccumulator() {
                        public void store(String str, String str2, boolean z) {
                            CSSProperty cSSProperty = new CSSProperty(null);
                            cSSProperty.name = str;
                            cSSProperty.value = str2;
                            arrayList.add(cSSProperty);
                        }
                    });
                    ruleMatch.rule = cSSRule;
                    CSS$2.this.val$result.matchedCSSRules.add(ruleMatch);
                }
            });
        }
    }
}
