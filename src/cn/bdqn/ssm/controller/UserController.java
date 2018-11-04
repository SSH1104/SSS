package cn.bdqn.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bdqn.ssm.pojo.Role;
import cn.bdqn.ssm.pojo.User;
import cn.bdqn.ssm.service.RoleService;
import cn.bdqn.ssm.service.UserService;
import cn.bdqn.ssm.util.Constants;
import cn.bdqn.ssm.util.PageSupport;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	@RequestMapping("/userList.html")
	public String getList(
			@RequestParam(value = "queryname", required = false) String queryname,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			Model model) {
		// 准备数据
		// 用户名
		if (queryname == null) {
			queryname = "";
		}
		// 角色id
		int roleid = 0;
		if (queryUserRole != null && !queryUserRole.equals("")) {
			roleid = Integer.parseInt(queryUserRole);
		}
		// 当前页
		int currentPageNo = 1;
		if (pageIndex != null) {
			currentPageNo = Integer.parseInt(pageIndex);
		}

		// 页面容量
		int pageSize = 5;
		// 总量
		int totalCount = userService.getCount(queryname, roleid);
		// 总页数
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentPageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();

		// 控制首页和尾页
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		// 用户列表
		int index = (currentPageNo - 1) * pageSize;
		List<User> userList = userService.getUserList(queryname, roleid, index,
				pageSize);
		model.addAttribute("userList", userList);
		// 角色列表
		List<Role> roleList = roleService.getRole();
		model.addAttribute("roleList", roleList);

		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);

		model.addAttribute("queryUserName", queryname);
		model.addAttribute("queryUserRole", roleid);

		return "userlist";
	}

	@RequestMapping(value = "/useradd.html", method = RequestMethod.GET)
	public String addUser(@ModelAttribute("user") User user) {
		return "useradd";
	}

	// 新增用户 多文件上传
	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addUserSave(
			User user,
			HttpSession session,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs,
			HttpServletRequest request) {

		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uplodefiles");
		System.out.println(attachs.length
				+ "==================================");
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];

			// 判断文件是否为空
			if (!attach.isEmpty()) {
				String oldFileName = attach.getOriginalFilename();// 原文件名
				String prefix = FilenameUtils.getExtension(oldFileName);// 源文件后缀
				int filesize = 5000000;
				if (attach.getSize() > filesize) {// 上传大小不得超过500k
					request.setAttribute("uploadFileError", "上传大小不得超过500k");
					return "useradd";
				} else if (prefix.equalsIgnoreCase("jpg")
						|| prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg")
						|| prefix.equalsIgnoreCase("pneg")) {// 上传文件格式不正确
					String filename = System.currentTimeMillis()
							+ RandomUtils.nextInt(1000000) + "_Personal.jpg";
					File targetFile = new File(path, filename);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("uploadFileError", "上传失败");
						flag = false;
					}
					if (i == 0) {
						idPicPath = path + File.separator + filename;
					} else if (i == 1) {
						workPicPath = path + File.separator + filename;
					}
				} else {
					request.setAttribute(errorInfo, "上传图片格式不正确");
					flag = false;
				}

			}
		}
		if (flag) {
			user.setCreatedBy(((User) session
					.getAttribute(Constants.USER_SESSION)).getId());
			user.setCreationDate(new Date());
			user.setIdPicPath(idPicPath);
			user.setWorkPicPath(workPicPath);
			if (userService.add(user)) {
				return "redirect:/user/userList.html";
			}
		}
		return "useradd";
	}

	// 进入修改页面 将用户信息显示
	@RequestMapping(value = "/toModifyUser.html")
	public String toModifyUser(String uid, Model model) {
		User user = userService.getUserById(uid);// 根据用户id获取用户信息
		model.addAttribute("user", user);
		return "usermodify";
	}

	// 修改操作
	@RequestMapping(value = "/modifyUser.html")
	public String modifyUser(User user, HttpSession session) {
		User admin = (User) session.getAttribute(Constants.USER_SESSION);// 从会话中获取管理员对象
		user.setModifyBy(admin.getId());
		user.setModifyDate(new Date());
		if (userService.modify(user)) {
			return "redirect:/user/userList.html";
		}
		return "usermodify";
	}

	// 用rest风格 查看
	@RequestMapping(value = "/view/{uid}")
	public String viewUser(@PathVariable String uid, Model model) {
		User user = userService.getUserById(uid);// 根据用户id获取用户信息

		model.addAttribute("user", user);
		return "userview";
	}

	

	// 删除用户
	@RequestMapping(value = "/delUser.html")
	@ResponseBody
	public Object delUser(@RequestParam String uid) {
		if (uid == null || uid.equals("")) {
			return "notexist"; // 表示没有数据
		} else {
			boolean flag = userService.delUser(uid);
			if (!flag) {
				return "false";
			} else {
				return "true";
			}
		}
	}
}
