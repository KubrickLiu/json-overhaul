package com.common.json.overhaul.operatorhandler.expr;

import java.util.ArrayList;
import java.util.List;

public class ExprScript {

    protected final String rawScript;

    protected List<String> variables = new ArrayList<>();

    public ExprScript(String rawScript) {
        this.rawScript = rawScript;
    }


    public String rawScript() {
        return rawScript;
    }

    public List<String> variables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }
}
