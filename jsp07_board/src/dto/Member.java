package dto;

import java.sql.Date;

public class Member {
	private String email;
	private String passwd;
	private String salt;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String filename;
	private Date regedate;
	
	public Member() {
		super();
	}

	public Member(String email, String passwd, String salt, String zipcode, String addr, String addrdetail,
			String filename, Date regedate) {
		super();
		this.email = email;
		this.passwd = passwd;
		this.salt = salt;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrdetail = addrdetail;
		this.filename = filename;
		this.regedate = regedate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrdetail() {
		return addrdetail;
	}

	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getRegedate() {
		return regedate;
	}

	public void setRegedate(Date regedate) {
		this.regedate = regedate;
	}

	@Override
	public String toString() {
		return "Member [email=" + email + ", passwd=" + passwd + ", salt=" + salt + ", zipcode=" + zipcode + ", addr="
				+ addr + ", addrdetail=" + addrdetail + ", filename=" + filename + ", regedate=" + regedate + "]";
	}

}
