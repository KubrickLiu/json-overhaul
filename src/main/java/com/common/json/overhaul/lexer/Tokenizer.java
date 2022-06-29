package com.common.json.overhaul.lexer;

import com.common.json.overhaul.node.NodeType;
import org.apache.commons.lang3.StringUtils;

public class Tokenizer {

    private static final String SEPARATOR_PATTERN = "#|@|\\$";

    /**
     * 词法解析过程
     *
     * @param content
     * @return
     */
    public static Morpheme tokenize(final String content) {
        // 校验合法性，否则返回无效对象
        if (!verifyContentValidity(content)) {
            return InvalidMorpheme.INSTANCE;
        }

        // 语句切割
        final String[] words = content.split(SEPARATOR_PATTERN);

        // 获得词素的名称
        final String morphemeName = words[0];

        // 构建组装器
        MorphemeBuilder builder = Morpheme.builder()
                .withName(morphemeName)
                .withContent(content);

        final int wordSize = words.length;

        switch (wordSize) {
            case 1:
                // 单一对象，为 object 类型
                builder.withNodeType(NodeType.OBJECT);
                break;

            case 2:
                // 获取拓展字
                final String morphemeExt = words[1];
                builder.withExt(morphemeExt);

                NodeType type = getNodeType(content);
                switch (type) {
                    case KV:
                    case ARRAY:
                    case OPERATOR:
                        builder.withNodeType(type);
                        break;

                    default:
                        throw new RuntimeException("cannot recognize node type from content :"
                                + content);
                }
                break;

            default:
                throw new RuntimeException("cannot recognize and tokenize content:" + content);
        }

        Morpheme morpheme = builder.build();
        return morpheme;
    }

    /**
     * 校验 content 合法性
     *
     * @param content
     * @return
     */
    private static boolean verifyContentValidity(final String content) {
        return StringUtils.isNotBlank(content);
    }

    /**
     * 从原始文本 content 中提取 node type 类型
     *
     * @param content
     * @return
     */
    private static NodeType getNodeType(final String content) {
        for (NodeType type : NodeType.values()) {
            if (type == NodeType.INVALID || type == NodeType.OBJECT) {
                continue;
            }

            if (content.contains(type.word)) {
                return type;
            }
        }

        return NodeType.INVALID;
    }
}
