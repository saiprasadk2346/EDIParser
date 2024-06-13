package com.parser.model;

public class Header {

    private String senderId;
    private String receiverId;
    private String versionNo;
    private String indicator;
    private String messageId;
    private String sequenceOrControlNumber;
    private String dateOfTransmission;
    private String timeOfTransmission;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSequenceOrControlNumber() {
        return sequenceOrControlNumber;
    }

    public void setSequenceOrControlNumber(String sequenceOrControlNumber) {
        this.sequenceOrControlNumber = sequenceOrControlNumber;
    }

    public String getDateOfTransmission() {
        return dateOfTransmission;
    }

    public void setDateOfTransmission(String dateOfTransmission) {
        this.dateOfTransmission = dateOfTransmission;
    }

    public String getTimeOfTransmission() {
        return timeOfTransmission;
    }

    public void setTimeOfTransmission(String timeOfTransmission) {
        this.timeOfTransmission = timeOfTransmission;
    }
}
