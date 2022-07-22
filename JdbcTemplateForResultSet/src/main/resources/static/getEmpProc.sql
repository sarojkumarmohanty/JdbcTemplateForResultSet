CREATE DEFINER=`root`@`localhost` PROCEDURE `getEmpProc`()
BEGIN
	select employee_id, last_name, salary from employees;
    select department_id,department_name from departments;
END