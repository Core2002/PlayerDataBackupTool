package fun.fifu.tool.test;

import fun.fifu.tool.test.controller.MongoController;

public class Test {
    @org.junit.jupiter.api.Test
    public void configReadTest(){
        System.out.println(DataManger.configPojo);
    }

    @org.junit.jupiter.api.Test
    public void configSaveTest(){
        DataManger.configPojo.setUuid2name_path("test_uuid2name.json");
        DataManger.saveData();
    }

    @org.junit.jupiter.api.Test
    public void backupTest(){
        new MongoController().backupWithUUID("00cc0e68-3261-45ac-9b21-ad796a0d98b7");
    }
}
