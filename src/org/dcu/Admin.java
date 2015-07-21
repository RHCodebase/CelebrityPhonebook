package org.dcu;

public class Admin
{
	private int       adminId; 
	private String    firstname;
    private String    surname;
    private String    username;
    private String    passwordHash;
    private boolean   canAddStaffDetails;
    private boolean   canEditStaffDetails;
    private boolean   canRemoveStaffDetails;
    
    public Admin(int adminId, String firstname, String surname, String username, String passwordHash, boolean canAddStaffDetails, boolean canEditStaffDetails, boolean canRemoveStaffDetails)
    {
    	// super();
    	this.adminId                =  adminId;
		this.firstname              =  firstname;
		this.surname                =  surname;
		this.username               =  username;
		this.passwordHash           =  passwordHash;
		this.canAddStaffDetails     =  canAddStaffDetails;
		this.canEditStaffDetails    =  canEditStaffDetails;
		this.canRemoveStaffDetails  =  canRemoveStaffDetails;
	}
    
    public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password) {
		this.passwordHash = passwordHash;
	}

	public boolean isCanAddStaffDetails() {
		return canAddStaffDetails;
	}

	public void setCanAddStaffDetails(boolean canAddStaffDetails) {
		this.canAddStaffDetails = canAddStaffDetails;
	}

	public boolean isCanEditStaffDetails() {
		return canEditStaffDetails;
	}

	public void setCanEditStaffDetails(boolean canEditStaffDetails) {
		this.canEditStaffDetails = canEditStaffDetails;
	}

	public boolean isCanRemoveStaffDetails() {
		return canRemoveStaffDetails;
	}

	public void setCanRemoveStaffDetails(boolean canRemoveStaffDetails) {
		this.canRemoveStaffDetails = canRemoveStaffDetails;
	}

}


/*
CREATE TABLE IF NOT EXISTS `administratorTable` 
(
    `adminId`                int(11)       NOT NULL AUTO_INCREMENT,
    `firstName`              varchar(30)   DEFAULT NULL,
    `surName`                varchar(30)   DEFAULT NULL,
    `userName`               varchar(30)   DEFAULT NULL,
    `passWordHash`           varchar(300)  DEFAULT NULL,
    `canAddStaffDetails`     varchar(30)   DEFAULT NULL,
    `canEditStaffDetails`    varchar(30)   DEFAULT NULL,
    `canRemoveStaffDetails`  varchar(30)   DEFAULT NULL,
     
     PRIMARY KEY (`adminId`)
)
*/