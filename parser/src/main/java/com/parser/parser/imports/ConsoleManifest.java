package com.parser.parser.imports;

import com.parser.model.Cargo;
import com.parser.model.Container;
import com.parser.model.Manifest;
import com.parser.parser.EDIMessage;
import com.parser.util.EDIUtil;
import org.apache.commons.lang3.BooleanUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleManifest implements EDIMessage {

    private static ConsoleManifest instance = null;

    private ConsoleManifest() {}

    public static ConsoleManifest getInstance() {
        if (instance == null) {
            instance = new ConsoleManifest();
        }
        return instance;
    }

    @Override
    public Manifest parse(BufferedReader br) throws IOException {
        String line;
        Manifest manifest = new Manifest();
        while ((line = br.readLine()) != null) {
            if (line.startsWith("<conscargo>")) {
                line = br.readLine();
                while (line != null && !line.startsWith("<END-conscargo>")) {
                    manifest.getCargos().add(parseCargo(line));
                    line = br.readLine();
                }
            } else if (line.startsWith("<conscont>")) {
                line = br.readLine();
                while (line != null && !line.startsWith("<END-conscont>")) {
                    manifest.getContainers().add(parseContainer(line));
                    line = br.readLine();
                }
            }
        }
        return manifest;
    }

    private Cargo parseCargo(String line) {
        String[] elements = line.trim().split("");
        Cargo cargo = new Cargo();
        cargo.setMessageType(elements[0]);
        cargo.setCustomHouseCode(elements[1]);
        cargo.setCarnNumber(elements[2]);
        cargo.setIgmNo(elements[3]);
        cargo.setIgmDate(EDIUtil.parseDate(elements[4], ""));
        cargo.setImoCodeOfVessel(elements[5]);
        cargo.setVesselCode(elements[6]);
        cargo.setVoyageNo(elements[7]);
        cargo.setLineNo(Integer.parseInt(elements[8]));
        cargo.setSubLineNo(Integer.parseInt(elements[9]));
        cargo.setMasterBLNo(elements[10]);
        cargo.setMasterBLDate(EDIUtil.parseDate(elements[11], "ddMMyyyy"));
        cargo.setPortOfLoading(elements[12]);
        cargo.setPortOfDestination(elements[13]);
        cargo.setHouseBlNo(elements[14]);
        cargo.setHouseBlDate(EDIUtil.parseDate(elements[15], "ddMMyyyy"));
        cargo.setImporterName(elements[16]);
        cargo.setImporterAddress1(elements[17]);
        cargo.setImporterAddress2(elements[18]);
        cargo.setImporterAddress3(elements[19]);
        cargo.setConsigneeName(elements[20]);
        cargo.setConsigneeAddress1(elements[21]);
        cargo.setConsigneeAddress2(elements[22]);
        cargo.setConsigneeAddress3(elements[23]);
        cargo.setNatureOfCargo(elements[24]);
        cargo.setItemType(elements[25]);
        cargo.setCargoMovement(elements[26]);
        cargo.setPortOfDischarge(elements[27]);
        cargo.setNumberOfPackages(elements[28]);
        cargo.setTypeOfPackages(elements[29]);
        cargo.setGrossWeight(EDIUtil.parseDouble(elements[30]));
        cargo.setUnitOfWeight(elements[31]);
        cargo.setGrossVolume(EDIUtil.parseDouble(elements[32]));
        cargo.setUnitOfVolume(elements[33]);
        cargo.setMarksOfNumbers(elements[34]);
        cargo.setGoodsDescription(elements[35]);
        cargo.setUnoCode(elements[36]);
        cargo.setImoCode(elements[37]);
        cargo.setTransitBondNo(elements[38]);
        cargo.setCarrierCode(elements[39]);
        cargo.setModeOfTransport(elements[40]);
        cargo.setMloCode(elements[41]);
        return cargo;
    }

    private Container parseContainer(String line) {
        String[] elements = line.trim().split("");
        Container container = new Container();
        container.setMessageType(elements[0]);
        container.setCustomHouseCode(elements[1]);
        container.setCarnNumber(elements[2]);
        container.setIgmNo(elements[3]);
        container.setIgmDate(EDIUtil.parseDate(elements[4], "ddMMyyyy"));
        container.setImoCodeOfVessel(elements[5]);
        container.setVesselCode(elements[6]);
        container.setVoyageNo(elements[7]);
        container.setLineNo(Integer.parseInt(elements[8]));
        container.setSubLineNo(Integer.parseInt(elements[9]));
        container.setContainerNo(elements[10]);
        container.setContainerSealNo(elements[11]);
        container.setContainerAgentCode(elements[12]);
        container.setContainerStatus(elements[13]);
        container.setTotalPackages(Integer.parseInt(elements[14]));
        container.setContainerWeight(EDIUtil.parseDouble(elements[15]));
        container.setIsoCode(elements[16]);
        container.setShippersOwnContainer(BooleanUtils.toBoolean(elements[17]));
        return container;
    }
}
