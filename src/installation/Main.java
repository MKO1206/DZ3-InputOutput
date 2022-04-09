package installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        final String pathDirGames = "C:/Users/User/Games";

        File dirSrc = new File(pathDirGames, "src");
        makeDir(dirSrc);

        File dirRes = new File(pathDirGames, "res");
        makeDir(dirRes);

        File dirSavegames = new File(pathDirGames, "savegames");
        makeDir(dirSavegames);

        File dirTemp = new File(pathDirGames, "temp");
        makeDir(dirTemp);

        File dirMain = new File(dirSrc.getAbsolutePath(), "main");
        makeDir(dirMain);

        File dirTest = new File(dirSrc.getAbsolutePath(), "test");
        makeDir(dirTest);

        File fileMain = new File(dirMain.getAbsolutePath(), "Main.java");
        makeFile(fileMain);

        File fileUtils = new File(dirMain.getAbsolutePath(), "Utils.java");
        makeFile(fileUtils);

        File dirDrawables = new File(dirRes.getAbsolutePath(), "drawables");
        makeDir(dirDrawables);

        File dirVectors = new File(dirRes.getAbsolutePath(), "vectors");
        makeDir(dirVectors);

        File dirIcons = new File(dirRes.getAbsolutePath(), "icons");
        makeDir(dirIcons);

        File fileTemp = new File(dirTemp.getAbsolutePath(), "temp.txt");
        makeFile(fileTemp);

        try (FileWriter writer = new FileWriter(fileTemp, false)) {
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void makeDir(File dirName) {
        if (dirName.mkdir()) {
            sb.append("Директория создана")
                    .append(dirName.getName())
                    .append("адрес расположения")
                    .append(dirName.getParent())
                    .append("\n");
        } else {
            sb.append("Ошибка создания директории")
                    .append(dirName.getName())
                    .append("адрес расположения")
                    .append(dirName.getParent())
                    .append("\n");
        }
    }

    public static void makeFile(File fileName) {
        try {
            if (fileName.createNewFile()) {
                sb.append("Файл создан")
                        .append(fileName.getName())
                        .append("адрес расположения")
                        .append(fileName.getParent())
                        .append("\n");
            } else {
                sb.append("Ошибка создания файла")
                        .append(fileName.getName())
                        .append("адрес расположения")
                        .append(fileName.getParent())
                        .append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}