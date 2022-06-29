package com.baidu.ai.common.engine.overhaul.transformation;

import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.node.NodeType;
import com.baidu.ai.common.engine.overhaul.node.function.ArrayNode;
import com.baidu.ai.common.engine.overhaul.node.function.FunctionalNode;
import com.baidu.ai.common.engine.overhaul.node.InvalidFunctionalNode;
import com.baidu.ai.common.engine.overhaul.node.function.KVNode;
import com.baidu.ai.common.engine.overhaul.node.function.ObjectNode;
import com.baidu.ai.common.engine.overhaul.node.function.OperatorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transformer.class);

    public static FunctionalNode transform(final Morpheme morpheme,
                                           final Object cfg) {
        FunctionalNode functionalNode = InvalidFunctionalNode.INSTANCE;
        NodeType nodeType = morpheme.getNodeType();

        switch (nodeType) {
            case OBJECT:
                functionalNode = new ObjectNode(morpheme);
                break;

            case KV:
                functionalNode = new KVNode(morpheme);
                break;

            case ARRAY:
                functionalNode = new ArrayNode(morpheme);
                break;

            case OPERATOR:
                functionalNode = new OperatorNode(morpheme);
                break;

            default:
                LOGGER.warn("cannot access here, nodeType:" + nodeType.name());
                break;
        }

        if (functionalNode != InvalidFunctionalNode.INSTANCE) {
            functionalNode.parseAttributes(cfg);
        }

        return functionalNode;
    }
}
