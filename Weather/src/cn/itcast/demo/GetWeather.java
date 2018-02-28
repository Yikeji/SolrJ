package cn.itcast.demo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetWeather {
	public static void main(String[] args) throws Exception {
		//获得连接扫描路径  路径必须在网页源码中存在
		  Document doc = Jsoup.connect("http://weather.sina.com.cn/")
	                .ignoreContentType(true).//忽略类型
	                ignoreHttpErrors(true).//忽略http错误
	                //超时时间
	                timeout(20000).get();
		 //获取地区
		  	String titleQuery = "div.slider_ct_header";
		  	Elements select = doc.select(titleQuery);
		  	if (select.size() <1 ) {
		  		 throw new RuntimeException("获取地区失败");
			}
		  	
		  	String text = select.select("h4.slider_ct_name").text();
		  	
		  	System.out.println(text);
		  //拿到标签中的内容
	        String cssQuery = "div#blk_fc_c0_scroll div.blk_fc_c0_i";
	        
	        Elements blks = doc.select(cssQuery);
	        if(blks.size() < 10){
	            throw new RuntimeException("获取天气失败");
	        }
	        for(Element blk : blks){
	            String time = blk.select("p.wt_fc_c0_i_day").text();
	            String temp = blk.select("p.wt_fc_c0_i_temp").text();
	            String tip = blk.select("p.wt_fc_c0_i_tip").text();
	            String levell = blk.select("ul.wt_fc_c0_i_level li.l").text();
	            String levelr = blk.select("ul.wt_fc_c0_i_level li.r").text();
	            Elements days = blk.select("p.wt_fc_c0_i_icons img");
	            for(Element day : days){
	                String icons = day.attr("title");
	                System.out.print(icons+"\t");
	            }
	            System.out.println(text+"\t");
	            System.out.print(time+"\t");
	            System.out.print(tip+"\t");
	            System.out.print(levell+"\t");
	            System.out.print(levelr+"\t");
	            System.out.println(temp);
	        }
	    }

}
