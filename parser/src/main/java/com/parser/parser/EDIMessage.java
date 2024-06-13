package com.parser.parser;

import com.parser.model.Manifest;

import java.io.BufferedReader;
import java.io.IOException;

public interface EDIMessage {
    Manifest parse(BufferedReader br) throws IOException;
}
