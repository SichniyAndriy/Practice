package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component("country")
@Scope("prototype")
@NoArgsConstructor
public class Country {
    private String name;
    private String capital;
    private String code;
    private String code2;
    private String flag;
}
