package com.jasonpeng.demo.util.common;

import com.alibaba.fastjson.JSONObject;

public class ResultBuilder {

    public static final String STATUS_KEY = "status";
    public static final String MESSAGE_KEY = "message";
    public static final String DATA_KEY = "data";

    public static class Builder<T>{
        private JSONObject jsonObject = new JSONObject();

        public Builder<T> status(Object code){
            jsonObject.put(STATUS_KEY, code);
            return this;
        }

        public Builder<T> message(Object message){
            jsonObject.put(MESSAGE_KEY, message);
            return this;
        }

        public Builder<T> data(Object data){
            jsonObject.put(DATA_KEY, data);
            return this;
        }

        public JSONObject build(){
            return jsonObject;
        }
    }

    public static JSONObject buildSimple(Object code, Object message){
        return new Builder<Void>().status(code).message(message).build();
    }

    public static <T> JSONObject build(Object code, Object message, T data){
        return new Builder<T>().status(code).message(message).data(data).build();
    }
}
