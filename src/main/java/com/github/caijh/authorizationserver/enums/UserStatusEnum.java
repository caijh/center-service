package com.github.caijh.authorizationserver.enums;

import com.github.caijh.commons.base.IndexEnum;

public enum UserStatusEnum implements IndexEnum {
    NORMAL(0, "正常"), LOCKED(1, "账号被锁定");

    private int index;
    private String desc;

    UserStatusEnum(int index, String desc) {
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
