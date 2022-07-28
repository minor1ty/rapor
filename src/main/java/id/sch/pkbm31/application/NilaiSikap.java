package id.sch.pkbm31.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NilaiSikap {
	private StringProperty nisn;
	private StringProperty namaPesertaDidik;
	private StringProperty nilaiSpiritual;
	private StringProperty nilaiSosial;
	
	public NilaiSikap() {
		this.nisn = new SimpleStringProperty();
		this.namaPesertaDidik = new SimpleStringProperty();
		this.nilaiSpiritual = new SimpleStringProperty();
		this.nilaiSosial = new SimpleStringProperty();
	}
	
	//NISN
	public String getNisn() {
	    return nisn.get();
	}
	public void setNisn(String nisn){
	    this.nisn.set(nisn);
	}
	public StringProperty nisnProperty() {
	    return nisn;
	}
	
	//NamaPesertaDidik
	public String getNamaPesertaDidik() {
	    return namaPesertaDidik.get();
	}
	public void setNamaPesertaDidik(String namaPesertaDidik){
	    this.namaPesertaDidik.set(namaPesertaDidik);
	}
	public StringProperty namaPesertaDidikProperty() {
	    return namaPesertaDidik;
	}
	
	//NilaiSpiritual
	public String getNilaiSpiritual() {
	    return nilaiSpiritual.get();
	}
	public void setNilaiSpiritual(String nilaiSpiritual){
	    this.nilaiSpiritual.set(nilaiSpiritual);
	}
	public StringProperty nilaiSpiritualProperty() {
	    return nilaiSpiritual;
	}
	
	//NilaiSosial
	public String getNilaiSosial() {
	    return nilaiSosial.get();
	}
	public void setNilaiSosial(String nilaiSosial){
	    this.nilaiSosial.set(nilaiSosial);
	}
	public StringProperty nilaiSosialProperty() {
	    return nilaiSosial;
	}
}
