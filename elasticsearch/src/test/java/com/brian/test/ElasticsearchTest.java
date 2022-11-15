package com.brian.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;

public class ElasticsearchTest {

    public final static String HOST = "127.0.0.1";
    public final static int PORT = 9300;

    private TransportClient client = null;

    @Before
    public void getConnet() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "swmv-es") // 集群名 默认名:elasticsearch 如果不是默认的，需要设置
                .put("client.transport.sniff", true)  // 自动把集群下的机器添加到列表中
                .build();
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST), PORT));
        System.out.println("连接信息:" + client.toString());
    }

    @After
    public void closeConnect() {
        if (null != client) {
            System.out.println("执行关闭连接操作...");
            client.close();
        }
    }

    @Test
    public void testCreateIndex() {
        CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate("twitter");

        Settings settings = Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 0).build();
        createIndexRequestBuilder.setSettings(settings);

        CreateIndexResponse createIndexResponse = createIndexRequestBuilder.get();

        System.out.println("createIndexResponse.isAcknowledged:" + createIndexResponse.isAcknowledged());
    }

    @Test
    public void testDeleteIndex() {
        IndicesExistsResponse indicesExistsResponse = client.admin().indices().prepareExists("twitter").get();
        if (indicesExistsResponse.isExists()) {
            DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete("twitter").get();

            System.out.println("deleteIndexResponse.isAcknowledged:" + deleteIndexResponse.isAcknowledged());
        }
    }

    @Test
    public void testPutMapping() throws IOException {
        // 1.在创建Index的时候设置Mapping
        // CreateIndexRequestBuilder.addMapping

        // 2.在创建好的Index上添加新的Mapping / 也可以更新Mapping
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject().startObject("properties").startObject("name").field("type",
                "text").endObject().endObject().endObject();
        PutMappingResponse putMappingResponse = client.admin().indices().preparePutMapping("twitter").setType("user").setSource(builder).get();

        System.out.println("putMappingResponse.isAcknowledged:" + putMappingResponse.isAcknowledged());
    }

    @Test
    public void test() throws Exception {
        // Index Alias
//        IndicesAliasesResponse indicesAliasesResponse = client.admin().indices().prepareAliases().addAlias("twitter", "twitterAlias").get();
//        System.out.println("indicesAliasesResponse.isAcknowledged:" + indicesAliasesResponse.isAcknowledged());

        // 增
//        IndexResponse indexResponse = client.prepareIndex("twitter", "user").setId("1").setSource(XContentFactory.jsonBuilder()
//                .startObject().field("name", "zhangsan-1").endObject()).get();
//        System.out.println(indexResponse.toString());

        // 搜索
//        MatchQueryBuilder query = QueryBuilders.matchQuery("name", "zhangsan-3");
        TermQueryBuilder query = QueryBuilders.termQuery("name", "zhangsan");
        SearchResponse searchResponse = client.prepareSearch("twitter")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000))
                .setQuery(query)
                .setSize(100)
                .get();
        QueryBuilders.queryStringQuery()
//        SearchResponse searchResponse = client.prepareSearch("twitter").setTypes("user").setQuery(query).get();
        System.out.println(searchResponse.getHits().getTotalHits());
        client.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(60000)).get()
    }



    @Test
    public void testAdd() throws IOException {
        XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("userName", "张三").field("sendDate", new Date()).field("msg", "你好李四").endObject();

        IndexResponse response = client.prepareIndex("msg", "tweet", "2").setSource(source).get();

        System.out.println("索引名称:" + response.getIndex() + "\n类型:" + response.getType()
                + "\n文档ID:" + response.getId() + "\n当前实例状态:" + response.status());
    }

    @Test
    public void testGet() {
        GetResponse response = client.prepareGet("msg", "tweet", "2").get();
        System.out.println("索引库的数据:" + response.getSourceAsString());
    }

    @Test
    public void testUpdate() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", "王五");
        jsonObject.addProperty("sendDate", "2008-08-08");
        jsonObject.addProperty("msg", "hello zhangsan");

        UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate("msg", "tweet", "1");
        UpdateResponse response = updateRequestBuilder.setDoc(jsonObject.toString(), XContentType.JSON).get();

        System.out.println("updateResponse索引名称:" + response.getIndex() + "\n updateResponse类型:" + response.getType()
                + "\n updateResponse文档ID:" + response.getId() + "\n当前实例updateResponse状态:" + response.status());
    }

    @Test
    public void testDelete() {
        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete("msg", "tweet", "1");
        DeleteResponse deleteResponse = deleteRequestBuilder.get();

        System.out.println("deleteResponse索引名称:" + deleteResponse.getIndex() + "\n deleteResponse类型:" + deleteResponse.getType() + "\n deleteResponse文档ID:" + deleteResponse.getId() + "\n当前实例deleteResponse状态:" + deleteResponse.status());
    }

    @Test
    public void testSearch() {
        SearchResponse response = client.prepareSearch("msg")
                .setTypes("tweet")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .get();

        System.out.println(response.getHits().getTotalHits());
        for (SearchHit searchHit : response.getHits().getHits()) {
            System.out.println(searchHit.getSourceAsString());
        }
    }
}
