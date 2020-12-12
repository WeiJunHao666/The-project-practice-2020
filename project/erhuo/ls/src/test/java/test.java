import com.erhuo.dao.CommodityMapper;
import com.erhuo.dao.CommodityMapperImpl;
import com.erhuo.pojo.Commodity;
import com.erhuo.service.CommodityService;
import com.erhuo.service.TypeService;
import com.erhuo.util.QiniuUpload;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;

public class test {

    @Test
    public void test1(){
        String upToken = QiniuUpload.getUpToken();
        System.out.println(upToken);
    }

    @Test
    public void test2(){
        String fileName = "C:\\Users\\zhuojiu\\Desktop\\商品分类\\男装\\xiku.jpg";
        QiniuUpload.uploadPic(fileName, null);
    }
}
