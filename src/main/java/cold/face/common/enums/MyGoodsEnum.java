package cold.face.common.enums;

import cold.face.common.enums.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

public enum MyGoodsEnum implements BaseEnum<MyGoodsEnum, Integer> {

    GOODS_COMPUTER(1, "电脑"),
    GOODS_MOBILE(2, "手机"),
    GOODS_WATCH(3, "手表");

    private final int value;
    private final String displayName;
    private static Map<Integer, MyGoodsEnum> valueMap;

    static {
        valueMap = new HashMap();
        for (MyGoodsEnum e : values())
            valueMap.put(e.value, e);
    }

    MyGoodsEnum(int value, String displayName) {
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
    public String getName() {
        return this.name();
    }

    public static MyGoodsEnum getEnum(Integer key) {
        if (!valueMap.containsKey(key)) {
            return null;
        }
        return valueMap.get(key);
    }

    public Map<Integer, MyGoodsEnum> getAllValueMap() {
        return valueMap;
    }
}
