package cn.bdqn.ssm.mapper;

import java.util.List;

import cn.bdqn.ssm.pojo.Role;

public interface RoleMapper {

	//查询角色名
	public List<Role> getRole();
	
	
	//角色列表
	public List<Role> getRoleList();
}
