package fun.fifu.tool.player_backup;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fun.fifu.tool.player_backup.pojo.ConfigPojo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public static String getUUID(String name) {
        for (Map.Entry<String, String> entry : uuid2namePojo.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Set<String> ambiguousName(String name) {
        Set<String> tmp = new HashSet<>();
        uuid2namePojo.entrySet().forEach(entry -> {
            if (entry.getValue().toLowerCase().contains(name.toLowerCase())) {
                tmp.add(entry.getValue());
            }
        });
        return tmp;
    }

    public static void rollBackData(String uuid, String dataBase64) {
        Base64.decodeToFile(dataBase64, FileUtil.file(configPojo.getWorld_playerdata_path() + uuid + ".dat"));
    }
}
