package com.lm.count.model;

/**
 * 配置模型类
 *
 * @author limin
 * @since 2021-12-28
 */
public class ConfigModel {
    private String checkType;
    private String checkPath;

    public ConfigModel(String checkType, String checkPath) {
        this.checkType = checkType;
        this.checkPath = checkPath;
    }

    public String getCheckType() {
        return checkType;
    }

    public String getCheckPath() {
        return checkPath;
    }

    public void setCheckPath(String checkPath) {
        this.checkPath = checkPath;
    }
}
