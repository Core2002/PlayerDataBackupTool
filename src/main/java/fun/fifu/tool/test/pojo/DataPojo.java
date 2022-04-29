package fun.fifu.tool.test.pojo;

import fun.fifu.tool.test.DataManger;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DataPojo {
    String player_name;
    String player_uuid;
    Map<String, String> data = new HashMap<>();

    public String toJson() {
        return DataManger.gson.toJson(this);
    }
}
