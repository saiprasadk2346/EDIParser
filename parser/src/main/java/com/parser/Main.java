package com.parser;

import com.parser.model.EDIFile;
import com.parser.parser.EDIParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Please provide sample EDI file path in the program arguments");
                return;
            }
            EDIFile ediFile = EDIParser.parseEDIFile(args[0]);
            System.out.println(ediFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}