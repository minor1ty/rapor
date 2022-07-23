package id.sch.pkbm31.application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PesertaDidik {
	
	//Declare PESERTA_DIDIK table columns
	private IntegerProperty tableId;
	private StringProperty nisn;
	private StringProperty namaLengkap;
	private StringProperty jenisKelamin;
	private StringProperty agama;
	
	//Constructor
	public PesertaDidik() {
		this.tableId = new SimpleIntegerProperty();
		this.nisn = new SimpleStringProperty();
		this.namaLengkap = new SimpleStringProperty();
		this.jenisKelamin = new SimpleStringProperty();
		this.agama = new SimpleStringProperty();
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
    
    //JenisKelamin
	public String getJenisKelamin() {
        return jenisKelamin.get();
    }
    public void setJenisKelamin(String jenisKelamin){
        this.jenisKelamin.set(jenisKelamin);
    }
    public StringProperty jenisKelaminProperty() {
        return jenisKelamin;
    }
    
    //Agama
	public String getAgama() {
        return agama.get();
    }
    public void setAgama(String agama){
        this.agama.set(agama);
    }
    public StringProperty agamaProperty() {
        return agama;
    }
}
