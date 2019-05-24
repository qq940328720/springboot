package cold.face.common.enums;

import cold.face.common.enums.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

public enum MqReciverTypeEnum implements BaseEnum<MqReciverTypeEnum, Integer> {
    
    MQ_HELLO(1, "hello"),
    MQ_NOODLE_TYPE(2, "noodleType"),
    MQ_NOODLE(3, "noodle");

    private final int value;
    private final String displayName;
    private static Map<Integer, MqReciverTypeEnum> valueMap;

    static {
        valueMap = new HashMap();
        for (MqReciverTypeEnum e : values())
            valueMap.put(e.value, e);
    }

    MqReciverTypeEnum(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getEnumName() {
        return this.name();
    }

    public static MqReciverTypeEnum getEnum(Integer key) {
        if (!valueMap.containsKey(key)) {
            return null;
        }
        return valueMap.get(key);
    }

    public Map<Integer, MqReciverTypeEnum> getAllValueMap() {
        return valueMap;
    }
}
