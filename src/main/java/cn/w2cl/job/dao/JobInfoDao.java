package cn.w2cl.job.dao;

import cn.w2cl.job.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Authror 卫骏
 * @Date 2020/1/30 15:50
 */
public interface JobInfoDao extends JpaRepository<JobInfo,Long> {
}
