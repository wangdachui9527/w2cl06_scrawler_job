package cn.w2cl.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @Authror 卫骏
 * @Date 2020/1/31 12:51
 */
@Component
public class ProxyTask implements PageProcessor {

    @Scheduled(fixedDelay = 1000)
    public void Process(){
        //创建下载器DownLoder
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("117.88.4.7",3000)));

        //给下载器设置代理服务器信息

        Spider.create(new ProxyTask())
                .addUrl("http://myip.fireflysoft.net/")
                .setDownloader(httpClientDownloader) //设置下载器
                .run();
    }

    @Override
    public void process(Page page){
        System.out.println(page.getHtml().toString());
    }

    private Site site = Site.me();

    @Override
    public Site getSite() {
        return this.site;
    }
}
