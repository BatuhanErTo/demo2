package com.spaghettierol.demo2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetSessionResponse {
    private String topic;
    private Timestamp datetime;
    private int duration;
}
