package com.baidu.ai.common.engine.overhaul.examples;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.common.engine.overhaul.Overhauler;
import com.baidu.ai.common.engine.overhaul.ResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Example1 extends AbstractExample {

    @Before
    public void init() throws Exception {
        this.loader = ResourceLoader.of("example1");
        this.overhauler = Overhauler.build(loader.cfg());
    }

    @Test
    public void test() {
        String config = "{\"new_word@word\":\"\"}";
        String raw = "{\"word\":\"hello world\"}";

        Overhauler overhauler = Overhauler.build(config);
        JSONObject ret = overhauler.parse(raw);
        System.out.println(ret);
    }

    @Test
    public void testRaw0() throws IOException {
        JSONObject raw = loader.loadFile("raw0.json");
        JSONObject ret = overhauler.parse(raw);
        System.out.println(ret);
    }

    @Test
    public void testRaw1() throws IOException {
        JSONObject raw = loader.loadFile("raw1.json");
        JSONObject ret = overhauler.parse(raw);
        System.out.println(ret);
    }
}
