package com.csmtech.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee implements Serializable {

	private Long employeeId;
	
	private String lastName;
	
	private BigDecimal salary;
	
	
}
