package com.common.json.overhaul.operatorhandler;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.node.function.ObjectNode;
import com.common.json.overhaul.operatorhandler.expr.ExprWithStructScript;
import com.common.json.overhaul.operatorhandler.expr.ExprUtils;
import com.common.json.overhaul.processor.ObjectProcessor;
import com.common.json.overhaul.processor.operator.IOperatedHandler;
import com.common.json.overhaul.processor.operator.OperatedTag;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

@OperatedTag(reversedWord = "if")
public class IF extends IOperatedHandler {

    private List<ExprWithStructScript> exprScripts;

    @Override
    public Object execute(JSONObject raw) {
        if (CollectionUtils.isEmpty(exprScripts)) {
            return null;
        }

        Object ret = exprScripts.stream()
                .filter(exprScript -> ExprUtils.execute(exprScript, raw))
                .findFirst()
                .map(exprScript -> {
                    ObjectNode objectNode = exprScript.objectNode();
                    Object tmp = ObjectProcessor.INSTANCE.process(objectNode, raw);
                    return tmp;
                });

        return ret;
    }

    @Override
    public void parseAttributes(Object cfg) {
        final JSONObject jsonCfg = (JSONObject) cfg;
        this.exprScripts = ExprUtils.generateExprWithStructScripts(operatorNode.name(), jsonCfg);
    }

}
