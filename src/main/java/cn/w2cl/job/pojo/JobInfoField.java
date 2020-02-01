package cn.w2cl.job.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Authror 卫骏
 * @Date 2020/1/30 15:27
 */
@Document(indexName = "jobinfo",type = "JobInfoField")
public class JobInfoField {

    @Id
    @Field(index = true,store = true,type = FieldType.Long)
    private Long id;
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String companyName; //公司名称
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String companyAddr; //公司联系方式
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String companyInfo; //公司信息
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String jobName; //职位名称
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String jobAddr; //工作地点
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String jobInfo; //职位信息
    @Field(index = true,store = true,type = FieldType.Integer)
    private Integer salaryMin; //薪资范围，最小
    @Field(index = true,store = true,type = FieldType.Integer)
    private Integer salaryMax; //薪资范围，最大
    @Field(index = true,store = true,type = FieldType.Text)
    private String url; //招聘信息详细页
    @Field(index = true,store = true,type = FieldType.Text)
    private String time; //职位最新发布时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyInfo='" + companyInfo + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobAddr='" + jobAddr + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
