package com.ali.shali.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author shali
 * @Date 2023/9/26 16:07
 * @PackageName:com.ali.shali.file
 * @ClassName: FileSystem
 * @Description: TODO
 * @Version 1.0
 */
public class FileSystem {
    Directory root;

    public FileSystem() {
        root = new Directory();
    }

    List<String> ls(String path) {
        Component contents = root.getComponent(path);
        List<String> list = new ArrayList<>();
        if (contents == null) return list;
        if (contents.isChild()) {
            list.add(contents.getName());
            return list;
        } else {
            contents.getChild().forEach((key, value) -> list.add(key));
        }
        Collections.sort(list);
        return list;
    }

    void mkdir(String path) {
        path = path.substring(1);
        String[] names = path.split("/");
        Component component = root;
        int i = 0;
        // 先递归遍历已经创建的部分路径
        while (i < names.length && component.getChild().containsKey(names[i])) {
            component = component.getChild().get(names[i]);
            ++i;
        }
        //创建未创建的路径
        while (i < names.length) {
            String dicName = names[i];
            Directory directory = new Directory(dicName);
            component.getChild().put(dicName, directory);
            component = directory;
            ++i;
        }
    }

    /**
     * 先创建文件所在的文件夹，再调用Diretory的getComponent方法获取到文件所在的文件夹，最后将文件添加到文件夹
     * @param filePath
     * @param content
     */
    void addContentToFile(String filePath, String content) {
        filePath = filePath.substring(1);
        String[] names = filePath.split("/");
        String fileName = names[names.length - 1];
        String directoryPath = "/";
        if (names.length > 1) {
            String tail = filePath.substring(0, filePath.length() - fileName.length() - 1);
            directoryPath += tail;
            mkdir(directoryPath);
        }
        // TODO 待优化，mkdir之后getComponent又一次遍历了文件路径
        Map<String, Component> child = root.getComponent(directoryPath).getChild();
        if (child.containsKey(fileName)) {
            File file = (File) child.get(fileName);
            file.setContent(file.getContent() + content);
            return;
        }
        File file = new File(fileName, content);
        child.put(fileName, file);
    }

    String readContentFromFile(String filePath) {
        filePath = filePath.substring(1);
        String[] names = filePath.split("/");
        String fileName = names[names.length - 1];
        String tail = names.length == 1 ? "" : filePath.substring(0, filePath.length() - fileName.length() - 1);
        String path = "/" + tail;
        File file = (File) root.getComponent(path).getChild().get(fileName);
        return file.getContent();
    }
}
