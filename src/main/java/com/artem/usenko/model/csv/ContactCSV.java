package com.artem.usenko.model.csv;


import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ContactCSV {
    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 1)
    private String userId;
    @CsvBindByPosition(position = 2)
    private String firstName;
    @CsvBindByPosition(position = 3)
    private String lastName;
    @CsvBindByPosition(position = 4)
    private String patronymic;
    @CsvBindByPosition(position = 5)
    private String mobilephone;
    @CsvBindByPosition(position = 6)
    private String phone;
    @CsvBindByPosition(position = 7)
    private String address;
    @CsvBindByPosition(position = 8)
    private String email;

}
