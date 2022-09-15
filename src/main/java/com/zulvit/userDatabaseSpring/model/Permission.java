package com.zulvit.userDatabaseSpring.model;

public enum Permission {
    ADMIN_WRITE_LIST("admin:write"),
    ADMIN_WATCH_LIST("admin:read"),
    SELLER_ORDER_READ("seller:read"),
    SELLER_ORDER_WRITE("seller:write"),
    /*SK - STORE KEEPER*/
    SK_READ("store_keeper:read"),
    SK_WRITE("store_keeper:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
