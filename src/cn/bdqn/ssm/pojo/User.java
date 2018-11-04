package cn.bdqn.ssm.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	private int id; // id
	private String userCode; // 用户编号
	private String userName;// 用户名称
	private String userPassword;// 用户密码
	private int gender;// 性别
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;// 生日
	private String phone;// 电话
	private String address;// 地址
	private int userRole;// 用户角色
	private int createdBy;// 创建�?
	private Date creationDate;// 创建日期
	private int modifyBy;// 修改者id
	private Date modifyDate;// 修改时间

	private int age; // 年龄

	private String idPicPath;// 文件位置
	private String workPicPath;

	private String userRoleName;// 角色名称

	public String getWorkPicPath() {
		return workPicPath;
	}

	public void setWorkPicPath(String workPicPath) {
		this.workPicPath = workPicPath;
	}

	public User() {
		super();
	}

	public User(int id, String userCode, String userName, String userPassword,
			int gender, Date birthday, String phone, String address,
			int userRole, int createdBy, Date creationDate, int modifyBy,
			Date modifyDate, int age, String idPicPath, String workPicPath,
			String userRoleName) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userRole = userRole;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.age = age;
		this.idPicPath = idPicPath;
		this.workPicPath = workPicPath;
		this.userRoleName = userRoleName;
	}

	public String getAddress() {
		return address;
	}

	public int getAge() {
		Date date = new Date();
		age = date.getYear() - (getBirthday().getYear());
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public int getGender() {
		return gender;
	}

	public int getId() {
		return id;
	}

	public String getIdPicPath() {
		return idPicPath;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public String getPhone() {
		return phone;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public int getUserRole() {
		return userRole;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdPicPath(String idPicPath) {
		this.idPicPath = idPicPath;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

}
