package com.baidu.ai.common.engine.overhaul.node;

import com.baidu.ai.common.engine.overhaul.node.function.FunctionalNode;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 树状节点
 */
public class TreeNode implements INode {

    private final LinkedList<FunctionalNode> children;

    public TreeNode() {
        this.children = new LinkedList<>();
    }

    @Override
    public String name() {
        return "tree_node";
    }

    /**
     * 添加功能性节点
     *
     * @param node
     * @return
     */
    public TreeNode appendFunctionalNode(@NotNull FunctionalNode node) {
        if (node != InvalidFunctionalNode.INSTANCE) {
            children.add(node);
        }
        return this;
    }

    public Iterator<FunctionalNode> functionalNodeIterator() {
        return children.iterator();
    }
}
