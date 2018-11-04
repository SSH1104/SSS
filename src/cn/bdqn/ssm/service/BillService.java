package cn.bdqn.ssm.service;

import java.util.List;

import cn.bdqn.ssm.pojo.Bill;

public interface BillService {

	// 查找总记录数
	public int getCount(Integer pid, String name, Integer ispay);

	// 分页列表
	public List<Bill> getBillList(Integer pid, String name, Integer ispay,
			Integer from, Integer pageSize);
}
