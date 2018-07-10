package com.ccore.Until;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by yutianran on 16/2/25.
 */
public class JsonUtil {

    private static Gson gson;

    //静态内部类的单例模式
    public static JsonUtil getInstance() {
        return SingletonHolder.mInstance;
    }

    private static class SingletonHolder {
        private static final JsonUtil mInstance = new JsonUtil();
    }

    //初始配置
    private JsonUtil() {
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();
    }

    public  <T> T stringToObj(String str, Type type) {
        return gson.fromJson(str, type);
    }

    public  String objToString(Object obj) {
        return gson.toJson(obj);
    }

    public class ItemTypeAdapterFactory implements TypeAdapterFactory {

        @Override
		public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            return new TypeAdapter<T>() {

                @Override
				public void write(JsonWriter out, T value) throws IOException {
                    delegate.write(out, value);
                }

                @Override
				public T read(JsonReader in) throws IOException {

                    JsonElement jsonElement = elementAdapter.read(in);
                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        if (jsonObject.has("cod") && jsonObject.get("cod").getAsInt() == 404) {
                            throw new IllegalArgumentException(jsonObject.get("message").getAsString());
                        }
                    }

                    return delegate.fromJsonTree(jsonElement);
                }
            }.nullSafe();
        }
    }



}
