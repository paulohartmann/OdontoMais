package br.com.odontomais.model;

import odontomais.model.Convenio;

import java.time.LocalDate;

public class ConvenioTest {

    Convenio convenioComValidade;

    @org.junit.Before
    public void setUp() throws Exception {
        LocalDate validade = LocalDate.of(2017, 12, 12);
        convenioComValidade = new Convenio("Convenio Teste", "12345678", validade, false);
    }



}
