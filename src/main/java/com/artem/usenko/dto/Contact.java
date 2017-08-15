package com.artem.usenko.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private Integer id;

    @NotNull
    private int userId;

    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[А-Яа-яЁёЇїІіЄєҐґa-zA-z]+$")
    private String firstName;

    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[А-Яа-яЁёЇїІіЄєҐґa-zA-z]+$")
    private String lastName;

    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[А-Яа-яЁёЇїІіЄєҐґa-zA-z]+$")
    private String patronymic;

    @Size(max = 13)
    @Pattern(regexp = "^(\\+380)+(50|63|66|67|73|93|95|96|97|98|99)(?:[0-9] ?){6}[0-9]+$")
    private String mobilephone;
    @Size(max = 15)
    @Pattern(regexp = "^[0-9]+$|^$")
    private String phone;

    @Size(max = 50)
    private String address;


    @Pattern(regexp = "^[a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,15})$|^$")
    @Size(max = 30)
    private String email;

}
