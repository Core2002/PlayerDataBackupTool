package fun.fifu.tool.test;

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
}
