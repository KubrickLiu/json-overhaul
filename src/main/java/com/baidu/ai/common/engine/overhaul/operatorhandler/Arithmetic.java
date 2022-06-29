package com.baidu.ai.common.engine.overhaul.operatorhandler;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.operatorhandler.expr.ExprScript;
import com.baidu.ai.common.engine.overhaul.operatorhandler.expr.ExprUtils;
import com.baidu.ai.common.engine.overhaul.processor.operator.IOperatedHandler;
import com.baidu.ai.common.engine.overhaul.processor.operator.OperatedTag;

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
