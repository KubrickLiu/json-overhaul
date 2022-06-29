package com.common.json.overhaul.node;

public enum NodeType {

    INVALID(""),  // 无效类型

    KV("@"),  // key-value 仅赋值类型

    ARRAY("#"),  // array 数组操作类型

    OPERATOR("$"),  // operator 自定义处理类型

    OBJECT(""); // object 新增一个对象结构类型

    public final String word;

    NodeType(String word) {
        this.word = word;
    }
}
