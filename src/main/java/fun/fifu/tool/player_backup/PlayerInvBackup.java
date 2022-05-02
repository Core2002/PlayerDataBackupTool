package fun.fifu.tool.player_backup;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import fun.fifu.tool.player_backup.controller.MongoController;
import fun.fifu.tool.player_backup.pojo.DataPojo;
import org.bson.Document;

import java.util.*;

public class PlayerInvBackup {
    public static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    public static MongoController mongoController = new MongoController();

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.print("1.备份  2.恢复  3.查看备份  4.清除所有备份  请选择操作（序号）：");
                switch (scanner.next()) {
                    case "1" -> backup();
                    case "2" -> rollback();
                    case "3" -> viewBackups();
                    case "4" -> delBackups();
                    default -> System.out.println("非法操作");
                }
            }
        } catch (Throwable ignored) {
        }
    }

    private static void delBackups() {
        TimeInterval timer = DateUtil.timer();
        mongoController.collection.find(new Document()).forEach(document -> mongoController.collection.deleteOne(document));
        System.out.println("清除完毕，耗时：" + timer.intervalMs() + "毫秒");
    }

    private static void viewBackups() {
        TimeInterval timer = DateUtil.timer();
        Set<String> temp = new HashSet<>();
        Map<String, Integer> count = new HashMap<>();
        mongoController.collection.find(new Document()).forEach(document -> {
            Map<String, String> dataPojo = DataManger.gson.fromJson(document.toJson(), DataPojo.class).getData();
            dataPojo.forEach((key, value) -> {
                temp.add(key);
                count.put(key, 1 + count.getOrDefault(key, 0));
            });
        });
        temp.forEach(t -> System.out.println(t + " -> " + count.get(t) + "条"));
        System.out.println("查看完毕，耗时：" + timer.intervalMs() + "毫秒");
    }

    private static void rollback() {
        System.out.print("请输入要还原的玩家：");
        String name = scanner.next();
        String uuid = DataManger.getUUID(name);
        if (uuid == null) {
            System.out.println("玩家 " + name + " 不存在");
            var tmp = DataManger.ambiguousName(name);
            if (!tmp.isEmpty() && tmp.size() < 10)
                System.out.println("你要找的是不是：" + tmp);
            return;
        }
        Map<String, String> dataPojo = DataManger.gson.fromJson(mongoController.collection.find(new Document("player_uuid", uuid)).first().toJson(), DataPojo.class).getData();
        System.out.print("请输入要还原的日期：");
        String date = scanner.next();
        if (!dataPojo.containsKey(date)) {
            System.out.println("日期 " + date + " 不存在");
            System.out.println("可能的日期："+dataPojo.keySet());
            return;
        }
        TimeInterval timer = DateUtil.timer();
        DataManger.rollBackData(uuid, dataPojo.get(date));
        System.out.println("恢复成功，耗时：" + timer.intervalMs() + "毫秒");
    }

    private static void backup() {
        TimeInterval timer = DateUtil.timer();
        mongoController.backupAll();
        System.out.println("备份成功，耗时：" + timer.intervalMs() + "毫秒");
    }
}
