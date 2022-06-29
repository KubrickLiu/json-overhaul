package com.common.json.overhaul.lexer;

import com.common.json.overhaul.node.NodeType;

/**
 * 词素
 */
public class Morpheme {

    private final NodeType nodeType;

    private final String content;

    private final String name;

    private final String ext;

    protected Morpheme(NodeType nodeType,
                       String content,
                       String name,
                       String ext) {
        this.nodeType = nodeType;
        this.content = content;
        this.name = name;
        this.ext = ext;
    }

    public static MorphemeBuilder builder() {
        return new MorphemeBuilder();
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getExt() {
        return ext;
    }
}
