package draw.service.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import draw.service.entity.memento.IState;

public class GsonRegister {

    public static Gson build() {
        GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.registerTypeAdapter(IState.class, new InterfaceAdapter());
        return gsonBuilder.create();
    }

}
