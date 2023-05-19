package com.spaghettierol.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDtoMutual {
    private Long id;
    private String code;
    private String title;
    private int level;
    private boolean optional;
}
