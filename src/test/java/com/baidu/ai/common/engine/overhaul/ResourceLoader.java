package com.baidu.ai.common.engine.overhaul;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResourceLoader {

    private static final String PKGBASE;

    static {
        PKGBASE = ResourceLoader.class.getResource("/").getPath();
    }

    private final String basePath;

    private ResourceLoader(String basePath) {
        this.basePath = basePath;
    }

    public static ResourceLoader of(@NotNull String basePath) {
        return new ResourceLoader(basePath);
    }

    public JSONObject cfg() throws IOException {
        return loadFile("config.json");
    }

    public JSONObject loadFile(@NotNull String fileName)
            throws IOException {
        String resourcePath = PKGBASE + basePath + '/' + fileName;
        File resourceFile = new File(resourcePath);

        if (!resourceFile.exists()) {
            throw new FileNotFoundException("file not find:" + resourcePath);
        }

        FileInputStream fileInputStream = new FileInputStream(resourceFile);
        String resourceString = new String(IOUtils.toByteArray(fileInputStream), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONObject.parseObject(resourceString);
        return jsonObject;
    }
}
