//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.alibaba.cloudapi.client;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpsApiClientWANHUA_API extends ApacheHttpClient{
    public final static String HOST = "api.whchem.com";
    static HttpsApiClientWANHUA_API instance = new HttpsApiClientWANHUA_API();
    public static HttpsApiClientWANHUA_API getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTPS);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }



    public void LMS_TransportCapacity(byte[] body , ApiCallback callback) {
        String path = "/LMS/01739/TransportCapacity";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
        


        sendAsyncRequest(request , callback);
    }

    public ApiResponse LMS_TransportCapacitySyncMode(byte[] body) {
        String path = "/LMS/01739/TransportCapacity";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
        


        return sendSyncRequest(request);
    }
    public void LMS_QueryTransportTask(byte[] body , ApiCallback callback) {
        String path = "/LMS/01736/QueryTransportTask";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
        


        sendAsyncRequest(request , callback);
    }

    public ApiResponse LMS_QueryTransportTaskSyncMode(byte[] body) {
        String path = "/LMS/01736/QueryTransportTask";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);

        return sendSyncRequest(request);
    }

}