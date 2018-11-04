package cn.bdqn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import cn.bdqn.ssm.pojo.User;

public interface UserMapper {
	// 根据用户Code查找用户
	public User getUser(@Param("ucode") String ucode);
	
	//查找总记录数
	public int getCount(@Param("uname")String username,@Param("rid")Integer userRole);
	
	//分页列表
	public List<User> getUserList(@Param("uname")String username,@Param("rid")Integer userRole
									,@Param("from")Integer from,@Param("pageSize")Integer pageSize);
	
	/**
	 * 增加用户信息
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(User user);
	
	/**
	 * 通过userId获取user
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserById(@Param("id")String id); 
	
	/**
	 * 修改用户信息
	 * @param connection
	 * @param user
	 * @return
	 */
	public int modify(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public  int delUser(@Param("id")String id);
}
