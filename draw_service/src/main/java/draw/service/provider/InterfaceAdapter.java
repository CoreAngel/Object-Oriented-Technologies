package draw.service.provider;

import com.google.gson.*;
import draw.service.entity.memento.IState;

import java.lang.reflect.Type;

public class InterfaceAdapter implements JsonSerializer<IState>, JsonDeserializer<IState> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    @Override
    public IState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive primitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = primitive.getAsString();
        Class objClass = this.getObjectClass(className);
        return jsonDeserializationContext.deserialize(jsonObject.get(DATA), objClass);
    }

    @Override
    public JsonElement serialize(IState o, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, o.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(o));
        return jsonObject;
    }

    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
