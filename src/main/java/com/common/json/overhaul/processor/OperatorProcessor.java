package com.common.json.overhaul.processor;

import com.alibaba.fastjson.JSONObject;
import com.common.json.overhaul.node.function.OperatorNode;
import com.common.json.overhaul.processor.operator.IOperatedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorProcessor implements IProcessor<OperatorNode>{

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatorProcessor.class);

    public static final OperatorProcessor INSTANCE = new OperatorProcessor();

    public static final String DEFAULT_CLASS_PATH = "com.common.json.overhaul.operatorhandler";

    private OperatorProcessor() {
    }

    @Override
    public Object process(OperatorNode node, JSONObject raw) {
        IOperatedHandler handler = node.getHandler();
        if (handler == null) {
            NullPointerException e = new NullPointerException();
            LOGGER.error("OperatorNode:{} handler is null", node.reversedWord(), e);
            return null;
        }

        return handler.execute(raw);
    }
}
