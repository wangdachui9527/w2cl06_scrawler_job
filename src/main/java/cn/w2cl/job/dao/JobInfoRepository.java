package cn.w2cl.job.dao;

import cn.w2cl.job.pojo.JobInfoField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 12:14
 */
public interface JobInfoRepository extends ElasticsearchRepository<JobInfoField,Long> {
    Page<JobInfoField> findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(int salaryMin, int salaryMax, int salaryMin1, int salaryMax1, String jobaddr, String keyword, String keyword1, Pageable pageable);
}
