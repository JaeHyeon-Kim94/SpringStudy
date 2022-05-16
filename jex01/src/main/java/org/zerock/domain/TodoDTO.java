package org.zerock.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TodoDTO {
    private String title;
    //http://localhost:8080/sample/ex03?title=test&dueDate=2021/05/16
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dueDate;
}
