package fun.fifu.tool.player_backup;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fun.fifu.tool.player_backup.pojo.ConfigPojo;

import java.util.Map;

public class DataManger {
    public static ConfigPojo configPojo;
    public static Map<String, String> uuid2namePojo;
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        configPojo = readData();
        uuid2namePojo = readUUID2Name();
    }

    private static Map<String, String> readUUID2Name() {
        return gson.fromJson(ResourceUtil.readUtf8Str(configPojo.getUuid2name_path()), Map.class);
    }

    public static ConfigPojo readData() {
        return gson.fromJson(ResourceUtil.readUtf8Str("config.json"), ConfigPojo.class);
    }

    public static void saveData() {
        FileUtil.writeUtf8String(gson.toJson(configPojo), "config.json");
    }

    public static String getName(String uuid) {
        return uuid2namePojo.get(uuid);
    }
}
