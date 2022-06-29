package com.baidu.ai.common.engine.overhaul.operatorhandler.expr;

import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.node.NodeType;
import com.baidu.ai.common.engine.overhaul.node.function.ObjectNode;

public class ExprWithStructScript extends ExprScript {

    private final ObjectNode objectNode;

    public ExprWithStructScript(String name, String rawScript, Object struct) {
        super(rawScript);

        Morpheme morpheme = Morpheme.builder()
                .withNodeType(NodeType.OBJECT)
                .withContent(name)
                .withName(name)
                .build();
        this.objectNode = new ObjectNode(morpheme);
        this.objectNode.parseAttributes(struct);
    }

    public ObjectNode objectNode() {
        return objectNode;
    }
}
