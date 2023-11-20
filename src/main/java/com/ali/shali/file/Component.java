package com.ali.shali.file;

import java.util.Map;

/**
 * @Author shali
 * @Date 2023/9/26 15:42
 * @PackageName:com.ali.shali.file
 * @ClassName: Component
 * @Description: TODO
 * @Version 1.0
 */
public interface Component {

    boolean isChild();

    Map<String, Component> getChild();

    String getName();
}
