import com.erhuo.dao.CommentMapperImpl;
import com.erhuo.pojo.Comment;
import com.erhuo.service.CommentService;
import com.erhuo.util.CommentUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {

    @org.junit.Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        CommentMapperImpl commentMapper = (CommentMapperImpl) context.getBean("commentDao");
        List<Comment> firstComment = commentMapper.queryLastIdNull(1);
        List<Comment> comments = commentMapper.queryLastIdNotNull(1);

        List<Comment> commentList = CommentUtil.addAllNode(firstComment, comments);
        System.out.println(commentList.size());
        CommentUtil.show(commentList);
    }
}
