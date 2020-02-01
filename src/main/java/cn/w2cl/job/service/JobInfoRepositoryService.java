package cn.w2cl.job.service;

import cn.w2cl.job.pojo.JobInfoField;

import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 12:15
 */
public interface JobInfoRepositoryService {

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
}
