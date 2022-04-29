package fun.fifu.tool.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fun.fifu.tool.test.pojo.ConfigPojo;

public class DataManger {
    public static ConfigPojo configPojo;
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        configPojo = readData();
    }

    public static ConfigPojo readData() {
        return gson.fromJson(ResourceUtil.readUtf8Str("config.json"), ConfigPojo.class);
    }

    public static void saveData() {
        FileUtil.writeUtf8String(gson.toJson(configPojo), "config.json");
    }
}
