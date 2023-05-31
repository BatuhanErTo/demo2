package com.spaghettierol.demo2.dto.request;

import com.spaghettierol.demo2.dto.ModuleDtoMutual;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSessionDto {
    @NotNull(message = "Topic info can not be NULL!")
    @NotBlank(message = "Topic info can not be BLANK!")
    private String topic;
    @NotNull(message = "DateTime info can not be NULL!")
    private Timestamp datetime;
    @NotNull(message = "Duration info can not be NULL!")
    private int duration;
    @NotNull(message = "Module info can not be NULL!")
    private ModuleDtoMutual moduleDto;
}
