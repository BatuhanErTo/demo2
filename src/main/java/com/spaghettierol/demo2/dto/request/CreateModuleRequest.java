package com.spaghettierol.demo2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateModuleRequest{
    @NotNull(message = "Code info can not be NULL!")
    @NotBlank(message = "Code info can not be BLANK!")
    private String code;
    @NotNull(message = "Title info can not be NULL!")
    @NotBlank(message = "Title info can not be BLANK!")
    private String title;
    @NotNull(message = "Level info can not be NULL!")
    private int level;
    @NotNull(message = "Optional info can not be NULL!")
    private boolean optional;
}
