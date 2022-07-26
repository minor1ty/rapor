package id.sch.pkbm31.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FormLegerNilai {
	//Declare LEGER_NILAI combobox items
	private StringProperty pesertaDidik;
	private StringProperty mataPelajaran;
	
	//Constructor
	public FormLegerNilai() {
		this.pesertaDidik = new SimpleStringProperty();
		this.mataPelajaran = new SimpleStringProperty();
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
	
	
	//MataPelajaran
	public String getMataPelajaran() {
		return mataPelajaran.get();
	}
	public void setMataPelajaran(String mataPelajaran){
		this.mataPelajaran.set(mataPelajaran);
	}
	public StringProperty mataPelajaranProperty() {
		return mataPelajaran;
	}

	@Override
	public String toString() {
		return this.getPesertaDidik();
	}
	
	
	
}
