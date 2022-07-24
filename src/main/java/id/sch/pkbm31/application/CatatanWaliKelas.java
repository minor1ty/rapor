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
}
