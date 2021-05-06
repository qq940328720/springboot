package cold.face.adapter.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijing_yan
 * @data 2021-04-29 10:56
 */
public class GoodsNumberCache {

    public static Map<Integer, Integer> map = new HashMap<>();

    static {
//        if (map.size() == 0) {
        map.put(1, 10);
        map.put(2, 11);
        map.put(3, 12);
//        }
    }

    public static Integer countDown(Integer key) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer value = map.get(key);
        if (value == null) {
            return -1;
        } else if (value.equals(0)) {
            return 0;
        } else {
            Integer ret = value - 1;
            map.put(key, ret);
            return ret;
        }
    }

    public static void fresh() {
        map.put(1, 10);
        map.put(2, 11);
        map.put(3, 12);
    }
}
