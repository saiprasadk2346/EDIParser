package com.parser.factory;

import com.parser.parser.EDIMessage;
import com.parser.parser.imports.ConsoleManifest;
import com.parser.parser.imports.ImportGeneralManifest;

public class EDIMessageFactory {
    public static EDIMessage getParser(String messageType) {
        switch (messageType) {
            case "SACHI01":
                return ImportGeneralManifest.getInstance();
            case "CMCHI21":
                return ConsoleManifest.getInstance();
            // Add cases for other message types
            default:
                throw new IllegalArgumentException("Unsupported message type: " + messageType);
        }
    }
}
