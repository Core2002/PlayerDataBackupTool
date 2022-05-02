package fun.fifu.tool.player_backup;

import cn.hutool.core.io.FileUtil;
import fun.fifu.tool.player_backup.controller.MongoController;

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
        new MongoController().backupAll();
    }

    @org.junit.jupiter.api.Test
    public void foreachDatTest() {
        Arrays.stream(FileUtil.file(DataManger.configPojo.getWorld_playerdata_path()).list()).filter(file -> file.endsWith(".dat")).forEach(file -> System.out.println(file.substring(0, file.lastIndexOf("."))));
    }

    @org.junit.jupiter.api.Test
    public void getUUIDTest() {
        System.out.println(DataManger.getUUID("NekokeCore"));
    }

    @org.junit.jupiter.api.Test
    public void ambiguousNameTest(){
        System.out.println(DataManger.ambiguousName("neko"));
    }
}
