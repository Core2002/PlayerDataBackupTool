package fun.fifu.tool.player_inv_backup;

import java.util.Scanner;

public class PlayerInvBackup {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("1.备份  2.恢复  3.查看备份  4.删除备份  请选择操作（序号）：");
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
    }

    private static void viewBackups() {
    }

    private static void rollback() {
    }

    private static void backup() {
    }
}
