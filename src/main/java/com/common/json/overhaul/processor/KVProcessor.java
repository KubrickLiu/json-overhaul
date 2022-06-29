package com.common.json.overhaul.processor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.common.json.overhaul.node.function.KVNode;

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
