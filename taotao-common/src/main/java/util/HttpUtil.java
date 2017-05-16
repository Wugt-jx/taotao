package util;

import constant.HttpConstant;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class HttpUtil {

    private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * get请求
     * @param url 请求的url
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String doGet(String url) throws IOException, HttpException {
        if (url==null){throw new NullPointerException("url is null!");}
        CloseableHttpClient client = httpClientBuilder.build();
        HttpGet get = new HttpGet(url);
        logger.info("request url:{}",url);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (response==null){throw new HttpException("response is null ! ");}
            if (response.getStatusLine().getStatusCode()!= HttpConstant.STATUS_CODE){
                throw new HttpException(EntityUtils.toString(response.getEntity()));
            }
            logger.info("request successfully!");
            String result = EntityUtils.toString(response.getEntity(),HttpConstant.CHAR_SET );
            response.close();
            return result;
        }catch (IOException e){
            throw e;
        } catch (HttpException e) {
            throw e;
        } finally {
            client.close();
        }
    }

    /**
     * 带参数的get请求
     * @param url   请求地址
     * @param params    参数
     * @return
     */
    public String doGet(String url,Map<String,Object>params) throws IOException, HttpException, URISyntaxException {
        if (url==null){throw new NullPointerException("url is null!");}
        logger.info("request url{}",url);
        if (params!=null&&params.size()>0){
            URIBuilder uriBuilder = null;
            CloseableHttpClient client = httpClientBuilder.build();
            try {
                uriBuilder = new URIBuilder(url);
                for (String key:params.keySet()){uriBuilder.addParameter(key,params.get(key).toString());}
                HttpGet get = new HttpGet(uriBuilder.build());
                CloseableHttpResponse response = client.execute(get);
                if (response==null){throw new NullPointerException("response is null !");}
                if(response.getStatusLine().getStatusCode()!=HttpConstant.STATUS_CODE){throw new HttpException(EntityUtils.toString(response.getEntity()));}
                String result = EntityUtils.toString(response.getEntity(),HttpConstant.CHAR_SET);
                response.close();
                return result;
            } catch (URISyntaxException e) {
                throw e;
            } catch (ClientProtocolException e) {
                throw e;
            } catch (IOException e) {
                throw e;
            }finally {
                client.close();
            }
        }else{
            return doGet(url);
        }
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public String doPost(String url, Map<String,Object>params) throws HttpException, IOException {
        if (url==null){throw new NullPointerException("url is null!");}

        CloseableHttpClient client =httpClientBuilder.build();
        HttpPost post = new HttpPost(url);
        logger.info("request url{}",url);
        if (params!=null&&params.size()>0) {
            List<NameValuePair> kvList = new ArrayList<>();
            for (String key : params.keySet()) {kvList.add(new BasicNameValuePair(key, params.get(key).toString()));}
            try {
                StringEntity entity = new UrlEncodedFormEntity(kvList, HttpConstant.CHAR_SET);
                post.setEntity(entity);
            } catch (UnsupportedEncodingException e) {throw new RuntimeException(e);}
        }
        try {
            CloseableHttpResponse response = client.execute(post);
            if (response==null){throw new NullPointerException("response is null ! ");}
            if (response.getStatusLine().getStatusCode()!=HttpConstant.STATUS_CODE){
                throw new HttpException(EntityUtils.toString(response.getEntity()));
            }
            logger.info("request successfully! ");
            String result = EntityUtils.toString(response.getEntity(),HttpConstant.CHAR_SET);
            response.close();
            return result;
        } catch (IOException e) {
            throw e;
        }finally {
            client.close();
        }
    }
}
