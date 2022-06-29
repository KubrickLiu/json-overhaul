package com.common.json.overhaul.node.function;

import com.common.json.overhaul.lexer.Morpheme;
import com.common.json.overhaul.processor.operator.IOperatedHandler;
import com.common.json.overhaul.processor.operator.OperatedHandlerManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;

public class OperatorNode extends FunctionalNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatorNode.class);

    private IOperatedHandler handler;

    public OperatorNode(@NotNull Morpheme morpheme) {
        super(morpheme);
    }

    @Override
    public void parseAttributes(Object cfg) {
        try {
            handler = OperatedHandlerManager.generate(reversedWord(), this);
            handler.parseAttributes(cfg);
        } catch (Exception e) {
            LOGGER.warn("OperatedHandlerManager generate error, reversedWord:{}.",
                    reversedWord(), e);
        }
    }

    public String reversedWord() {
        return morpheme.getExt();
    }

    public IOperatedHandler getHandler() {
        return handler;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("name:" + name())
                .add("type:Operator")
                .add("reversedWord:" + reversedWord());
        return joiner.toString();
    }
}
