package com.common.json.overhaul.processor.operator;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.node.function.OperatorNode;

public abstract class IOperatedHandler {

    protected OperatorNode operatorNode;

    public abstract Object execute(JSONObject raw);

    public abstract void parseAttributes(Object cfg);

    public void setOperatorNode(OperatorNode operatorNode) {
        this.operatorNode = operatorNode;
    }
}
