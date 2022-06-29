package com.baidu.ai.common.engine.overhaul.lexer;

import com.baidu.ai.common.engine.overhaul.node.NodeType;

public class MorphemeBuilder {

    private NodeType nodeType;

    private String content;

    private String name;

    private String ext;

    protected MorphemeBuilder() {
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public MorphemeBuilder withNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MorphemeBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return name;
    }

    public MorphemeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public String getExt() {
        return ext;
    }

    public MorphemeBuilder withExt(String ext) {
        this.ext = ext;
        return this;
    }

    public Morpheme build() {
        return new Morpheme(nodeType, content, name, ext);
    }
}
