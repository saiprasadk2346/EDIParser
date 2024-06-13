package com.parser.model;

import java.util.Date;

public class Container {

    private String messageType;
    private String customHouseCode;
    private String carnNumber;
    private String imoCodeOfVessel;
    private String vesselCode;
    private String voyageNo;
    private String igmNo;
    private Date igmDate;
    private int lineNo;
    private int subLineNo;
    private String containerNo;
    private String containerSealNo;
    private String containerAgentCode;
    private String containerStatus;
    private int totalPackages;
    private Double containerWeight;
    private String isoCode;
    private boolean shippersOwnContainer;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCustomHouseCode() {
        return customHouseCode;
    }

    public String getCarnNumber() {
        return carnNumber;
    }

    public void setCarnNumber(String carnNumber) {
        this.carnNumber = carnNumber;
    }

    public void setCustomHouseCode(String customHouseCode) {
        this.customHouseCode = customHouseCode;
    }

    public String getImoCodeOfVessel() {
        return imoCodeOfVessel;
    }

    public void setImoCodeOfVessel(String imoCodeOfVessel) {
        this.imoCodeOfVessel = imoCodeOfVessel;
    }

    public String getVesselCode() {
        return vesselCode;
    }

    public void setVesselCode(String vesselCode) {
        this.vesselCode = vesselCode;
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo;
    }

    public String getIgmNo() {
        return igmNo;
    }

    public void setIgmNo(String igmNo) {
        this.igmNo = igmNo;
    }

    public Date getIgmDate() {
        return igmDate;
    }

    public void setIgmDate(Date igmDate) {
        this.igmDate = igmDate;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public int getSubLineNo() {
        return subLineNo;
    }

    public void setSubLineNo(int subLineNo) {
        this.subLineNo = subLineNo;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getContainerSealNo() {
        return containerSealNo;
    }

    public void setContainerSealNo(String containerSealNo) {
        this.containerSealNo = containerSealNo;
    }

    public String getContainerAgentCode() {
        return containerAgentCode;
    }

    public void setContainerAgentCode(String containerAgentCode) {
        this.containerAgentCode = containerAgentCode;
    }

    public String getContainerStatus() {
        return containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public int getTotalPackages() {
        return totalPackages;
    }

    public void setTotalPackages(int totalPackages) {
        this.totalPackages = totalPackages;
    }

    public Double getContainerWeight() {
        return containerWeight;
    }

    public void setContainerWeight(Double containerWeight) {
        this.containerWeight = containerWeight;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public boolean getShippersOwnContainer() {
        return shippersOwnContainer;
    }

    public void setShippersOwnContainer(boolean shippersOwnContainer) {
        this.shippersOwnContainer = shippersOwnContainer;
    }
}
