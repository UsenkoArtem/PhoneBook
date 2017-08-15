package com.artem.usenko.dto;


import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3, max = 15)
    private String login;

    @Pattern(regexp = "^[А-Яа-яЁёЇїІіЄєҐґa-zA-z']+$")
    @Size(min = 5, max = 15)
    private String firstName;

    @Pattern(regexp = "^[А-Яа-яЁёЇїІіЄєҐґa-zA-z']+$")
    @Size(min = 5, max = 15)
    private String lastName;

    @Pattern(regexp = "^[А-Яа-яЁёЇїІіЄєҐґa-zA-z']+$")
    @Size(min = 5, max = 15)
    private String patronymic;

    @Size(min = 5, max = 15)
    private String password;


}
