package com.baidu.ai.common.engine.overhaul.operatorhandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baidu.ai.common.engine.overhaul.processor.operator.IOperatedHandler;
import com.baidu.ai.common.engine.overhaul.processor.operator.OperatedTag;

@OperatedTag(reversedWord = "count")
public class Count extends IOperatedHandler {

    private String path;

    @Override
    public Object execute(JSONObject raw) {
        Object o = JSONPath.eval(raw, path);
        if (o instanceof JSONObject) {
            return ((JSONObject) o).size();
        }

        if (o instanceof JSONArray) {
            return ((JSONArray) o).size();
        }

        return 1;
    }

    @Override
    public void parseAttributes(Object cfg) {
        this.path = (String) cfg;
    }
}
