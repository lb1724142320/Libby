//
//  Created by  fred on 2016/10/26.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.alibaba.cloudapi.client;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;



import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class DemoWANHUA_API {


    static{
        //HTTP Client init
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("");
        httpParam.setAppSecret("");
        HttpApiClientWANHUA_API.getInstance().init(httpParam);


        //HTTPS Client init
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        httpsParam.setAppKey("");
        httpsParam.setAppSecret("");

        /**
        * HTTPS request use DO_NOT_VERIFY mode only for demo
        * Suggest verify for security
        */
        //httpsParam.setRegistry(getNoVerifyRegistry());

        HttpsApiClientWANHUA_API.getInstance().init(httpsParam);


    }


    public static void LMS_TransportCapacityHttpsTest(){
        HttpsApiClientWANHUA_API.getInstance().LMS_TransportCapacity("default".getBytes(SdkConstant.CLOUDAPI_ENCODING) , new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void LMS_TransportCapacityHttpsSyncTest(){
        ApiResponse response = HttpsApiClientWANHUA_API.getInstance().LMS_TransportCapacitySyncMode("default".getBytes(SdkConstant.CLOUDAPI_ENCODING));
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public static void LMS_QueryTransportTaskHttpsTest(){
        HttpsApiClientWANHUA_API.getInstance().LMS_QueryTransportTask("default".getBytes(SdkConstant.CLOUDAPI_ENCODING) , new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void LMS_QueryTransportTaskHttpsSyncTest(){
        ApiResponse response = HttpsApiClientWANHUA_API.getInstance().LMS_QueryTransportTaskSyncMode("default".getBytes(SdkConstant.CLOUDAPI_ENCODING));
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }

    private static Registry<ConnectionSocketFactory> getNoVerifyRegistry() {
            RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
            try {
                registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE).build();
                registryBuilder.register(
                        "https",
                        new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(
                                KeyStore.getInstance(KeyStore.getDefaultType()), new TrustStrategy() {
                                    @Override
                                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                                        return true;
                                    }
                                }).build(),
                                new HostnameVerifier() {
                                    @Override
                                    public boolean verify(String paramString, SSLSession paramSSLSession) {
                                        return true;
                                    }
                                }));

            } catch (Exception e) {
                throw new RuntimeException("HttpClientUtil init failure !", e);
            }
            return registryBuilder.build();
        }


        private static void trustAllHttpsCertificates() throws Exception {
            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
            javax.net.ssl.TrustManager tm = new miTM();
            trustAllCerts[0] = tm;
            javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                    .getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                    .getSocketFactory());
        }

        static class miTM implements javax.net.ssl.TrustManager,
                javax.net.ssl.X509TrustManager {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public boolean isServerTrusted(
                    java.security.cert.X509Certificate[] certs) {
                return true;
            }

            public boolean isClientTrusted(
                    java.security.cert.X509Certificate[] certs) {
                return true;
            }

            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType)
                    throws java.security.cert.CertificateException {
                return;
            }

            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType)
                    throws java.security.cert.CertificateException {
                return;
            }
        }
}
