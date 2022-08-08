package com.practice.day19.annotation;

import java.util.List;

public class AnnotationDemo {

    @SuppressWarnings({"unused", "rawtypes"})
    public void save() {
        List list = null;
    }

    // 标记方法已经过时
    @Deprecated
    private void save1() {
    }
}
