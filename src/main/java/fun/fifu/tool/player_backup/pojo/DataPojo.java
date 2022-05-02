package fun.fifu.tool.player_backup.pojo;

import fun.fifu.tool.player_backup.DataManger;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DataPojo {
    String player_uuid;
    Map<String, String> data = new HashMap<>();

    public String toJson() {
        return DataManger.gson.toJson(this);
    }
}
