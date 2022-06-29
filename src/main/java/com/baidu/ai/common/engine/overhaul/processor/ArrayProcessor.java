package com.baidu.ai.common.engine.overhaul.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baidu.ai.common.engine.overhaul.node.TreeNode;
import com.baidu.ai.common.engine.overhaul.node.function.ArrayNode;
import com.baidu.ai.common.engine.overhaul.traversion.Traverser;

public class ArrayProcessor implements IProcessor<ArrayNode> {

    public static final ArrayProcessor INSTANCE = new ArrayProcessor();

    private ArrayProcessor() {
    }

    @Override
    public Object process(ArrayNode arrayNode, JSONObject raw) {
        JSONArray ret = new JSONArray();
        TreeNode treeNode = arrayNode.getTreeNode();

        String path = arrayNode.path();
        Object o = JSONPath.eval(raw, path);
        if (o == null || !(o instanceof JSONArray)) {
            return null;
        }

        ((JSONArray) o).stream()
                .forEach(raw0 -> {
                    if (!(raw0 instanceof JSONObject)) {
                        return;
                    }

                    JSONObject tmp = Traverser.traverseTreeNode(treeNode, (JSONObject) raw0);
                    if (tmp == null || tmp.size() == 0) {
                        return;
                    }

                    ret.add(tmp);
                });

        return ret;
    }
}
