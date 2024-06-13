package com.parser.parser.imports;

import com.parser.model.Cargo;
import com.parser.model.Container;
import com.parser.model.Manifest;
import com.parser.model.VesselInfo;
import com.parser.parser.EDIMessage;
import com.parser.util.EDIUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class ImportGeneralManifest implements EDIMessage {

    private static ImportGeneralManifest instance = null;

    private ImportGeneralManifest() {}

    public static ImportGeneralManifest getInstance() {
        if (instance == null) {
            instance = new ImportGeneralManifest();
        }
        return instance;
    }


    @Override
    public Manifest parse(BufferedReader br) throws IOException {
        String line;
        Manifest manifest = new Manifest();
        while ((line = br.readLine()) != null) {
            if (line.startsWith("<vesinfo>")) {
                line = br.readLine();
                while (line != null && !line.startsWith("<END-vesinfo>")) {
                    manifest.getVesselInfos().add(parseVesselInfo(line));
                    line = br.readLine();
                }
            } else if (line.startsWith("<cargo>")) {
                line = br.readLine();
                while (line != null && !line.startsWith("<END-cargo>")) {
                    manifest.getCargos().add(parseCargo(line));
                    line = br.readLine();
                }
            } else if (line.startsWith("<contain>")) {
                line = br.readLine();
                while (line != null && !line.startsWith("<END-contain>")) {
                    manifest.getContainers().add(parseContainer(line));
                    line = br.readLine();
                }
            }
        }
        return manifest;
    }

    private VesselInfo parseVesselInfo(String line) {
        String[] elements = line.substring(2).split("");
        VesselInfo vesselInfo = new VesselInfo();
        vesselInfo.setCode(elements[0]);
        vesselInfo.setVoyageNumber(elements[1]);
        vesselInfo.setDate(elements[2]);
        vesselInfo.setUniqueId(elements[3]);
        vesselInfo.setCallSign(elements[4]);
        vesselInfo.setVesselName(elements[5]);
        vesselInfo.setCargoType(elements[6]);
        vesselInfo.setDepartureDate(elements[7]);
        return vesselInfo;
    }

    private Cargo parseCargo(String line) {
        String[] elements = line.trim().split("");
        Cargo cargo = new Cargo();
        cargo.setMessageType(elements[0]);
        cargo.setCustomHouseCode(elements[1]);
        cargo.setImoCodeOfVessel(elements[2]);
        cargo.setVesselCode(elements[3]);
        cargo.setVoyageNo(elements[4]);
        cargo.setIgmNo(elements[5]);
        cargo.setIgmDate(EDIUtil.parseDate(elements[6], "ddMMyyyy"));
        cargo.setLineNo(Integer.parseInt(elements[7]));
        cargo.setSubLineNo(Integer.parseInt(elements[8]));
        cargo.setBlNo(elements[9]);
        cargo.setBlDate(EDIUtil.parseDate(elements[10], "ddMMyyyy"));
        cargo.setPortOfLoading(elements[11]);
        cargo.setPortOfDestination(elements[12]);
        cargo.setHouseBlNo(elements[13]);
        cargo.setHouseBlDate(EDIUtil.parseDate(elements[14], "ddMMyyyy"));
        cargo.setImporterName(elements[15]);
        cargo.setImporterAddress1(elements[16]);
        cargo.setImporterAddress2(elements[17]);
        cargo.setImporterAddress3(elements[18]);
        cargo.setConsigneeName(elements[19]);
        cargo.setConsigneeAddress1(elements[20]);
        cargo.setConsigneeAddress2(elements[21]);
        cargo.setConsigneeAddress3(elements[22]);
        cargo.setNatureOfCargo(elements[23]);
        cargo.setItemType(elements[24]);
        cargo.setCargoMovement(elements[25]);
        cargo.setPortOfDischarge(elements[26]);
        cargo.setNumberOfPackages(elements[27]);
        cargo.setTypeOfPackages(elements[28]);
        cargo.setGrossWeight(EDIUtil.parseDouble(elements[29]));
        cargo.setUnitOfWeight(elements[30]);
        cargo.setGrossVolume(EDIUtil.parseDouble(elements[31]));
        cargo.setUnitOfVolume(elements[32]);
        cargo.setMarksOfNumbers(elements[33]);
        cargo.setGoodsDescription(elements[34]);
        cargo.setUnoCode(elements[35]);
        cargo.setImoCode(elements[36]);
        cargo.setTransitBondNo(elements[37]);
        cargo.setCarrierCode(elements[38]);
        cargo.setModeOfTransport(elements[39]);
        cargo.setMloCode(elements[40]);
        return cargo;
    }

    private Container parseContainer(String line) {
        String[] elements = line.trim().split("");
        Container container = new Container();
        container.setMessageType(elements[0]);
        container.setCustomHouseCode(elements[1]);
        container.setImoCodeOfVessel(elements[2]);
        container.setVesselCode(elements[3]);
        container.setVoyageNo(elements[4]);
        container.setIgmNo(elements[5]);
        container.setIgmDate(EDIUtil.parseDate(elements[6], "ddMMyyyy"));
        container.setLineNo(Integer.parseInt(elements[7]));
        container.setSubLineNo(Integer.parseInt(elements[8]));
        container.setContainerNo(elements[9]);
        container.setContainerSealNo(elements[10]);
        container.setContainerAgentCode(elements[11]);
        container.setContainerStatus(elements[12]);
        container.setTotalPackages(Integer.parseInt(elements[13]));
        container.setContainerWeight(EDIUtil.parseDouble(elements[14]));
        container.setIsoCode(elements[15]);
        container.setShippersOwnContainer(BooleanUtils.toBoolean(elements[16]));
        return container;
    }
}
