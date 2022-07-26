package id.sch.pkbm31.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CatatanWaliKelas {
	//Declare PESERTA_DIDIK table columns
	private StringProperty nisn;
	private StringProperty namaPesertaDidik;
	private StringProperty catatan;
	
	//Constructor
	public CatatanWaliKelas() {
		this.nisn = new SimpleStringProperty();
		this.namaPesertaDidik = new SimpleStringProperty();
		this.catatan = new SimpleStringProperty();
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
	
	//Catatan
	public String getCatatan() {
	    return namaPesertaDidik.get();
	}
	public void setCatatan(String catatan){
	    this.catatan.set(catatan);
	}
	public StringProperty catatanProperty() {
	    return catatan;
	}
}
