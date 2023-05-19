package com.spaghettierol.demo2.dto.request;

import com.spaghettierol.demo2.model.PositionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateConvenerRequest {
    @NotNull(message = "Name info can not be NULL!")
    @NotBlank(message = "Name info can not be BLANK!")
    private String name;
    @NotNull(message = "Position Type info can not be NULL!")
    private PositionType positionType;
}
