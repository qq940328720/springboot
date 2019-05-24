package cold.face.common.enums.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class EnumSerializer extends JsonSerializer<BaseEnum> {
    @Override
    public void serialize(BaseEnum value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (null != value) {
            jgen.writeStartObject();
            jgen.writeFieldName("value");
            jgen.writeObject(value.getValue());
            jgen.writeFieldName("enumName");
            jgen.writeObject(value.getEnumName());
            jgen.writeFieldName("displayName");
            jgen.writeString(value.getDisplayName());
            jgen.writeEndObject();
        } else {
            jgen.writeNull();
        }
    }
}