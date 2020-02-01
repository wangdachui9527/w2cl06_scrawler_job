package cn.w2cl.job.service.impl;

import cn.w2cl.job.dao.JobInfoRepository;
import cn.w2cl.job.pojo.JobInfoField;
import cn.w2cl.job.pojo.JobResult;
import cn.w2cl.job.service.JobInfoFieldService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 12:16
 */
@Service
public class JobInfoRepositoryServiceImpl implements JobInfoFieldService {

    @Autowired
    private JobInfoRepository jobInfoRepository;

    @Override
    public void save(JobInfoField jobInfoField) {
        this.jobInfoRepository.save(jobInfoField);
    }

    @Override
    public void saveAll(List<JobInfoField> list) {
        this.jobInfoRepository.saveAll(list);
    }

    @Override
    public JobResult search(String salary, Integer page, String jobaddr, String keyword) {
        //定义薪水最小值，薪水最大值
        int salaryMin = 0,salaryMax = 0;
        //解析薪水 ，如果是"*"代表的是默认最大值或最小值
        String[] salarys = salary.split("-");
        if("*".equals(salarys[0])){
            salaryMin = 0; //最小值
        }else{
            //拿到的最小值薪水转成int类型并乘以10000
            salaryMin = Integer.parseInt(salarys[0]) * 10000;
        }

        if("*".equals(salarys[1])){
            salaryMax = 10000000;  //最大值 1000万
        }else{
            //拿到的最大值薪水转成int类型乘以10000
            salaryMax = Integer.parseInt(salarys[1]) * 10000;
        }

        //判断jobaddr和keyword是否为空，如果为空，那么让他们等于* 意思是查所有
        if(StringUtils.isBlank(jobaddr)){
            jobaddr = "*";
        }
        if(StringUtils.isBlank(keyword)){
            keyword = "*";
        }

        //调用repository进行复杂查询，注意方法名的格式
        //两个Between分别是两对数值第一个SalaryMinBetween对应一对 salaryMin,salaryMax
            //第二个SalaryMaxBetween对应一对salaryMin,salaryMax
            //后面的每个And对应一个交集条件
        Page<JobInfoField> pages = this.jobInfoRepository.findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(salaryMin,salaryMax,
                salaryMin,salaryMax,jobaddr,keyword,keyword,PageRequest.of(page - 1,30));

        //封装结果对象
        JobResult jobResult = new JobResult();
        jobResult.setRows(pages.getContent());
        jobResult.setPageTotal(pages.getTotalPages());

        return jobResult;
    }
}
