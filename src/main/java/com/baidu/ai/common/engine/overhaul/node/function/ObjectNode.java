package com.baidu.ai.common.engine.overhaul.node.function;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.OverhaulGrammer;
import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.node.TreeNode;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public class ObjectNode extends FunctionalNode {

    public enum MethodType {
        OBJ,
        VALUE
    }

    private TreeNode treeNode; // 配置中设置 json 或 array 的场景下使用

    private Object cfgValue; // 配置中设置 value ，不解析直接赋值的场景下使用

    private MethodType methodType;

    public ObjectNode(@NotNull Morpheme morpheme) {
        super(morpheme);
    }

    @Override
    public void parseAttributes(Object cfg) {
        if (cfg instanceof JSONObject) {
            methodType = MethodType.OBJ;
            treeNode = OverhaulGrammer.generate((JSONObject) cfg);
            return;
        }

        methodType = MethodType.VALUE;
        cfgValue = cfg;
    }

    public MethodType methodType() {
        return methodType;
    }

    public Object getCfgValue() {
        return cfgValue;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("name:" + name())
                .add("type:Object");
        return joiner.toString();
    }
}
