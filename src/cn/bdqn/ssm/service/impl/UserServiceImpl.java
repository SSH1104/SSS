package cn.bdqn.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.ssm.mapper.UserMapper;
import cn.bdqn.ssm.pojo.User;
import cn.bdqn.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	public User getUser(String ucode, String upwd) {
		User user = userMapper.getUser(ucode);
		if (user != null) {
			if (user.getUserPassword().equals(upwd)) {
				return user;
			}
		}
		return null;
	}

	public int getCount(String username, Integer userRole) {
		return userMapper.getCount(username, userRole);
	}

	public List<User> getUserList(String username, Integer userRole,
			Integer from, Integer pageSize) {
		return userMapper.getUserList(username, userRole, from, pageSize);
	}

	public boolean add(User user) {
		int flag = userMapper.add(user);
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	public User getUserById(String id) {
		return userMapper.getUserById(id);
	}

	public boolean modify(User user) {
		int flag = userMapper.add(user);
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delUser(String id) {
		int flag = userMapper.delUser(id);
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

}
