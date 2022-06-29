package com.common.json.overhaul.operatorhandler;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.operatorhandler.expr.ExprScript;
import com.common.json.overhaul.operatorhandler.expr.ExprUtils;
import com.common.json.overhaul.processor.operator.IOperatedHandler;
import com.common.json.overhaul.processor.operator.OperatedTag;

@OperatedTag(reversedWord = "arithmetic")
public class Arithmetic extends IOperatedHandler {

    private ExprScript exprScript;

    @Override
    public Object execute(JSONObject raw) {
        if (exprScript == null) {
            return null;
        }

        Number ret = ExprUtils.execute(exprScript, raw);
        return ret;
    }

    @Override
    public void parseAttributes(Object cfg) {
        final String rawScript = (String) cfg;
        this.exprScript = ExprUtils.generateExprScript(rawScript);
    }
}
