package com.demo.gk0624;

import com.demo.gk0624.entity.Tool;
import com.demo.gk0624.model.RentalRequest;
import com.demo.gk0624.repo.ToolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserControllerIntegrationTest {



    RentalRequest request;

    @Autowired
    private ToolRepository toolRepository;

    @Test
    void testFindAll() {
        List<Tool> tools = toolRepository.findAll();

    }

//    @Test
//    void testSaveTool() {
//        Tool tool = new Tool("TST1", "TestTool", "TestBrand", new BigDecimal("0.99"), true, true, true);
//        toolRepository.save(tool);
//        Tool foundTool = toolRepository.findById("TST1").orElse(null);
//        assertThat(foundTool).isNotNull();
//        assertThat(foundTool.getToolType()).isEqualTo("TestTool");
//    }
}