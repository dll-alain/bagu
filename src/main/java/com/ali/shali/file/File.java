package com.ali.shali.file;

import java.util.Map;

/**
 * @Author shali
 * @Date 2023/9/26 15:43
 * @PackageName:com.ali.shali.file
 * @ClassName: File
 * @Description: TODO
 * @Version 1.0
 */
public class File implements Component{

    String fileName;
    String content;

    public File(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    @Override
    public boolean isChild() {
        return true;
    }

    @Override
    public Map<String, Component> getChild() {
        return null;
    }

    @Override
    public String getName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
