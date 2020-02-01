package cn.w2cl.job.test;

import cn.w2cl.job.pojo.JobInfo;
import cn.w2cl.job.pojo.JobInfoField;
import cn.w2cl.job.service.JobInfoFieldService;
import cn.w2cl.job.service.JobInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 11:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private JobInfoFieldService jobInfoFieldService;

    @Autowired
    private JobInfoService jobInfoService;

    @Test
    public void test01(){
        System.out.println("123");
    }

    /**
     * 创建索引和映射
     */
    @Test
    public void createIndex(){
        this.elasticsearchTemplate.createIndex(JobInfoField.class);
        this.elasticsearchTemplate.putMapping(JobInfoField.class);
    }

    /**
     * 把数据库中的数据复制到索引库中
     */
    @Test
    public void copyToIndexBase(){
        //定义页码
        int pageNo = 1;
        //定义页容量
        int pageSize = 0;
        //查询数据库数据
        do {
            Page<JobInfo> page = jobInfoService.findByPage(pageNo, 500);
            //将查询到的数据封装到JobInfoField中
            List<JobInfoField> list = new ArrayList<>();
            for (JobInfo jobInfo : page.getContent()) {
                //定义JobInfoField
                JobInfoField jobInfoField = new JobInfoField();
                //将jobInfo的属性复制到jobInfoFiled变量中
                BeanUtils.copyProperties(jobInfo, jobInfoField);
                //放入到集合中
                list.add(jobInfoField);
            }
            //批量存储到索引库
            this.jobInfoFieldService.saveAll(list);
            //获取分页查询出的数据数量
            pageSize = page.getContent().size();
            //页码数+1
            pageNo++;
        }while(pageSize == 500); //如果查询到的数据量不等于500，说明已经是最后一页了，可以停止循环了
    }
}
