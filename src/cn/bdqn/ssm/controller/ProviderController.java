package cn.bdqn.ssm.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bdqn.ssm.pojo.Provider;
import cn.bdqn.ssm.pojo.User;
import cn.bdqn.ssm.service.ProviderService;
import cn.bdqn.ssm.util.Constants;
import cn.bdqn.ssm.util.PageSupport;

@Controller
@RequestMapping("/sys")
public class ProviderController {
	@Resource
	private ProviderService providerService;

	@RequestMapping("/provider/list.html")
	public String getList(
			@RequestParam(value = "queryProCode", required = false) String queryProCode,
			@RequestParam(value = "queryProName", required = false) String queryProName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			Model model) {
		// 准备数据
		// 供应商编码
		if (queryProCode == null) {
			queryProCode = "";
		}
		// 供应商姓名
		if (queryProName == null) {
			queryProName = "";
		}
		// 当前页
		int currentPageNo = 1;
		if (pageIndex != null) {
			currentPageNo = Integer.parseInt(pageIndex);
		}

		// 页面容量
		int pageSize = 5;
		// 总量
		int totalCount = providerService.getCount(queryProCode, queryProName);
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
		// 列表
		int index = (currentPageNo - 1) * pageSize;
		List<Provider> providerList = providerService.getProviderList(
				queryProCode, queryProName, index, pageSize);
		model.addAttribute("providerList", providerList);

		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);

		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("queryProName", queryProName);

		return "providerlist";
	}

	@RequestMapping(value = "/proadd.html", method = RequestMethod.GET)
	public String toAddProvider(@ModelAttribute("provider") Provider pro) {
		return "provideradd";
	}

	@RequestMapping(value = "/addSave.html", method = RequestMethod.POST)
	public String addProvider(Provider pro, HttpSession session) {
		User admin = (User) session.getAttribute(Constants.USER_SESSION);// 从会话中获取管理员对象
		pro.setCreatedBy(admin.getId());
		pro.setCreationDate(new Date());
		if (providerService.add(pro)) {
			 return "redirect:/sys/provider/list.html";
		}
		return "provideradd";
	}

	// 进入修改页面 将用户信息显示
	@RequestMapping(value = "/toModifyProvider.html")
	public String toModifyProvider(String pid, Model model) {
		Provider pro = providerService.getProviderById(pid);// 根据id获取信息
		model.addAttribute("provider", pro);
		return "providermodify";
	}

	// 修改操作
	@RequestMapping(value = "/modifyProvider.html")
	public String modifyProvider(Provider pro, HttpSession session) {
		User admin = (User) session.getAttribute(Constants.USER_SESSION);// 从会话中获取管理员对象
		pro.setModifyBy(admin.getId());
		pro.setModifyDate(new Date());
		if (providerService.modify(pro)) {
			return "redirect:/sys/provider/list.html";
		}
		return "providermodify";
	}

	// 用rest风格 查看
	@RequestMapping(value = "/view/{id}")
	public String viewUser(@PathVariable String id, Model model) {
		Provider pro = providerService.getProviderById(id);// 根据id获取信息
		model.addAttribute("provider", pro);
		return "providerview";
	}

}
