package com.example.demo.models;

public class ChangePassModel {
	
	private String oldpassword;
	private String newpassword;
	
	public ChangePassModel(String oldpassword,String newpassword) {
		this.oldpassword=oldpassword;
		this.newpassword=newpassword;
	}
	public ChangePassModel() {
		
	}

	public void setOldpassword1(String oldpassword1) {
		this.oldpassword = oldpassword1;
	}



	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getOldpassword() {
		return oldpassword;
	}


	public String getNewpassword() {
		return newpassword;
	}
	
}
