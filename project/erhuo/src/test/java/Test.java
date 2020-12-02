import com.erhuo.controller.CommentController;
import com.erhuo.dao.CommentMapperImpl;
import com.erhuo.dao.UserMapper;
import com.erhuo.pojo.Comment;
import com.erhuo.pojo.CommentLite;
import com.erhuo.service.CommentService;
import com.erhuo.util.CommentUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class Test {

    @org.junit.Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        CommentMapperImpl commentMapper = (CommentMapperImpl) context.getBean("commentDao");
        List<Comment> firstComment = commentMapper.queryLastIdNull(1);
        List<Comment> comments = commentMapper.queryLastIdNotNull(1);

        List<Comment> commentList = CommentUtil.addAllNode(firstComment, comments);
        List<CommentLite> comList = new ArrayList<CommentLite>();
        CommentUtil.getLite(commentList,comList);
    }
}
