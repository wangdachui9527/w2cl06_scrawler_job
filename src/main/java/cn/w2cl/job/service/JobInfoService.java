package cn.w2cl.job.service;

import cn.w2cl.job.pojo.JobInfo;

import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/1/30 15:52
 */
public interface JobInfoService {

    /**
     * 保存招聘信息
     * @param jobInfo
     */
    void save(JobInfo jobInfo);

    /**
     * 根据条件查询招聘信息集合
     * @param jobInfo
     * @return
     */
    List<JobInfo> findByJobInfo(JobInfo jobInfo);
}
