package com.common.json.overhaul.node;

import com.common.json.overhaul.lexer.InvalidMorpheme;
import com.common.json.overhaul.lexer.Morpheme;
import com.common.json.overhaul.node.function.FunctionalNode;
import org.jetbrains.annotations.NotNull;

public class InvalidFunctionalNode extends FunctionalNode {

    public static final InvalidFunctionalNode INSTANCE =
            new InvalidFunctionalNode(InvalidMorpheme.INSTANCE);

    private InvalidFunctionalNode(@NotNull Morpheme morpheme) {
        super(morpheme);
    }

    @Override
    public void parseAttributes(Object cfg) {

    }
}
