package com.parser.parser;

import com.parser.factory.EDIMessageFactory;
import com.parser.model.EDIFile;
import com.parser.model.Header;
import com.parser.model.Manifest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EDIParser {

    public static EDIFile parseEDIFile(String filePath) throws IOException {
        EDIFile ediFile = new EDIFile();
        Manifest manifest;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("HREC")) {
                    Header header = parseHeader(line);
                    EDIMessage message = EDIMessageFactory.getParser(header.getMessageId());
                    manifest = message.parse(br);
                    ediFile.setManifest(manifest);
                    ediFile.setHeader(header);
                }
            }
            return ediFile;
        }
    }

    private static Header parseHeader(String line) {
        String[] elements = line.split("");
        Header header = new Header();
        header.setSenderId(elements[2]);
        header.setReceiverId(elements[4]);
        header.setVersionNo(elements[5]);
        header.setIndicator(elements[6]);
        header.setMessageId(elements[8]);
        header.setSequenceOrControlNumber(elements[9]);
        header.setDateOfTransmission(elements[10]);
        header.setTimeOfTransmission(elements[11]);
        return header;
    }
}

