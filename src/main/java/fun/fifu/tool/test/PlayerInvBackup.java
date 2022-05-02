package fun.fifu.tool.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import fun.fifu.tool.test.controller.MongoController;
import org.bson.Document;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerInvBackup {
    public static Scanner scanner = new Scanner(System.in);
    public static MongoController mongoController = new MongoController();

    public static void main(String[] args) {
        try {
            System.out.print("1.备份  2.恢复  3.查看备份  4.清除所有备份  请选择操作（序号）：");
            switch (scanner.next()) {
                case "1" -> backup();
                case "2" -> rollback();
                case "3" -> viewBackups();
                case "4" -> delBackups();
                default -> System.out.println("非法操作");
            }
        } catch (Throwable ignored) {
        }
    }

    private static void delBackups() {
        TimeInterval timer = DateUtil.timer();
        mongoController.getCollection().find(new Document()).forEach(document -> mongoController.getCollection().deleteOne(document));
        System.out.println("清除完毕，耗时：" + timer.intervalMs() + "毫秒");
    }

    private static void viewBackups() {
    }

    private static void rollback() {
    }

    private static void backup() {
        TimeInterval timer = DateUtil.timer();
        Arrays.stream(FileUtil.file(DataManger.configPojo.getWorld_playerdata_path()).list()).filter(file -> file.endsWith(".dat")).forEach(file -> mongoController.backupWithUUID(file.substring(0, file.lastIndexOf("."))));
        System.out.println("备份成功，耗时：" + timer.intervalMs() + "毫秒");
    }
}
