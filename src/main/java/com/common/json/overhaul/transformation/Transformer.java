package com.common.json.overhaul.transformation;

import com.common.json.overhaul.lexer.Morpheme;
import com.common.json.overhaul.node.NodeType;
import com.common.json.overhaul.node.function.ArrayNode;
import com.common.json.overhaul.node.function.FunctionalNode;
import com.common.json.overhaul.node.InvalidFunctionalNode;
import com.common.json.overhaul.node.function.KVNode;
import com.common.json.overhaul.node.function.ObjectNode;
import com.common.json.overhaul.node.function.OperatorNode;
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
