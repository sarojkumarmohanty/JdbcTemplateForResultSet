package com.csmtech.controller;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csmtech.domain.Employee;

@Controller
@RequestMapping("/getData")
public class MyController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping
	public void calProcedure() {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

		/*
		 * Map<String, Object> pData = jdbcTemplate.call(new CallableStatementCreator()
		 * {
		 * 
		 * @Override public CallableStatement createCallableStatement(Connection con)
		 * throws SQLException { CallableStatement cs =
		 * con.prepareCall("{call getEmpProc()}");
		 * 
		 * return cs; } }, new ArrayList<SqlParameter>());
		 */

		/*
		 * List<Employee> empList = new ArrayList<Employee>();
		 * simpleJdbcCall.withProcedureName("getEmpProc").returningResultSet(
		 * "#result-set-1", new RowMapper<Employee>() {
		 * 
		 * @Override public Employee mapRow(ResultSet rs, int rowNum) throws
		 * SQLException { Employee e = new Employee(); e.setEmployeeId(rs.getLong(1));
		 * e.setLastName(rs.getString(2)); e.setSalary(rs.getBigDecimal(3));
		 * empList.add(e); return e; } });
		 * 
		 * System.out.println(empList);
		 */

		List<Employee> empList = new ArrayList<>();
		Map<String, Object> pData = simpleJdbcCall.withProcedureName("getEmpProc").execute();

		ArrayList l = (ArrayList) pData.get("#result-set-1");

		for (Object x : l) {
			LinkedCaseInsensitiveMap m = (LinkedCaseInsensitiveMap) x;
			Employee e = new Employee();
			e.setEmployeeId((Long) m.get("employee_id"));
			e.setLastName((String) m.get("last_name"));
			e.setSalary((BigDecimal) m.get("salary"));
			empList.add(e);
		}

		empList.forEach(s -> System.out.println(s));

	}

}
