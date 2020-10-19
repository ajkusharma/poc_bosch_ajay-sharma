package com.example.sharma.demo01.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sharma.demo01.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{ }
