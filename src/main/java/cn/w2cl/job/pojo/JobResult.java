package cn.w2cl.job.pojo;

import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/2/1 15:28
 */
public class JobResult {

    private List<JobInfoField> rows;
    private Integer pageTotal;

    public List<JobInfoField> getRows() {
        return rows;
    }

    public void setRows(List<JobInfoField> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
