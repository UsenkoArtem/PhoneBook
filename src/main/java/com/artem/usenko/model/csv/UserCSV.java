package com.artem.usenko.model.csv;


import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCSV {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String login;

    @CsvBindByPosition(position = 2)
    private String firstName;

    @CsvBindByPosition(position = 3)
    private String lastName;

    @CsvBindByPosition(position = 4)
    private String patronymic;

    @CsvBindByPosition(position = 5)
    private String password;


}
