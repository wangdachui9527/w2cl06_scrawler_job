package cn.w2cl.job.dao;

import cn.w2cl.job.pojo.JobInfoField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 12:14
 */
public interface JobInfoRepository extends ElasticsearchRepository<JobInfoField,Long> {
}
