package com.oms.core.product.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * table: miku_mine_sc_product
 */
@Getter
@Setter
public class Product{

    private Long    id;
    //定制类型
    private Byte    mineType;
    //产品名
    private String  prodName;
    //产品规格产品规格
    private String  prodSpec;
    //产品包装
    private String  prodPack;
    //产品可以使用的问题
    private String  prodAimProblemIds;
    //成本价
    private Long    prodCostPrice;
    //建议零售价
    private Long    prodRetailPrice;
    //产品备注
    private String  prodNote;
    //商品图片链接
    private String  prodPicUrls;
    //产品状态
    private Byte    prodShowStatus;
    //产品功效
    private String  prodResult;
    //对应的多媒体教学资源
    private Long    multimediaResId;
    //版本号
    private Long    version;
    //创建时间
    private Date    dateCreated;
    //修改时间
    private Date    lastUpdated;
    //缩略图
    private String  thnmbnail;
    //使用步骤
    private byte    userStep;
    //肤感
    private String  skinFeel;
    //类型
    private byte    scpType;
    //编号
    private String  scpNum;
    //最低价
    private Long    prodlowCostPrice;
    //商品剩余数量
    private Integer quantity;
    //商品已售数量
    private Integer soldQuantity;
    //编码
    private String  prodCode;
    //是否显示
    private Byte    status;
    //时间值
    private Integer prodTime;
}
