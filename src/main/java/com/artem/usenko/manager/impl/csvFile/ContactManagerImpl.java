package com.artem.usenko.manager.impl.csvFile;

import com.artem.usenko.dto.Contact;
import com.artem.usenko.dto.mapper.csv.ContactMapper;
import com.artem.usenko.manager.ContactManager;
import com.artem.usenko.model.csv.ContactCSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Profile("csvFile")
@Repository
public class ContactManagerImpl implements ContactManager {

    @Value("${ContactPath}")
    private String contactPath;
    private static int lastId = 0;
    private static int contCol = 0;

    private static final String COMMA_DELIMITER = ",";

    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "id,userId,firstName,lastName,patronymic,mobilephone,phone,address,email";

    @PostConstruct
    public void setUp() throws IOException {
        List csvRecords = getCSVRecords();
        int size = csvRecords.size();
        if (size == 0) return;
        CSVRecord csvRecord = (CSVRecord) csvRecords.get(size - 1);
        String s = csvRecord.get(0);
        char[] chars = s.toCharArray();
        for (int i = 0; i<chars.length;++i) {
            char aChar = chars[i];
           if  (Character.isDigit(aChar)) {
                lastId *= 10;
                lastId += (int) (aChar - 48);
            }
        }
        contCol = size;
    }

    @Override
    public List<Contact> getAllContactsByUserId(int userId) throws IOException {
        String s = String.valueOf(userId);
        List csvRecords = getCSVRecords();
        List<ContactCSV> contactCSVS = new ArrayList<>();
        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            if (record.get(1).equals(s)) {
                ContactCSV contactCSV =
                        new ContactCSV(record.get(0), record.get(1), record.get(2),
                                record.get(3), record.get(4), record.get(5),
                                record.get(6), record.get(7), record.get(8));
                contactCSVS.add(contactCSV);
            }
        }

        return ContactMapper.fromAO(contactCSVS);
    }

    @Override
    public Contact updateContact(Contact contact) throws IOException {
        List csvRecords = getCSVRecords();
        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);

            if (record.get(0).equals(String.valueOf(contact.getId()))) {
                rewriteFile(csvRecords, i, ContactMapper.toAO(contact));
                return contact;
            }

        }
        return null;
    }

    @Override
    public void deleteContactById(int id) throws IOException {
        List csvRecords = getCSVRecords();
        for (int i = 1; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            if (record.get(0).equals(String.valueOf(id))) {
                rewriteFile(csvRecords, i, null);
            }

        }
    }

    @Override
    public Contact addContact(Contact contact) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(contactPath, true);
        fileOutputStream.write(239);
        fileOutputStream.write(187);
        fileOutputStream.write(191);
        BufferedWriter fileWriter = new BufferedWriter
                (new OutputStreamWriter(fileOutputStream, "UTF-8"));
        if (contCol == 0) {
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);

        }
        contact.setId(lastId + 1);
        ContactCSV contactCSV = ContactMapper.toAO(contact);
        fileWriter.append(contactCSV.getId());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getUserId());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getFirstName());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getLastName());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getPatronymic());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getMobilephone());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getPhone());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getAddress());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(contactCSV.getEmail());
        fileWriter.append(NEW_LINE_SEPARATOR);
        fileWriter.flush();
        fileWriter.close();
        lastId++;
        contCol++;
        return contact;
    }

    private List getCSVRecords() throws IOException {
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        BufferedReader fileReader = new BufferedReader(
                (new InputStreamReader(new FileInputStream(contactPath), "UTF-8")));
        CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
        List csvRecords = csvFileParser.getRecords();
        fileReader.close();
        return csvRecords;
    }

    private void rewriteFile(List csvRecords, int index, ContactCSV contactCSV) throws IOException {
        File file = new File(contactPath);
        file.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(contactPath, true);
        fileOutputStream.write(239);
        fileOutputStream.write(187);
        fileOutputStream.write(191);
        BufferedWriter fileWriter = new BufferedWriter
                (new OutputStreamWriter(fileOutputStream, "UTF-8"));
        fileWriter.append(FILE_HEADER);
        fileWriter.append(NEW_LINE_SEPARATOR);
        for (int i = 1; i < csvRecords.size(); ++i) {
            if (i == index) {
                if (contactCSV != null) {
                    fileWriter.append(contactCSV.getId());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getUserId());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getFirstName());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getLastName());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getPatronymic());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getMobilephone());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getPhone());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getAddress());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(contactCSV.getEmail());
                    fileWriter.append(NEW_LINE_SEPARATOR);
                } } else {
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
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(6));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(7));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(record.get(8));
                fileWriter.append(NEW_LINE_SEPARATOR);
                }
            }

        contCol--;
        fileWriter.close();
    }
}
