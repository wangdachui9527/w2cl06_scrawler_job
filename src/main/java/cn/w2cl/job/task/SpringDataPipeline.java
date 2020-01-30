package cn.w2cl.job.task;

import cn.w2cl.job.pojo.JobInfo;
import cn.w2cl.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Authror 卫骏
 * @Date 2020/1/30 18:49
 */
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取保存到内存的JobInfo
        JobInfo jobInfo = resultItems.get("jobInfo");
        if(jobInfo != null){
            jobInfoService.save(jobInfo);
        }
    }
}
