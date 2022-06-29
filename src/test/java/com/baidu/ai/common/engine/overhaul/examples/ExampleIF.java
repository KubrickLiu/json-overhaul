package com.baidu.ai.common.engine.overhaul.examples;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.Overhauler;
import com.baidu.ai.common.engine.overhaul.ResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ExampleIF extends AbstractExample {

    @Before
    public void init() throws Exception {
        this.loader = ResourceLoader.of("exampleIF");
        this.overhauler = Overhauler.build(loader.cfg());
    }

    @Test
    public void testRaw0() throws IOException {
        JSONObject raw = loader.loadFile("raw0.json");
        JSONObject ret = overhauler.parse(raw);
        System.out.println(ret);
    }
}
