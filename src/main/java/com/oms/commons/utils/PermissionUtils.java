package com.oms.commons.utils;

public class PermissionUtils{

    //私有访问权限
    private static final int PRIVATELY_VISIT = 1 << 0;
    //好友访问权限
    private static final int FRIENDLY_VISIT  = 1 << 1;
    //所有人访问权限
    private static final int PUBLICLY_VISIT  = 1 << 2;

    //私有修改权限
    private static final int PRIVATELY_REVISE = 1 << 3;
    //好友修改权限
    private static final int FRIENDLY_REVISE  = 1 << 4;
    //所有人修改权限
    private static final int PUBLICLY_REVISE  = 1 << 5;

    //私有权限
    private static final int PRIVATELY = PRIVATELY_VISIT | PRIVATELY_REVISE;
    //好友权限
    private static final int FRIENDLY  = FRIENDLY_VISIT | FRIENDLY_REVISE;
    //所有人权限
    private static final int PUBLICLY  = PUBLICLY_VISIT | PUBLICLY_REVISE;

    public static int getPrivatelyVisit(){
        return PRIVATELY_VISIT;
    }

    public static int getFriendlyVisit(){
        return FRIENDLY_VISIT;
    }

    public static int getPubliclyVisit(){
        return PUBLICLY_VISIT;
    }

    public static int getPrivatelyRevise(){
        return PRIVATELY_REVISE;
    }

    public static int getFriendlyRevise(){
        return FRIENDLY_REVISE;
    }

    public static int getPubliclyRevise(){
        return PUBLICLY_REVISE;
    }

    public static int getPrivately(){
        return PRIVATELY;
    }

    public static int getFriendly(){
        return FRIENDLY;
    }

    public static int getPublicly(){
        return PUBLICLY;
    }

    public static int setToPrivatelyVisit(){
        return PRIVATELY_VISIT;
    }

    public static int setToFriendlyVisit(){
        return FRIENDLY_VISIT | PRIVATELY_VISIT;
    }

    public static int setToPubliclyVisit(){
        return PUBLICLY_VISIT | FRIENDLY_VISIT | PRIVATELY_VISIT;
    }

    public static int setToPrivatelyRevise(){
        return PRIVATELY_REVISE;
    }

    public static int setToFriendlyRevise(){
        return FRIENDLY_REVISE | PRIVATELY_REVISE;
    }

    public static int setToPubliclyRevise(){
        return PUBLICLY_REVISE | FRIENDLY_REVISE | PRIVATELY_REVISE;
    }

    public static int setToPrivate(){
        return setToPrivatelyVisit() | setToPrivatelyRevise();
    }

    public static int setToFriendly(){
        return setToFriendlyVisit() | setToFriendlyRevise();
    }

    public static int setToPublicly(){
        return setToPubliclyVisit() | setToPubliclyRevise();
    }

    public static boolean allow(int has, int need){
        return (has & need) == has;
    }
}
