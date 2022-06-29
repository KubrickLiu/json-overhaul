package com.baidu.ai.common.engine.overhaul.node;

import com.baidu.ai.common.engine.overhaul.lexer.InvalidMorpheme;
import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.node.function.FunctionalNode;
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
