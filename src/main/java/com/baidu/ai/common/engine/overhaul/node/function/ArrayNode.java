package com.baidu.ai.common.engine.overhaul.node.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.OverhaulGrammer;
import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.node.TreeNode;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public class ArrayNode extends FunctionalNode {

    private TreeNode treeNode;

    public ArrayNode(@NotNull Morpheme morpheme) {
        super(morpheme);
    }

    @Override
    public void parseAttributes(Object cfg) {
        JSONObject configure = getConfigure((JSON) cfg);
        this.treeNode = OverhaulGrammer.generate(configure);
    }

    private JSONObject getConfigure(JSON cfg) {
        if (cfg instanceof JSONObject) {
            return (JSONObject) cfg;
        } else if (cfg instanceof JSONArray) {
            return ((JSONArray) cfg).getJSONObject(0);
        }

        throw new RuntimeException();
    }

    public String path() {
        return morpheme.getExt();
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("name:" + name())
                .add("type:Array");
        return joiner.toString();
    }
}
