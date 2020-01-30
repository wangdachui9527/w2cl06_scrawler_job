package cn.w2cl.job.task;

import cn.w2cl.job.pojo.JobInfo;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authror 卫骏
 * @Date 2020/1/30 16:31
 */
@Component
public class JobProcessor implements PageProcessor {

    @Autowired
    private SpringDataPipeline springDataPipeline;

    private String url = "https://search.51job.com/list/000000,000000,0000,32%252C01,9,99,Java%25E5%25BC%2580%25E5%258F%2591,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    @Override
    public void process(Page page) {

        //解析页面，获取招聘信息详情的url地址
        List<Selectable> selectables = page.getHtml().css("div#resultList div.el").nodes();
        //判断获取到的集合是否为空，
        if(selectables.size() == 0) {
            //如果为空表示这是招聘详情页
            this.saveJobInfo(page);
        }else{
            //如果不为空表示是列表页
            for (Selectable selectable : selectables) {
                String url = selectable.css("p.t1 span").links().toString();
                page.addTargetRequest(url);
            }
            //下一页
            String nextUrl = page.getHtml().css("div.dw_page div.p_box div.p_wp div.p_in ul li.bk").nodes().get(1).links().toString();
            page.addTargetRequest(nextUrl);
        }


    }

    /**
     * 解析页面并保存招聘信息
     * @param page
     */
    private void saveJobInfo(Page page) {
        JobInfo jobInfo = new JobInfo();

        Html html = page.getHtml();
        //获取发布时间
        String timeStr = html.css("div.tHjob div.in div.cn p.ltype","title").toString();
        String[] split = timeStr.split("\\|");
        for (String s : split) {
            if(s.contains("发布")){
                String substring = s.substring(0, s.length() - 2);
                if(substring.contains("发布")){
                    substring = substring.substring(0,substring.length() - 2);
                }
                jobInfo.setTime(substring);
            }
        }
        //获取招聘详情url
        jobInfo.setUrl(page.getUrl().toString());
        //获取公司地址
        String text = html.css("div.bmsg").nodes().get(1).css("p.fp", "text").toString();
        jobInfo.setCompanyAddr(text);
        //获取公司详情
        jobInfo.setCompanyInfo(html.css("div.tmsg","text").toString());
        //获取公司地址
        jobInfo.setCompanyName(html.css("p.cname a","title").toString());
        //获取工作地址
        jobInfo.setJobAddr(text);
        //获取工作详情
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        //获取工作名称
        jobInfo.setJobName(html.css("div.tHjob div.in div.cn h1","title").toString());
        //获取薪资范围
        String salaryStr = html.css("div.tHjob div.in div.cn strong", "text").toString();
        Integer[] salary = MathSalary.getSalary(salaryStr);
        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);
        //把结果保存到内存
        page.putField("jobInfo",jobInfo);
    }

    private Site site = Site.me()
            .setCharset("gbk") //字符集编码
            .setTimeOut(10000) //10秒超时
            .setRetrySleepTime(3000) //三秒重试
            .setSleepTime(3); //重试3次
    @Override
    public Site getSite() {
        return this.site;
    }

    //initialDelay 初始化启动后等待1秒再执行
    //fixedDelay 上一次执行后多久再执行
    @Scheduled(initialDelay = 1000,fixedDelay = 200*1000)
    public void process(){
        List<Pipeline> pipelines = new ArrayList<>();
        pipelines.add(springDataPipeline);
        Spider.create(new JobProcessor())
                .addUrl(url) //爬取的主页地址
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000))) // 10万条数据进行去重
                .setPipelines(pipelines)
                .run(); //启动爬虫
    }
}
