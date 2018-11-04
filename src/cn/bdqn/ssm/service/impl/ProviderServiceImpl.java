package cn.bdqn.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.ssm.mapper.ProviderMapper;
import cn.bdqn.ssm.pojo.Provider;
import cn.bdqn.ssm.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Resource
	private ProviderMapper providerMapper;

	public int getCount(String code, String name) {
		return providerMapper.getCount(code, name);
	}

	public List<Provider> getProviderList(String code, String name,
			Integer from, Integer pageSize) {
		return providerMapper.getProviderList(code, name, from, pageSize);
	}

	public List<Provider> getProList() {
		return providerMapper.getProList();
	}

	public boolean add(Provider pro) {
		int flag = providerMapper.insert(pro);
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Provider getProviderById(String id) {
		return providerMapper.getProviderById(id);
	}

	public boolean modify(Provider pro) {
		int flag = providerMapper.modify(pro);
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}

}
