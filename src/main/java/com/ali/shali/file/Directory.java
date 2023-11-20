package com.ali.shali.file;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shali
 * @Date 2023/9/26 15:54
 * @PackageName:com.ali.shali.file
 * @ClassName: Directory
 * @Description: TODO
 * @Version 1.0
 */
public class Directory implements Component{

    public String directoryName;

    public Map<String, Component> contents;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
        this.contents = new HashMap<>();
    }

    public Directory() {
        this.contents = new HashMap<>();
    }

    @Override
    public boolean isChild() {
        return false;
    }

    @Override
    public Map<String, Component> getChild() {
        return contents;
    }

    @Override
    public String getName() {
        return directoryName;
    }

    public Component getComponent(String path) {
        if ("/".equals(path)) return this;
        path = path.substring(1);
        String[] names = path.split("/");
        Component component = null;
        Map<String, Component> temp = contents;
        for (String s : names) {
            component = temp.get(s);
            if (!component.isChild()) temp = component.getChild();
        }
        return component;
    }
}
