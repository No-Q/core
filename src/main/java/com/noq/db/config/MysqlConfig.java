package com.noq.db.config;

public class MysqlConfig {

	private String mysqlUserName;
	private String mysqlPassword;
	private String mysqlUrl;

	public MysqlConfig(String mysqlUserName, String mysqlPassword, String mysqlUrl) {
		super();
		this.mysqlUserName = mysqlUserName;
		this.mysqlPassword = mysqlPassword;
		this.mysqlUrl = mysqlUrl;
	}

	public String getMysqlUserName() {
		return mysqlUserName;
	}

	public String getMysqlPassword() {
		return mysqlPassword;
	}

	public String getMysqlUrl() {
		return mysqlUrl;
	}

}
