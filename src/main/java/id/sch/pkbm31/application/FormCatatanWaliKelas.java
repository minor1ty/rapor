package id.sch.pkbm31.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FormCatatanWaliKelas {
	//Declare LEGER_NILAI combobox items
	private StringProperty pesertaDidik;
	
	//Constructor
	public FormCatatanWaliKelas() {
		this.pesertaDidik = new SimpleStringProperty();
	}
	
	//PesertaDidik
	public String getPesertaDidik() {
		return pesertaDidik.get();
	}
	public void setPesertaDidik(String pesertaDidik){
		this.pesertaDidik.set(pesertaDidik);
	}
	public StringProperty pesertaDidikProperty() {
		return pesertaDidik;
	}
	
	@Override
	public String toString() {
		return this.getPesertaDidik();
	}
}
