package org.example_04;


import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String firstName;
    private String secondName;
    private String username;
    private String email;
    private int age;

}
