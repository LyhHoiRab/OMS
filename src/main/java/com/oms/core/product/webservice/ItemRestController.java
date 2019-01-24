package com.oms.core.product.webservice;

import com.oms.commons.consts.ProductType;
import com.oms.commons.security.annotation.APIDoc;
import com.oms.core.product.entity.Item;
import com.oms.core.product.service.ItemService;
import com.wah.doraemon.security.response.Responsed;
import com.wah.doraemon.utils.Page;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/api/1.0/item")
public class ItemRestController{

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "分页查询商品信息")
    public Responsed<Page<Item>> page(Long pageNum, Long pageSize, String code, String name, String productName, ProductType type){
        Page<Item> page = itemService.page(pageNum, pageSize, code, name, productName, type);

        return new Responsed<Page<Item>>("查询成功", page);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @APIDoc(description = "商品信息导入")
    public Responsed upload(MultipartFile file) throws Exception{
        itemService.upload(file);

        return new Responsed("导入成功");
    }

    @RequestMapping(value = "/export/template", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @APIDoc(description = "商品模版导出")
    public void exportTemplate(HttpServletResponse response) throws Exception{
        SXSSFWorkbook book = itemService.exportTemplate();

        String filename = URLEncoder.encode("商品模版.xlsx", "UTF-8");

        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        response.setContentType("application/octet-stream");

        OutputStream output = response.getOutputStream();
        book.write(output);

        output.close();
    }
}
