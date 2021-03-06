package cn.w2cl.job.service;

import cn.w2cl.job.pojo.JobInfoField;
import cn.w2cl.job.pojo.JobResult;

import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 12:15
 */
public interface JobInfoFieldService {

    /**
     * 保存一条
     * @param jobInfoField
     */
    void save(JobInfoField jobInfoField);

    /**
     * 批量保存
     * @param list
     */
    void saveAll(List<JobInfoField> list);

    JobResult search(String salary, Integer page, String jobaddr, String keyword);
}
