package org.p2p.solanaj.rpc;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.p2p.solanaj.rpc.types.RpcRequest;
import org.p2p.solanaj.rpc.types.RpcResponse;

public class RpcClient {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String endpoint;
    private OkHttpClient httpClient = new OkHttpClient();
    private RpcApi rpcApi;

    public RpcClient(Cluster endpoint) {
        this(endpoint.getEndpoint());
    }

    public RpcClient(String endpoint) {
        this.endpoint = endpoint;
        rpcApi = new RpcApi(this);
    }

    private void saveToFile(String fileName,String content){
        try {
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8),0,content.length());
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
        }
    }

    public <T> T callLog(String method, List<Object> params, Class<T> clazz) throws RpcException {
        RpcRequest rpcRequest = new RpcRequest(method, params);

        JsonAdapter<RpcRequest> rpcRequestJsonAdapter = new Moshi.Builder().build().adapter(RpcRequest.class);
        JsonAdapter<RpcResponse<T>> resultAdapter = new Moshi.Builder().build()
                .adapter(Types.newParameterizedType(RpcResponse.class, Type.class.cast(clazz)));

        Request request = new Request.Builder().url(endpoint)
                .post(RequestBody.create(rpcRequestJsonAdapter.toJson(rpcRequest), JSON)).build();

        try {
            Response response = httpClient.newCall(request).execute();
//
            if (method.equalsIgnoreCase("getBlock")){
                saveToFile(method,response.body().string());
            }
            return null;
//            RpcResponse<T> rpcResult = resultAdapter.fromJson(response.body().string());
//
//            if (rpcResult.getError() != null) {
//                throw new RpcException(rpcResult.getError().getMessage());
//            }
//
//            return (T) rpcResult.getResult();
        } catch (IOException e) {
            throw new RpcException(e.getMessage());
        }
    }

    public <T> T call(String method, List<Object> params, Class<T> clazz) throws RpcException {
        RpcRequest rpcRequest = new RpcRequest(method, params);

        JsonAdapter<RpcRequest> rpcRequestJsonAdapter = new Moshi.Builder().build().adapter(RpcRequest.class);
        JsonAdapter<RpcResponse<T>> resultAdapter = new Moshi.Builder().build()
                .adapter(Types.newParameterizedType(RpcResponse.class, Type.class.cast(clazz)));

        Request request = new Request.Builder().url(endpoint)
                .post(RequestBody.create(rpcRequestJsonAdapter.toJson(rpcRequest), JSON)).build();

        try {
            Response response = httpClient.newCall(request).execute();
            RpcResponse<T> rpcResult = resultAdapter.fromJson(response.body().string());

            if (rpcResult.getError() != null) {
                throw new RpcException(rpcResult.getError().getMessage());
            }

            return (T) rpcResult.getResult();
//            Response response = httpClient.newCall(request).execute();
//
//            if (method.equalsIgnoreCase("getBlock")){
//                saveToFile(method,response.body().string());
//            }
//            return null;
//            RpcResponse<T> rpcResult = resultAdapter.fromJson(response.body().string());
//
//            if (rpcResult.getError() != null) {
//                throw new RpcException(rpcResult.getError().getMessage());
//            }
//
//            return (T) rpcResult.getResult();
        } catch (IOException e) {
            throw new RpcException(e.getMessage());
        }
    }

    public RpcApi getApi() {
        return rpcApi;
    }

    public String getEndpoint() {
        return endpoint;
    }

}
