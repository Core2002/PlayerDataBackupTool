package fun.fifu.tool.test;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import fun.fifu.tool.test.controller.MongoController;

import java.util.Arrays;

public class Test {
    @org.junit.jupiter.api.Test
    public void configReadTest() {
        System.out.println(DataManger.uuid2namePojo);
    }

    @org.junit.jupiter.api.Test
    public void configSaveTest() {
        DataManger.configPojo.setUuid2name_path("test_uuid2name.json");
        DataManger.saveData();
    }

    @org.junit.jupiter.api.Test
    public void backupTest() {
        new MongoController().backupWithUUID("00cc0e68-3261-45ac-9b21-ad796a0d98b7");
    }

    @org.junit.jupiter.api.Test
    public void foreachDatTest() {
        Arrays.stream(FileUtil.file(DataManger.configPojo.getWorld_playerdata_path()).list()).filter(file -> file.endsWith(".dat")).forEach(file -> System.out.println(file.substring(0, file.lastIndexOf("."))));
    }
}
