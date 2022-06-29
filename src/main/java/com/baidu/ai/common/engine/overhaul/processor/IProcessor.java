package com.baidu.ai.common.engine.overhaul.processor;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.node.function.FunctionalNode;

public interface IProcessor<NODE extends FunctionalNode> {

    Object process(NODE node, JSONObject raw);
}
