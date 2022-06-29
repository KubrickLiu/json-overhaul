package com.common.json.overhaul.operatorhandler.expr;

import com.common.json.overhaul.lexer.Morpheme;
import com.common.json.overhaul.node.NodeType;
import com.common.json.overhaul.node.function.ObjectNode;

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
