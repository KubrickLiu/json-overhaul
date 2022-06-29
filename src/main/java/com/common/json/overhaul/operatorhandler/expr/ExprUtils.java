package com.common.json.overhaul.operatorhandler.expr;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExprUtils {

    private static final GroovyShell GROOVY_SHELL = new GroovyShell();

    public static <T> T execute(@NotNull ExprScript exprScript,
                                @NotNull JSONObject raw) {
        Script script = GROOVY_SHELL.parse(exprScript.rawScript());

        List<String> variables = exprScript.variables();
        Binding binding = ExprUtils.generateVariablesBinding(variables, raw);

        script.setBinding(binding);
        return (T) script.run();
    }

    public static Binding generateVariablesBinding(@NotNull List<String> variables,
                                                   @NotNull JSONObject raw) {
        Binding binding = new Binding();
        variables.stream()
                .forEach(variable -> {
                    Object v = JSONPath.eval(raw, variable);
                    binding.setVariable(variable, v);
                });
        return binding;
    }

    public static ExprScript generateExprScript(@NotNull String rawScript) {
        List<String> variables = extractVariables(rawScript);
        ExprScript exprScript = new ExprScript(rawScript);
        exprScript.setVariables(variables);
        return exprScript;
    }

    public static List<ExprWithStructScript> generateExprWithStructScripts(
            @NotNull String name,
            @NotNull Map<String, Object> scriptAndStructMap) {
        List<ExprWithStructScript> exprScripts = scriptAndStructMap.entrySet()
                .stream()
                .map(entry -> {
                    String rawScript = entry.getKey();
                    List<String> variables = extractVariables(rawScript);

                    ExprWithStructScript exprScript = new ExprWithStructScript(name, rawScript, entry.getValue());
                    exprScript.setVariables(variables);
                    return exprScript;
                }).collect(Collectors.toList());
        return exprScripts;
    }

    private static List<String> extractVariables(final String script) {
        boolean inWords = false;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> variables = new ArrayList<>();

        for (int i = 0; i < script.length(); i++) {
            char ch = script.charAt(i);
            if (isValidChar(ch)) {
                if (!inWords) {
                    inWords = true;
                }

                stringBuilder.append(ch);
            } else if (inWords) {
                inWords = false;
                if (stringBuilder.length() > 0) {
                    variables.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }
        }

        if (stringBuilder.length() > 0) {
            variables.add(stringBuilder.toString());
        }

        Iterator<String> iterator = variables.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (NumberUtils.isParsable(word)) {
                iterator.remove();
            }
        }
        return variables;
    }

    private static boolean isValidChar(final char ch) {
        if (CharUtils.isAsciiAlphanumeric(ch)) {
            return true;
        }

        if (ch == '.' || ch == '-' || ch == '_') {
            return true;
        }

        return false;
    }
}
