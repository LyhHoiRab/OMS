package com.oms.commons.utils;

import com.google.common.collect.Lists;
import com.oms.core.product.entity.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ItemExcelUtils{

    public static final String[] TITLES = new String[]{"商品编码", "商品名称", "库存"};

    public static SXSSFWorkbook exportTemplate(){
        //创建文档
        SXSSFWorkbook book = new SXSSFWorkbook();

        //创建工作区
        SXSSFSheet sheet = book.createSheet();

        //写入标题
        SXSSFRow row = sheet.createRow(0);
        for(int i = 0; i < TITLES.length; i++){
            row.createCell(i).setCellValue(TITLES[i]);
        }

        return book;
    }

    public static List<Item> upload(MultipartFile file) throws Exception{
        List<Item> list = Lists.newArrayList();

        //读取文件
        XSSFWorkbook book = new XSSFWorkbook(file.getInputStream());

        //读取工作区
        XSSFSheet sheet = book.getSheetAt(0);

        //开始行
        int firstRow = sheet.getFirstRowNum();
        //结束行
        int lastRow  = sheet.getLastRowNum();

        //读取正文
        for(int currentRow = firstRow + 1; currentRow <= lastRow; currentRow++){
            XSSFRow row = sheet.getRow(currentRow);

            //商品编码
            String code = row.getCell(0).getStringCellValue();

            if(StringUtils.isNotBlank(code)){
                //商品名称
                String  name  = row.getCell(1).getStringCellValue();
                //库存
                Integer stock = new Integer((int) row.getCell(2).getNumericCellValue());

                //库存校验
                if(stock == null || stock < 0){
                    stock = 0;
                }

                if(StringUtils.isNotBlank(code)){
                    Item item = new Item();
                    item.setCode(code);
                    item.setName(name);
                    item.setStock(stock);

                    list.add(item);
                }
            }
        }

        return list;
    }
}
