package com.baidu.ai.common.engine.overhaul.processor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baidu.ai.common.engine.overhaul.node.function.KVNode;

public class KVProcessor implements IProcessor<KVNode> {

    public static final KVProcessor INSTANCE = new KVProcessor();

    private KVProcessor() {}

    @Override
    public Object process(KVNode kvNode, JSONObject raw) {
        final String path = kvNode.path();

        Object v = JSONPath.eval(raw, path);
        if (v == null) {
            v = kvNode.getDefaultValue();
        }

        return v;
    }
}
