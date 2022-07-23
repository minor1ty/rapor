package id.sch.pkbm31.application;

public class AkunPengguna {
	
	//Declare AKUN_PENGGUNA table columns
	private String jenisPengguna;
	private String namaLengkap;
	private String namaPengguna;
	private String kataSandi;
	private String pertanyaanKeamanan;
	private String jawabanKeamanan;
	
	public AkunPengguna(String jenisPengguna, String namaLengkap, String namaPengguna, String kataSandi,
			String pertanyaanKeamanan, String jawabanKeamanan) {
		super();
		this.jenisPengguna = jenisPengguna;
		this.namaLengkap = namaLengkap;
		this.namaPengguna = namaPengguna;
		this.kataSandi = kataSandi;
		this.pertanyaanKeamanan = pertanyaanKeamanan;
		this.jawabanKeamanan = jawabanKeamanan;
	}

	public String getJenisPengguna() {
		return jenisPengguna;
	}

	public void setJenisPengguna(String jenisPengguna) {
		this.jenisPengguna = jenisPengguna;
	}

	public String getNamaLengkap() {
		return namaLengkap;
	}

	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}

	public String getNamaPengguna() {
		return namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	public String getKataSandi() {
		return kataSandi;
	}

	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}

	public String getPertanyaanKeamanan() {
		return pertanyaanKeamanan;
	}

	public void setPertanyaanKeamanan(String pertanyaanKeamanan) {
		this.pertanyaanKeamanan = pertanyaanKeamanan;
	}

	public String getJawabanKeamanan() {
		return jawabanKeamanan;
	}

	public void setJawabanKeamanan(String jawabanKeamanan) {
		this.jawabanKeamanan = jawabanKeamanan;
	}
	
    
}
