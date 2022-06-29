package com.baidu.ai.common.engine.overhaul;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.lexer.Morpheme;
import com.baidu.ai.common.engine.overhaul.lexer.Tokenizer;
import com.baidu.ai.common.engine.overhaul.node.TreeNode;
import com.baidu.ai.common.engine.overhaul.node.function.FunctionalNode;
import com.baidu.ai.common.engine.overhaul.processor.OperatorProcessor;
import com.baidu.ai.common.engine.overhaul.processor.operator.OperatedHandlerManager;
import com.baidu.ai.common.engine.overhaul.transformation.Transformer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverhaulGrammer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverhaulGrammer.class);

    static {
        try {
            OperatedHandlerManager.load(OperatorProcessor.DEFAULT_CLASS_PATH);
        } catch (Exception e) {
            LOGGER.error("initialize operator handler error.", e);
        }
    }

    /**
     * 完成语法解析和词素转换，最终得到可遍历处理的节点类型 TreeNode
     *
     * @param cfg
     * @return
     */
    public static TreeNode generate(@NotNull JSONObject cfg) {
        TreeNode treeNode = new TreeNode();

        cfg.entrySet().stream()
                .forEach(entry -> {
                    String content = entry.getKey();
                    Object nodeCfg = entry.getValue();

                    // 词素解析
                    Morpheme morpheme = Tokenizer.tokenize(content);

                    // 词素转换成功能节点 FunctionalNode
                    FunctionalNode functionalNode = Transformer.transform(morpheme, nodeCfg);

                    // TreeNode 装载功能节点
                    treeNode.appendFunctionalNode(functionalNode);
                });

        return treeNode;
    }
}
