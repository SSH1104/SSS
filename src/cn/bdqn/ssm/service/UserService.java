package cn.bdqn.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import cn.bdqn.ssm.pojo.User;

public interface UserService {
	// 根据用户Code查找用户
	public User getUser(String ucode, String upwd);

	// 查找总记录数
	public int getCount(String username, Integer userRole);

	// 分页列表
	public List<User> getUserList(String username, Integer userRole,
			Integer from, Integer pageSize);
	
	/**
	 * 增加用户信息
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean add(User user);
	
	/**
	 * 通过userId获取user
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserById(String id); 
	
	/**
	 * 修改用户信息
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean modify(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public boolean delUser(String id);
}
