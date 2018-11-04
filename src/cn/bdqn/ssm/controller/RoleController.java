package cn.bdqn.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.bdqn.ssm.pojo.Role;
import cn.bdqn.ssm.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private RoleService roleService;

	@RequestMapping("/list.html")
	public String getList(Model model) {
		List<Role> rolelist = roleService.getRoleList();
		model.addAttribute("roleList", rolelist);
		return "rolelist";
	}

	// json 显示用户角色列表
	@RequestMapping(value = "/roleList.html")
	@ResponseBody
	public List<Role> showRoleList() {

		List<Role> list = new ArrayList<Role>();
		try {
			list = roleService.getRole();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSONArray.toJSONString(list));
		return list;
	}
}
