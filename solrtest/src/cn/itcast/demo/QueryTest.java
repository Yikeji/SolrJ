package cn.itcast.demo;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;

public class QueryTest {
	/**
	 * 抽取httpSolrServer的创建代码
	 * @throws Exception
	 */
	private HttpSolrServer httpSolrServer;
	@Before
	public void init(){
		String baseUrl = "http://localhost:8187/solr";
		httpSolrServer = new HttpSolrServer(baseUrl);
	}
	@Test
	public void queryIndex() throws Exception{
		//首先创建搜索对象
		SolrQuery query = new SolrQuery();
		//设置搜索的条件
		query.setQuery("*:*");
		//发起搜索的请求
		QueryResponse response = httpSolrServer.query(query);
		//处理搜索的结果
		SolrDocumentList results = response.getResults();
		System.out.println("搜索到的结果的总数："+results.getNumFound());
		
		for (SolrDocument solrDocument : results) {
			System.out.println("-------------");
			System.out.println("id"+"--"+solrDocument.get("id"));
			System.out.println("name"+"--"+solrDocument.get("name"));
			System.out.println("birthday"+"--"+solrDocument.get("birthday"));
		}
	}
}
