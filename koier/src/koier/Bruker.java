package koier;

/**
 * Class that contains information of the active user.
 * 
 * 
 * Contains the following information: brukerID, name, surname, emial, phone number, permission, username and password.
 * 
 * @author Marian og Emil
 *
 */
public class Bruker {
	
	public int getBrukerID() {
		return brukerID;
	}

	public String getFornavn() {
		return fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getMail() {
		return mail;
	}

	public int getMobilnr() {
		return mobilnr;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
	public int brukerID;
	public String fornavn;
	public String etternavn;
	public String mail;
	public int mobilnr;
	public int permission;
	public String brukernavn;
	public String passord;
	
	/**
	 * 
	 * Constructor for Class bruker
	 * 
	 * @param brukerID
	 * @param fornavn
	 * @param etternavn
	 * @param mail
	 * @param mobilnr
	 * @param permission
	 * @param brukernavn
	 * @param passord
	 */
	
	public Bruker(int brukerID, String fornavn, String etternavn, String mail, int mobilnr, int permission, String brukernavn, String passord){
		this.brukerID = brukerID;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mail = mail;
		this.mobilnr = mobilnr;
		this.permission = permission;
		this.brukernavn = brukernavn;
		this.passord = passord;
	}
	
	public String getbruker(){
		return this.brukernavn;
	}
	public String getpassord(){
		return this.passord;
	}
	
	public String toString() {
		return "bruker: " + brukernavn + "\n" + "passord: " + passord;
	}
	public static void main(String[] args) {
		Bruker bruker1 = new Bruker(78543,"marian", "aspas", "mariaasp@stud.ntnu.no", 98635269, 1 ,"mariaasp", "passord");
		System.out.println(bruker1);
	}
	
}
