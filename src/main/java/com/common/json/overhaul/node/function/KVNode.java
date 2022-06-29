package com.common.json.overhaul.node.function;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.lexer.Morpheme;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

/**
 * 关键字是 @ 符号
 *
 * KV 仅赋值类型
 * "a@b":{
 *     "default_value": xxxxxx
 * },
 *
 */
public class KVNode extends FunctionalNode {

    /**
     * 默认值的 key
     */
    private static final String DEFAULT_VALUE = "default_value";

    private Object defaultValue;

    public KVNode(@NotNull Morpheme morpheme) {
        super(morpheme);
    }

    @Override
    public void parseAttributes(Object cfg) {
        if (cfg == null || !(cfg instanceof JSONObject)) {
            return;
        }

        final JSONObject jsonCfg = (JSONObject) cfg;
        this.defaultValue = jsonCfg.get(DEFAULT_VALUE);
    }

    public String path() {
        return morpheme.getExt();
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("name:" + name())
                .add("type:KV");
        return joiner.toString();
    }
}
