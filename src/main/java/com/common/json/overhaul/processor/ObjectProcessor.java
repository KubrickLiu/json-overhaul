package com.common.json.overhaul.processor;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.node.TreeNode;
import com.common.json.overhaul.node.function.ObjectNode;
import com.common.json.overhaul.traversion.Traverser;

public class ObjectProcessor implements IProcessor<ObjectNode> {

    public static final ObjectProcessor INSTANCE = new ObjectProcessor();

    private ObjectProcessor() {
    }

    @Override
    public Object process(ObjectNode objectNode, JSONObject raw) {
        ObjectNode.MethodType methodType = objectNode.methodType();

        switch (methodType) {
            case VALUE:
                return objectNode.getCfgValue();

            case OBJ:
                TreeNode treeNode = objectNode.getTreeNode();
                return Traverser.traverseTreeNode(treeNode, raw);

            default:
                throw new IllegalStateException();
        }
    }
}
