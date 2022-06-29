package com.common.json.overhaul.traversion;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.Overhauler;
import com.common.json.overhaul.node.NodeType;
import com.common.json.overhaul.node.TreeNode;
import com.common.json.overhaul.node.function.ArrayNode;
import com.common.json.overhaul.node.function.FunctionalNode;
import com.common.json.overhaul.node.function.KVNode;
import com.common.json.overhaul.node.function.ObjectNode;
import com.common.json.overhaul.node.function.OperatorNode;
import com.common.json.overhaul.processor.ArrayProcessor;
import com.common.json.overhaul.processor.KVProcessor;
import com.common.json.overhaul.processor.ObjectProcessor;
import com.common.json.overhaul.processor.OperatorProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class Traverser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Traverser.class);

    public static JSONObject traverseOverhauler(Overhauler overhauler,
                                                JSONObject raw) {
        Iterator<TreeNode> iterator = overhauler.nodeIterator();
        while (iterator.hasNext()) {
            TreeNode treeNode = iterator.next();
            raw = traverseTreeNode(treeNode, raw);
        }

        return raw;
    }

    public static JSONObject traverseTreeNode(TreeNode treeNode,
                                              JSONObject raw) {
        Iterator<FunctionalNode> iterator = treeNode.functionalNodeIterator();
        JSONObject ret = new JSONObject();

        while (iterator.hasNext()) {
            FunctionalNode functionalNode = iterator.next();
            Object v = traverseFunctionalNode(functionalNode, raw);

            if (v != null) {
                ret.put(functionalNode.name(), v);
            }
        }

        return ret;
    }

    public static Object traverseFunctionalNode(FunctionalNode functionalNode,
                                                JSONObject raw) {
        NodeType nodeType = functionalNode.nodeType();

        Object v = null;
        switch (nodeType) {
            case KV:
                v = KVProcessor.INSTANCE.process((KVNode) functionalNode, raw);
                break;

            case ARRAY:
                v = ArrayProcessor.INSTANCE.process((ArrayNode) functionalNode, raw);
                break;

            case OBJECT:
                v = ObjectProcessor.INSTANCE.process((ObjectNode) functionalNode, raw);
                break;

            case OPERATOR:
                v = OperatorProcessor.INSTANCE.process((OperatorNode) functionalNode, raw);
                break;

            default:
                Exception e = new IllegalAccessException();
                LOGGER.warn("cannot access here ", e);
                break;
        }

        return v;
    }
}
