package com.setu.hsapiassistance.service.api.http;

import com.setu.hsapiassistance.model.HSUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @date May 4, 2017
 * @author setu
 */
public class RestTemplate {

    private final ObjectMapper objectMapper;
    private final long MINIMUM_DELAY_TIME = 2000;
    private final long MAXIMUM_DELAY_TIME = 10000;
    private final int STATUS_CODE_API_LIMIT_EXCEEDED = 429;

    public RestTemplate() {
        objectMapper = new ObjectMapper();
    }

    public <T extends Object> T getForObject(String url, Class<T> responseType) throws RestException, APILimitExceededException {
//        System.out.println("Url to connect: " + url);
        try {
            String responseString = getResponseString(url);
            T responseObject = null;

            if (responseString != null) {
                responseObject = getObjectMapper().readValue(responseString, responseType);
            }

            return responseObject;
        } catch (IOException ex) {
            if (HSUtil.DEBUG) {
                System.err.println("RestException caught: " + ex.getMessage() + ", url: " + url);
            }
            throw new RestException(ex);
        }
    }

    private String getResponseString(String url) throws APILimitExceededException {
        return getResponseString(url, MINIMUM_DELAY_TIME);
    }

    private String getResponseString(String url, long retryIn) throws APILimitExceededException {
        String responseString = null;

        try {
            HttpResponse response = Request.Get(url)
                    .connectTimeout(60000)
                    .socketTimeout(60000)
                    .execute().returnResponse();
            int statusCode = getStatusCode(response);

            if (statusCode == STATUS_CODE_API_LIMIT_EXCEEDED) {
                if (HSUtil.DEBUG) {
                    System.err.println("APILimitExceededException caught, url: " + url + ", statusCode: " + statusCode);
                }
                throw new APILimitExceededException();
            }

            responseString = getContent(response);
        } catch (IOException ex) {
//            System.out.println("Exception caught: " + ex.getMessage());
            try {
                Thread.sleep(retryIn);
            } catch (InterruptedException ex1) {
                System.err.println("Thread sleep interrupted");
            }

            if (retryIn * 2 <= MAXIMUM_DELAY_TIME) {
                return getResponseString(url, retryIn * 2);
            } else {
                System.out.println("Giving up " + url);
                return responseString;
            }
        }

        return responseString;
    }

    private int getStatusCode(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    private String getContent(HttpResponse response) throws IOException {
        String content;
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            String line;
            StringBuilder sBuilder = new StringBuilder();
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line);
            }
            content = sBuilder.toString();
        }

        if (content == null || content.length() == 0) {
            return null;
        } else {
            return content;
        }
    }

    /**
     * @return the objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
