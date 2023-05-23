package section01.statement;


import model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;


public class Application5 {

    public static void main(String[] args) {

        /* 1. Connection 생성 */
        Connection con = getConnection();
        /* 2. Statement, ResultSet 생성 */
        Statement stmt = null;
        ResultSet rset = null;
        /* 3. 한 행의 정보를 담을 DTO 객체 생성 */
        EmployeeDTO row = null;
        /* 4. 여러 DTO를 하나의 인스턴스로 묶기 위한 List 생성 */
        List<EmployeeDTO> empList = new ArrayList<>();
        /* 5. 전체 직원 정보 조회 쿼리를 담은 String 변수 생성 */
        String query = "SELECT * FROM EMPLOYEE";
        /* 6. statment 생성 및 쿼리 실행 */
        try{
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

        /* 7. ResultSet에 존재하는 모든 결과값을 객체에 담아 배열에 추가 */
            while(rset.next()){
                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);
            }}
            catch(SQLException e){
            e.printStackTrace();}
        finally{
            /* 8. 자원 반납 */
            close(con);
            close(rset);
            close(stmt);

        }


        /* 9. 전체 직원 정보 오버라이딩된 toString으로 출력 */
        for(EmployeeDTO emp : empList){
            System.out.println(emp.toString());
        }
    }

}
