package com.baidu.ai.common.engine.overhaul.lexer;

import com.baidu.ai.common.engine.overhaul.node.NodeType;

public class InvalidMorpheme extends Morpheme {

    public static final InvalidMorpheme INSTANCE = new InvalidMorpheme();

    protected InvalidMorpheme() {
        super(NodeType.INVALID, "", "invalid", null);
    }
}
