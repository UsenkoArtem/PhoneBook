package com.artem.usenko.manager.impl.csvFile;

import com.artem.usenko.dto.User;
import com.artem.usenko.dto.mapper.csv.UserMapper;
import com.artem.usenko.manager.UserManager;
import com.artem.usenko.model.csv.UserCSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Profile("csvFile")
@Repository
public class UserManagerImpl implements UserManager {


    @Value("${UserPath}")
    private String baseDir;
    private static int lastId = 0;
    private static int colUser = 0;

    private static final String COMMA_DELIMITER = ",";

    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "id,login,firstName,lastName,patronymic,password";

    @PostConstruct
    public void setUp() throws IOException {
        List csvRecords = getCSVRecords();
        int size = csvRecords.size();
        if (size == 0) return;
        CSVRecord csvRecord = (CSVRecord) csvRecords.get(size - 1);
        String s = csvRecord.get(0);
        System.out.println(s.toLowerCase());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char aChar = chars[i];
            if (Character.isDigit(aChar)) {
                lastId *= 10;
                lastId += (int) (aChar - 48);
            }
        }
        colUser = size;
    }


    @Override
    public User getUserByLogin(String login) throws IOException {
        List csvRecords = getCSVRecords();
        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            UserCSV userCSV = new UserCSV(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5));
            if (userCSV.getLogin().equals(login))
                return UserMapper.fromAO(userCSV);

        }

        return null;
    }

    @Override
    public User addNewUser(User user) throws IOException {
        System.out.println(baseDir);
        FileOutputStream fileOutputStream = new FileOutputStream(baseDir, true);
        fileOutputStream.write(239);
        fileOutputStream.write(187);
        fileOutputStream.write(191);
        BufferedWriter fileWriter = new BufferedWriter
                (new OutputStreamWriter(fileOutputStream, "UTF-8"));
        if (colUser == 0) {
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);

        }
        user.setId(lastId + 1);
        fileWriter.append(String.valueOf(user.getId()));
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getLogin());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getFirstName());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getLastName());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getPatronymic());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getPassword());
        fileWriter.append(NEW_LINE_SEPARATOR);
        fileWriter.flush();
        fileWriter.close();
        lastId++;
        colUser++;
        return user;
    }

    @Override
    public User getUserById(int id) throws IOException {
        List csvRecords = getCSVRecords();
        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            UserCSV userCSV = new UserCSV(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5));
            if (userCSV.getId().equals(String.valueOf(id)))
                return UserMapper.fromAO(userCSV);

        }

        return null;
    }

    private List getCSVRecords() throws IOException {
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        BufferedReader fileReader = new BufferedReader(
                (new InputStreamReader(new FileInputStream(baseDir), "UTF-8")));
        CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
        List csvRecords = csvFileParser.getRecords();
        fileReader.close();
        return csvRecords;
    }

    private void rewriteFile(List csvRecords, int index, UserCSV user) throws IOException {
        File file = new File(baseDir);
        file.delete();
        BufferedWriter fileWriter = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(baseDir, true), "UTF-8"));
        fileWriter.append(FILE_HEADER);
        fileWriter.append(NEW_LINE_SEPARATOR);
        for (int i = 1; i < csvRecords.size(); ++i) {
            if (i == index) {
                if (user != null) {

                    fileWriter.append(user.getId());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(user.getLogin());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(user.getFirstName());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(user.getLastName());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(user.getPatronymic());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(user.getPassword());
                    fileWriter.append(NEW_LINE_SEPARATOR);
                }
            } else {
                CSVRecord record = (CSVRecord) csvRecords.get(i);
                fileWriter.append(record.get(0));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(1));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(2));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(3));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(4));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(5));
                fileWriter.append(NEW_LINE_SEPARATOR);

            }
        }

        colUser--;
        fileWriter.close();
    }


}
