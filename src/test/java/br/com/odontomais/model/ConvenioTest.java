package br.com.odontomais.model;

import odontomais.model.Convenio;
import org.junit.Assert;

import java.time.LocalDate;

public class ConvenioTest {
    Convenio convenioComValidade;

    @org.junit.Before
    public void setUp() throws Exception {
        LocalDate validade = LocalDate.of(2017, 12, 12);
        convenioComValidade = new Convenio("Convenio Teste", "12345678", validade, false);
    }

    @org.junit.Test
    public void testaValidadeOK() throws Exception {
        boolean resultado = convenioComValidade.testaValidade(LocalDate.of(2017, 10, 10));
        Assert.assertEquals(true, resultado);
    }

    @org.junit.Test
    public void testaValidadeExpirado() throws Exception {
        boolean resultado = convenioComValidade.testaValidade(LocalDate.of(2018, 10, 10));
        Assert.assertEquals(false, resultado);
    }

    @org.junit.Test
    public void testaValidadeInexistente() throws Exception {
        Convenio c = new Convenio("Sem Validade", "12345667", LocalDate.now(), true);
        boolean resultado = convenioComValidade.testaValidade(LocalDate.of(2017, 10, 10));
        Assert.assertEquals(true, resultado);
    }




}
