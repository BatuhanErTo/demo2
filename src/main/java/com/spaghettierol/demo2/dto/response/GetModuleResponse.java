package com.spaghettierol.demo2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetModuleResponse {
    private String code;
    private String title;
    private int level;
    private boolean optional;
}
