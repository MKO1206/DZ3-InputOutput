package conservation;

import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        final String pathDirSavegames = "C:/Users/User/Games/savegames";

        GameProgress saveGame1 = new GameProgress(75, 2, 1, 50);
        GameProgress saveGame2 = new GameProgress(60, 1, 2, 100);
        GameProgress saveGame3 = new GameProgress(95, 4, 3, 200);

        File save1 = new File(pathDirSavegames, "save1.dat");
        makeFile(save1);
        File save2 = new File(pathDirSavegames, "save2.dat");
        makeFile(save2);
        File save3 = new File(pathDirSavegames, "save3.dat");
        makeFile(save3);
        File saveZip = new File(pathDirSavegames, "save.zip");
        makeFile(saveZip);

        saveGame(save1.getAbsolutePath(), saveGame1);
        saveGame(save2.getAbsolutePath(), saveGame2);
        saveGame(save3.getAbsolutePath(), saveGame3);

        ArrayList<String> saveGameList = new ArrayList<>();
        saveGameList.add(save1.getAbsolutePath());
        saveGameList.add(save2.getAbsolutePath());
        saveGameList.add(save3.getAbsolutePath());

        if (zipFiles(saveZip.getAbsolutePath(), saveGameList)) {
            delFile(save1);
            delFile(save2);
            delFile(save3);
        }
    }

    public static void saveGame(String path, GameProgress savegame) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(savegame);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean zipFiles(String path, ArrayList<String> fileList) {
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
            for (String fileZip : fileList) {
                FileInputStream fis = new FileInputStream(fileZip);
                ZipEntry entry = new ZipEntry(fileZip);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
            zout.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void makeFile(File fileName) {
        try {
            if (fileName.createNewFile()) {
                System.out.println("Файл создан"
                        + fileName.getName()
                        + "адрес расположения"
                        + fileName.getParent()
                        + "\n");
            } else {
                System.out.println("Ошибка создания файла"
                        + fileName.getName()
                        + "адрес расположения"
                        + fileName.getParent()
                        + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delFile (File filename) {
        if (filename.delete()) {
            System.out.println("Файл" + filename.getName() + "удален.");
        }
    }
}