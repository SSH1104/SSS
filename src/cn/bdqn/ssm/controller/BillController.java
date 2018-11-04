package cn.bdqn.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bdqn.ssm.pojo.Bill;
import cn.bdqn.ssm.pojo.Provider;
import cn.bdqn.ssm.service.BillService;
import cn.bdqn.ssm.service.ProviderService;
import cn.bdqn.ssm.util.PageSupport;

@Controller
@RequestMapping("/sys")
public class BillController {
	@Resource
	private BillService billService;
	@Resource
	private ProviderService providerService;

	@RequestMapping("/bill/list.html")
	public String getList(
			@RequestParam(value = "queryProductName", required = false) String queryProductName,
			@RequestParam(value = "queryProviderId", required = false) String queryProviderId,
			@RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			Model model) {
		// 准备数据
		// 订单 名
		if (queryProductName == null) {
			queryProductName = "";
		}
		// 供应商
		int pid=0;
		if (queryProviderId !=null && !queryProviderId.equals("")) {
			pid=Integer.parseInt(queryProviderId);
		}
		//是否付款
		int ispay=0;
		if (queryIsPayment !=null && !queryIsPayment.equals("")) {
			ispay=Integer.parseInt(queryIsPayment);
		}
		// 当前页
		int currentPageNo = 1;
		if (pageIndex != null) {
			currentPageNo = Integer.parseInt(pageIndex);
		}

		// 页面容量
		int pageSize = 5;
		// 总量
		int totalCount = billService.getCount(pid, queryProductName,ispay);
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
		int index = (currentPageNo - 1) * pageSize;
		// 供应商列表
		List<Provider> providerList = providerService.getProList();
		model.addAttribute("providerList", providerList);
		//订单列表
		List<Bill> billList = billService.getBillList(pid, queryProductName, ispay, index, pageSize);
		model.addAttribute("billList", billList);
		
		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);

		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", pid);
		model.addAttribute("queryIsPayment", ispay);

		return "billlist";
	}
}
