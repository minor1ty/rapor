package id.sch.pkbm31.application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LegerNilai {
		//Declare PESERTA_DIDIK table columns
		private IntegerProperty tableId;
		private StringProperty kodeMapel;
		private StringProperty nisn;
		private IntegerProperty ganjil;
		private IntegerProperty genap;
		private StringProperty namaLengkap;
		private StringProperty namaMapel;
		
		//Constructor
		public LegerNilai() {
			this.tableId = new SimpleIntegerProperty();
			this.kodeMapel = new SimpleStringProperty();
			this.nisn = new SimpleStringProperty();
			this.ganjil = new SimpleIntegerProperty();
			this.genap = new SimpleIntegerProperty();
			this.namaLengkap = new SimpleStringProperty();
			this.namaMapel = new SimpleStringProperty();
		}
		
		//TableId
		public int getTableId() {
	        return tableId.get();
	    }
	    public void setTableId(int tableId){
	        this.tableId.set(tableId);
	    }
	    public IntegerProperty tableIdProperty() {
	        return tableId;
	    }
	    
	    //KodeMapel
		public String getKodeMapel() {
	        return kodeMapel.get();
	    }
	    public void setKodeMapel(String kodeMapel){
	        this.kodeMapel.set(kodeMapel);
	    }
	    public StringProperty kodeMapelProperty() {
	        return kodeMapel;
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
	    
	    //Ganjil
		public int getGanjil() {
	        return ganjil.get();
	    }
	    public void setGanjil(int ganjil){
	        this.ganjil.set(ganjil);
	    }
	    public IntegerProperty ganjilProperty() {
	        return ganjil;
	    }
	    
	    //Genap
		public int getGenap() {
	        return genap.get();
	    }
	    public void setGenap(int genap){
	        this.genap.set(genap);
	    }
	    public IntegerProperty genapProperty() {
	        return genap;
	    }
	    
	    //NamaLengkap
		public String getNamaLengkap() {
	        return namaLengkap.get();
	    }
	    public void setNamaLengkap(String namaLengkap){
	        this.namaLengkap.set(namaLengkap);
	    }
	    public StringProperty namaLengkapProperty() {
	        return namaLengkap;
	    }
	    
	    //NamaMapel
		public String getNamaMapel() {
	        return namaMapel.get();
	    }
	    public void setNamaMapel(String namaMapel){
	        this.namaMapel.set(namaMapel);
	    }
	    public StringProperty namaMapelProperty() {
	        return namaMapel;
	    }
}
