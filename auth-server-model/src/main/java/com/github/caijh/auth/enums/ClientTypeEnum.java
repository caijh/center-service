package com.github.caijh.auth.enums;


import com.github.caijh.commons.base.enums.IndexEnum;

public enum ClientTypeEnum implements IndexEnum {
    PUBLIC(0, "公开的"),
    CONFIDENTIAL(1, "私有的");

    private Integer index;
    private String desc;

    ClientTypeEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    public String getDesc() {
        return desc;
    }
}
