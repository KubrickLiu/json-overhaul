package com.common.json.overhaul.lexer;

import com.common.json.overhaul.node.NodeType;

public class InvalidMorpheme extends Morpheme {

    public static final InvalidMorpheme INSTANCE = new InvalidMorpheme();

    protected InvalidMorpheme() {
        super(NodeType.INVALID, "", "invalid", null);
    }
}
