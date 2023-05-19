package com.spaghettierol.demo2.dto.request;

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
public class CreateSessionRequest {
    @NotNull(message = "Topic info can not be null!")
    @NotBlank(message = "Topic info can not be blank!")
    private String topic;
    @NotNull(message = "Date Time info can not be null!")
    private Timestamp datetime;
    @NotNull(message = "Duration info can not be null!")
    private int duration;
}
