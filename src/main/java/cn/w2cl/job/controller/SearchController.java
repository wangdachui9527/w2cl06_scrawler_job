package cn.w2cl.job.controller;

import cn.w2cl.job.pojo.JobResult;
import cn.w2cl.job.service.JobInfoFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 15:45
 */
@RestController
public class SearchController {

    @Autowired
    private JobInfoFieldService jobInfoFieldService;

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public JobResult search(String salary, Integer page, String jobddr, String keyword){
        JobResult jobResult = jobInfoFieldService.search(salary,page,jobddr,keyword);
        return jobResult;
    }
}
