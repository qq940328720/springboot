package cold.face.dal.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

public class NoodlesTypeProvider {
    public String selectNoodlesType() {
        return new SQL() {
            {
                SELECT("*");
                FROM("noodles_type");
            }
        }.toString();
    }
}
