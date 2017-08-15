package com.artem.usenko.service;


import com.artem.usenko.dto.User;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;

public interface RegService {

    User registration(User user) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;

    Boolean thisLoginAlreadyNotExists(String login) throws IOException;
}
