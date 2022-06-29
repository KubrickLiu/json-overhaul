package com.baidu.ai.common.engine.overhaul.node.function;

import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.node.INode;
import com.baidu.ai.common.engine.overhaul.node.NodeType;
import org.jetbrains.annotations.NotNull;

public abstract class FunctionalNode implements INode {

    protected final Morpheme morpheme;

    public FunctionalNode(@NotNull Morpheme morpheme) {
        this.morpheme = morpheme;
    }

    @Override
    public String name() {
        return morpheme.getName();
    }

    public NodeType nodeType() {
        return morpheme.getNodeType();
    }

    /**
     * 获取原始文本
     *
     * @return
     */
    public String getOriginContent() {
        return morpheme.getContent();
    }

    /**
     * 从配置解析出 node 节点所需的属性
     *
     * @param cfg
     */
    public abstract void parseAttributes(Object cfg);
}
