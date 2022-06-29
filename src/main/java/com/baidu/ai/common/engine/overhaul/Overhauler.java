package com.baidu.ai.common.engine.overhaul;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.node.TreeNode;
import com.baidu.ai.common.engine.overhaul.traversion.Traverser;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Overhauler {

    private List<TreeNode> treeNodeChain;

    protected Overhauler() {
        treeNodeChain = new LinkedList<>();
    }

    public static Overhauler build(@NotNull String string) {
        Object rawJSON = JSON.parse(string);
        if (rawJSON instanceof JSONObject) {
            return build((JSONObject) rawJSON);
        } else {
            return build((JSONArray) rawJSON);
        }
    }

    public static Overhauler build(@NotNull JSONArray cfg) {
        Overhauler overhauler = new Overhauler();
        Iterator iterator = cfg.iterator();

        while(iterator.hasNext()) {
            JSONObject o = (JSONObject) iterator.next();
            TreeNode tmp = OverhaulGrammer.generate(o);
            overhauler.appendTreeNode(tmp);
        }

        return overhauler;
    }

    public static Overhauler build(@NotNull JSONObject cfg) {
        Overhauler overhauler = new Overhauler();
        TreeNode tmp = OverhaulGrammer.generate(cfg);
        overhauler.appendTreeNode(tmp);
        return overhauler;
    }

    public JSONObject parse(@NotNull String stringOrigin) {
        JSONObject origin = JSONObject.parseObject(stringOrigin);
        return parse(origin);
    }

    public JSONObject parse(@NotNull JSONObject o) {
        return Traverser.traverseOverhauler(this, o);
    }

    protected void appendTreeNode(TreeNode treeNode) {
        treeNodeChain.add(treeNode);
    }

    public Iterator<TreeNode> nodeIterator() {
        return treeNodeChain.iterator();
    }
}
