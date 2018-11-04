package cn.bdqn.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.ssm.mapper.RoleMapper;
import cn.bdqn.ssm.pojo.Role;
import cn.bdqn.ssm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;
	
	public List<Role> getRole() {
		return roleMapper.getRole();
	}

	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

}
