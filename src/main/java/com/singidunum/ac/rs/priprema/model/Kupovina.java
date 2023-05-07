package com.singidunum.ac.rs.priprema.model;

import java.util.Set;

public class Kupovina {
    private Set<StavkaKupovine> stavkeKupovina;

    public Kupovina() {
    }

    public Kupovina(Set<StavkaKupovine> stavkeKupovina) {
        this.stavkeKupovina = stavkeKupovina;
    }

    public Set<StavkaKupovine> getStavkeKupovina() {
        return stavkeKupovina;
    }

    public void setStavkeKupovina(Set<StavkaKupovine> stavkeKupovina) {
        this.stavkeKupovina = stavkeKupovina;
    }

    @Override
    public String toString() {
        return "Kupovina{" +
                "stavkeKupovina=" + stavkeKupovina +
                '}';
    }
}
