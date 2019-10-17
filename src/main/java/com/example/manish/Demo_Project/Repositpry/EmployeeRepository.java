package com.example.manish.Demo_Project.Repositpry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.manish.Demo_Project.Model.EmployeeInfo;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeInfo,Integer>
{
	

	
	
}
