package id.sch.pkbm31.application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MataPelajaran {
	
	//Declare MATA_PELAJARAN table columns
	private IntegerProperty tableId;
	private StringProperty kodeMapel;
	private StringProperty namaMapel;
	private StringProperty pengetahuanKkm;
	private StringProperty pengetahuanTidakKkm;
	private StringProperty keterampilanKkm;
	private StringProperty keterampilanTidakKkm;
	
	//Constructor
	public MataPelajaran() {
		this.tableId = new SimpleIntegerProperty();
		this.kodeMapel = new SimpleStringProperty();
		this.namaMapel = new SimpleStringProperty();
		this.pengetahuanKkm = new SimpleStringProperty();
		this.pengetahuanTidakKkm = new SimpleStringProperty();
		this.keterampilanKkm = new SimpleStringProperty();
		this.keterampilanTidakKkm = new SimpleStringProperty();
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
    
    //PengetahuanKkm
	public String getPengetahuanKkm() {
        return pengetahuanKkm.get();
    }
    public void setPengetahuanKkm(String pengetahuanKkm){
        this.pengetahuanKkm.set(pengetahuanKkm);
    }
    public StringProperty pengetahuanKkmProperty() {
        return pengetahuanKkm;
    }
    
    //PengetahuanTidakKkm
	public String getPengetahuanTidakKkm() {
        return pengetahuanTidakKkm.get();
    }
    public void setPengetahuanTidakKkm(String pengetahuanTidakKkm){
        this.pengetahuanTidakKkm.set(pengetahuanTidakKkm);
    }
    public StringProperty pengetahuanTidakKkmProperty() {
        return pengetahuanTidakKkm;
    }
    
    //KeterampilanKkm
	public String getKeterampilanKkm() {
        return keterampilanKkm.get();
    }
    public void setKeterampilanKkm(String keterampilanKkm){
        this.keterampilanKkm.set(keterampilanKkm);
    }
    public StringProperty keterampilanKkmProperty() {
        return keterampilanKkm;
    }
    
    //KeterampilanTidakKkm
	public String getKeterampilanTidakKkm() {
        return keterampilanTidakKkm.get();
    }
    public void setKeterampilanTidakKkm(String keterampilanTidakKkm){
        this.keterampilanTidakKkm.set(keterampilanTidakKkm);
    }
    public StringProperty keterampilanTidakKkmProperty() {
        return keterampilanTidakKkm;
    }
}
