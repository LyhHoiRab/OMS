package statistics.dao;

import com.oms.OMSApplication;
import com.oms.core.statistics.dao.PayTypeStatisticsDao;
import com.oms.core.statistics.entity.PayTypeStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = OMSApplication.class)
public class PayTypeStatisticsDaoTest{

    @Autowired
    private PayTypeStatisticsDao payTypeStatisticsDao;

    @Test
    public void findGroupBySeller(){
        List<PayTypeStatistics> list = payTypeStatisticsDao.findGroupBySeller(null, null, null, null);
        System.out.println(list);
    }
}
