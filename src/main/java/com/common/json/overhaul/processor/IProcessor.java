package com.common.json.overhaul.processor;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.node.function.FunctionalNode;

public interface IProcessor<NODE extends FunctionalNode> {

    Object process(NODE node, JSONObject raw);
}
