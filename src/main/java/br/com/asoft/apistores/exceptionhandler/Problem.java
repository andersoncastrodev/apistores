package br.com.asoft.apistores.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private String type;
    private String title;
    private Integer status;
    private LocalDateTime timestamp;
    private String detail;
    private String userMessage;
    private List<Fields> fields;
}
