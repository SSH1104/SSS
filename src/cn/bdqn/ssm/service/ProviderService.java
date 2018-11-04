package cn.bdqn.ssm.service;

import java.util.List;

import cn.bdqn.ssm.pojo.Provider;
import cn.bdqn.ssm.pojo.User;

public interface ProviderService {

	// 查找总记录数
	public int getCount(String code, String name);

	// 分页列表
	public List<Provider> getProviderList(String code, String name,
			Integer from, Integer pageSize);

	// 查找列表不分页
	public List<Provider> getProList();

	//增加
	public boolean add(Provider pro);

	//根据ID获取信息
	public Provider getProviderById(String id);

	//修改
	public boolean modify(Provider pro);
}
