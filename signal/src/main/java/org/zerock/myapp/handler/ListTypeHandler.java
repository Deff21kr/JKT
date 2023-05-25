package org.zerock.myapp.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.zerock.myapp.domain.AttachFileDTO;

public class ListTypeHandler extends BaseTypeHandler<List<AttachFileDTO>>  {


	    @Override
	    public void setNonNullParameter(PreparedStatement ps, int i, List<AttachFileDTO> parameter, JdbcType jdbcType) throws SQLException {
	        // List<AttachFileDTO>를 JDBC 타입에 맞게 변환하여 PreparedStatement에 설정
	        // 예: AttachFileDTO의 필드를 구분자로 연결한 문자열로 변환한 후 PreparedStatement에 설정
	        StringBuilder sb = new StringBuilder();
	        for (AttachFileDTO dto : parameter) {
	            sb.append(dto.getFileName()).append(",")
	              .append(dto.getUploadPath()).append(",")
	              .append(dto.getUuid()).append(";");
	        }
	        String value = sb.deleteCharAt(sb.length() - 1).toString(); // 마지막 세미콜론 제거
	        ps.setString(i, value);
	    }//setNonNullParameter

	    @Override
	    public List<AttachFileDTO> getNullableResult(ResultSet rs, String columnName) throws SQLException {
	        // JDBC 결과에서 columnName 컬럼의 값을 List<AttachFileDTO> 타입으로 변환하여 반환
	        // 예: 문자열로 저장된 값에서 필드 값을 추출하여 AttachFileDTO 객체로 변환한 후 List로 반환
	        String value = rs.getString(columnName);
	        List<AttachFileDTO> list = new ArrayList<>();
	        if (value != null && !value.isEmpty()) {
	            String[] items = value.split(";");
	            for (String item : items) {
	                String[] fields = item.split(",");
	                if (fields.length == 3) {
	                    AttachFileDTO dto = new AttachFileDTO();
	                    dto.setFileName(fields[0]);
	                    dto.setUploadPath(fields[1]);
	                    dto.setUuid(fields[2]);
	                    list.add(dto);
	                }//if 
	            }//for
	        }//if
	        return list;
	    }//getNullableResult1

	    @Override
	    public List<AttachFileDTO> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	        // JDBC 결과에서 columnIndex 위치의 값을 List<AttachFileDTO> 타입으로 변환하여 반환
	        // 예: 문자열로 저장된 값에서 필드 값을 추출하여 AttachFileDTO 객체로 변환한 후 List로 반환
	        String value = rs.getString(columnIndex);
	        List<AttachFileDTO> list = new ArrayList<>();
	        if (value != null && !value.isEmpty()) {
	            String[] items = value.split(";");
	            for (String item : items) {
	                String[] fields = item.split(",");
	                if (fields.length == 3) {
	                    AttachFileDTO dto = new AttachFileDTO();
	                    dto.setFileName(fields[0]);
	                    dto.setUploadPath(fields[1]);
	                    dto.setUuid(fields[2]);
	                    list.add(dto);
	                }//if
	            }//for
	        }//if
	        return list;
	    }//getNullableResult2

	    @Override
	    public List<AttachFileDTO> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	        // CallableStatement에서 columnIndex 위치의 값을 List<AttachFileDTO> 타입으로 변환하여 반환
	        // 예: 문자열로 저장된 값에서 필드 값을 추출하여 AttachFileDTO 객체로 변환한 후 List로 반환
	        String value = cs.getString(columnIndex);
	        List<AttachFileDTO> list = new ArrayList<>();
	        if (value != null && !value.isEmpty()) {
	            String[] items = value.split(";");
	            for (String item : items) {
	                String[] fields = item.split(",");
	                if (fields.length == 3) {
	                    AttachFileDTO dto = new AttachFileDTO();
	                    dto.setFileName(fields[0]);
	                    dto.setUploadPath(fields[1]);
	                    dto.setUuid(fields[2]);
	                    list.add(dto);
	                }//if
	            }//for
	        }//if
	        return list;
	    }//getNullableResult3
	
}//ListTypeHandler
