package com.parser.model;

import java.util.ArrayList;
import java.util.List;

public class Manifest {
    private List<VesselInfo> vesselInfos;
    private List<Cargo> cargos;
    private List<Container> containers;

    public List<VesselInfo> getVesselInfos() {
        if (vesselInfos == null) {
            vesselInfos = new ArrayList<>();
        }
        return vesselInfos;
    }

    public void setVesselInfos(List<VesselInfo> vesselInfos) {
        this.vesselInfos = vesselInfos;
    }

    public List<Cargo> getCargos() {
        if (cargos == null) {
            cargos = new ArrayList<>();
        }
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public List<Container> getContainers() {
        if (containers == null) {
            containers = new ArrayList<>();
        }
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
}
