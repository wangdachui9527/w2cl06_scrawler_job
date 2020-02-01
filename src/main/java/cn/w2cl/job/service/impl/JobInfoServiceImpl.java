package cn.w2cl.job.service.impl;

import cn.w2cl.job.dao.JobInfoDao;
import cn.w2cl.job.pojo.JobInfo;
import cn.w2cl.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/1/30 15:54
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //根据jobInfo的url和时间来判断当前实体再数据库是否存在
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        List<JobInfo> byJobInfo = this.findByJobInfo(param);
        if(byJobInfo.size() == 0){
            //如果查到的结果为空，那么就需要保存数据
            this.jobInfoDao.saveAndFlush(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findByJobInfo(JobInfo jobInfo) {
        Example<JobInfo> example = Example.of(jobInfo);
        List<JobInfo> jobInfoList = this.jobInfoDao.findAll(example);
        return jobInfoList;
    }

    @Override
    public Page<JobInfo> findByPage(int pageNo, int pageSize) {
        Page<JobInfo> all = this.jobInfoDao.findAll(PageRequest.of(pageNo-1, pageSize));
        return all;
    }
}
