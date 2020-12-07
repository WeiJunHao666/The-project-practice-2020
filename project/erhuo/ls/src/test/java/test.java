import com.erhuo.dao.CommodityMapper;
import com.erhuo.dao.CommodityMapperImpl;
import com.erhuo.pojo.Commodity;
import com.erhuo.service.CommodityService;
import com.erhuo.util.QiniuUpload;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class test {
    @Autowired
    private CommodityService commodityService;
    @Test
    public void test1(){
        String upToken = QiniuUpload.getUpToken();
        System.out.println(upToken);
    }
}
