package cn.w2cl.job.service.impl;

import cn.w2cl.job.dao.JobInfoRepository;
import cn.w2cl.job.pojo.JobInfoField;
import cn.w2cl.job.service.JobInfoFieldService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
