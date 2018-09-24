package cold.face.adapter.impl;

import cold.face.dal.dao.NoodlesTypeMapper;
import cold.face.dal.model.NoodlesType;
import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.MyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyTestServiceImpl implements MyTestService {
    @Autowired
    private NoodlesTypeMapper noodlesTypeMapper;

    //自动注入jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseDTO getNoodlesTypeByJDBCTmp() {
        ResponseDTO responseDTO = new ResponseDTO();
        String sql = "SELECT * from noodles_type where id < ?";
        Object types = this.jdbcTemplate.execute(sql, new PreparedStatementCallback() {
            @Nullable
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//              1就是sql的第一个参数的意思，
//              ps.setint(1,id); 就是把id替代sql的第一个问号
                ps.setInt(1, 2);
                ResultSet rs = ps.executeQuery();
                NoodlesType userinfo = new NoodlesType();
                List<NoodlesType> list = new ArrayList();
                while (rs.next()) {
                    userinfo.setId(rs.getInt("id"));
                    userinfo.setName(rs.getString("name"));
                    userinfo.setTypeCode(rs.getString("type_code"));
                    userinfo.setParentTypeCode(rs.getString("parent_type_code"));
                    userinfo.setRemark(rs.getString("remark"));
                    list.add(userinfo);
                }
                return list;
            }
        });
        responseDTO.setData(types);
        return responseDTO;
    }

    @Override
    public ResponseDTO getNoodlesTypeByMybatis() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<NoodlesType> types = noodlesTypeMapper.getAllNoodlesType();
        responseDTO.setData(types);
        return responseDTO;
    }

    @Override
    public ResponseDTO getNoodlesTypeBySqlProvider() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<NoodlesType> types = noodlesTypeMapper.getNoodlesTypeBySqlProvider();
        responseDTO.setData(types);
        return responseDTO;
    }

    @Override
    public ResponseDTO exceptionTest() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<NoodlesType> types = null;
        types.get(0).setRemark("aaaa");
        responseDTO.setData(types);
        return responseDTO;
    }
}
