package koier;

// TODO: Auto-generated Javadoc
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
	
	/**
	 * Gets the bruker id.
	 *
	 * @return the bruker id
	 */
	public int getBrukerID() {
		return brukerID;
	}

	/**
	 * Gets the fornavn.
	 *
	 * @return the fornavn
	 */
	public String getFornavn() {
		return fornavn;
	}

	/**
	 * Gets the etternavn.
	 *
	 * @return the etternavn
	 */
	public String getEtternavn() {
		return etternavn;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Gets the mobilnr.
	 *
	 * @return the mobilnr
	 */
	public int getMobilnr() {
		return mobilnr;
	}

	/**
	 * Gets the permission.
	 *
	 * @return the permission
	 */
	public int getPermission() {
		return permission;
	}

	/**
	 * Sets the permission.
	 *
	 * @param permission the new permission
	 */
	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	
	/** The bruker id. */
	public int brukerID;
	
	/** The fornavn. */
	public String fornavn;
	
	/** The etternavn. */
	public String etternavn;
	
	/** The mail. */
	public String mail;
	
	/** The mobilnr. */
	public int mobilnr;
	
	/** The permission. */
	public int permission;
	
	/** The brukernavn. */
	public String brukernavn;
	
	/** The passord. */
	public String passord;
	
	/**
	 * Constructor for Class bruker.
	 *
	 * @param brukerID the bruker id
	 * @param fornavn the fornavn
	 * @param etternavn the etternavn
	 * @param mail the mail
	 * @param mobilnr the mobilnr
	 * @param permission the permission
	 * @param brukernavn the brukernavn
	 * @param passord the passord
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
	
	/**
	 * Gets the bruker.
	 *
	 * @return the bruker
	 */
	public String getbruker(){
		return this.brukernavn;
	}
	
	/**
	 * Gets the passord.
	 *
	 * @return the passord
	 */
	public String getpassord(){
		return this.passord;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "bruker: " + brukernavn + "\n" + "passord: " + passord;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Bruker bruker1 = new Bruker(78543,"marian", "aspas", "mariaasp@stud.ntnu.no", 98635269, 1 ,"mariaasp", "passord");
		System.out.println(bruker1);
	}
	
}
