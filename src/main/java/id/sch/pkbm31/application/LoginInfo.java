package id.sch.pkbm31.application;

public class LoginInfo {
	private String namaPengguna;
	private String kataSandi;
	
	public LoginInfo(String namaPengguna, String kataSandi) {
		super();
		this.namaPengguna = namaPengguna;
		this.kataSandi = kataSandi;
	}

	/**
	 * @return the namaPengguna
	 */
	public String getNamaPengguna() {
		return namaPengguna;
	}

	/**
	 * @param namaPengguna the namaPengguna to set
	 */
	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	/**
	 * @return the kataSandi
	 */
	public String getKataSandi() {
		return kataSandi;
	}

	/**
	 * @param kataSandi the kataSandi to set
	 */
	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}
	
	
}
