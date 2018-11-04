package cn.bdqn.ssm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bdqn.ssm.pojo.User;
import cn.bdqn.ssm.service.UserService;
import cn.bdqn.ssm.util.Constants;

@Controller
public class LoginController {
	@Resource
	private UserService userService;

	// 登录校验
	@RequestMapping(value = "/user/login.html")
	public String login(@RequestParam(value = "userCode") String userCode,
			@RequestParam(value = "userPassword") String userPassword,
			HttpSession session, HttpServletRequest request) {
		User user = userService.getUser(userCode, userPassword);
		if (user != null) {

			// 将用户名放在会话中
			session.setAttribute(Constants.USER_SESSION, user);
			return "redirect:/user/main.html"; // 登录成功后跳转到主页
		}else {
			/*// 将错误信息放在request域中 并返回登录页面
			request.setAttribute("error", "用户名和密码不匹配！");*/
			throw new RuntimeException("用户名和密码不匹配！");
		}
		
	}

	// 访问主页
	@RequestMapping("/user/main.html")
	public String frame() {
		return "frame";
	}

	// 退出登录
	@RequestMapping("/user/loginout.html")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
}
