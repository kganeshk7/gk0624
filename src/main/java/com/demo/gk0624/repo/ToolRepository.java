package com.demo.gk0624.repo;

import com.demo.gk0624.entity.ToolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<ToolInfo, String> {}