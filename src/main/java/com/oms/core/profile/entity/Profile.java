package com.oms.core.profile.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: profile
 */
@Getter
@Setter
public class Profile{

    private Long   id;
    private String mobile;
    private String realName;
    private String nickname;
    private String lemonName;
    private String identityCard;
    private String birthday;
    private String profilePic;
    private String password;
    private String wxno;
    private Date   dateCreated;
    private Date   lastUpdated;
}
