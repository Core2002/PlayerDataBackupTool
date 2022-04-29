package fun.fifu.tool.test.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DataPojo {
    String player_name;
    String player_uuid;
    Map<String, String> data = new HashMap<>();
}
