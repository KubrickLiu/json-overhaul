package com.common.json.overhaul.processor.operator;

import com.common.json.overhaul.node.function.OperatorNode;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OperatedHandlerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatedHandlerManager.class);

    private static Map<String, Constructor<IOperatedHandler>> operatorConstructorMap = new ConcurrentHashMap<>();

    public static void register(@NotNull String reversedWord, Constructor constructor) {
        LOGGER.info("OperatedHandlerManager register reversedWord:{}", reversedWord);
        operatorConstructorMap.put(reversedWord, constructor);
    }

    public static IOperatedHandler generate(@NotNull String reversedWord,
                                            @NotNull OperatorNode operatorNode) throws Exception {
        Constructor<IOperatedHandler> constructor = operatorConstructorMap.get(reversedWord);
        if (constructor == null) {
            return null;
        }

        IOperatedHandler handler = constructor.newInstance();
        handler.setOperatorNode(operatorNode);
        return handler;
    }

    public static void load(@NotNull String classPath) throws Exception {
        Reflections reflections = new Reflections(classPath);
        Set<Class<? extends IOperatedHandler>> subTypes = reflections.getSubTypesOf(IOperatedHandler.class);

        for (Class<? extends IOperatedHandler> clazz : subTypes) {
            // 标签
            OperatedTag tag = clazz.getAnnotation(OperatedTag.class);

            if (tag != null) {
                register(tag.reversedWord(), clazz.getDeclaredConstructor());
            }
        }
    }


}
