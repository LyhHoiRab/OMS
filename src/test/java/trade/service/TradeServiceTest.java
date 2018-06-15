package trade.service;

import com.oms.OMSApplication;
import com.oms.core.trade.entity.Trade;
import com.oms.core.trade.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = OMSApplication.class)
public class TradeServiceTest{

    @Autowired
    private TradeService tradeService;

    @Test
    public void page(){
        PageRequest pageRequest = new PageRequest(1L, 20L);

        Page<Trade> page = tradeService.page(pageRequest, null, null, null, null, null,
                                        null, null, null, null, null,
                                  null, null, null, null, null, null, null,
                                              null);

        List<Trade> list = page.getContent();
        System.out.println(list.size());
    }
}
