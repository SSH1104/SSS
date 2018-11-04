package cn.bdqn.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.ssm.mapper.BillMapper;
import cn.bdqn.ssm.pojo.Bill;
import cn.bdqn.ssm.service.BillService;

@Service
public class BillServiceImpl implements BillService {

	@Resource
	private BillMapper billMapper;

	public int getCount(Integer pid, String name, Integer ispay) {
		return billMapper.getCount(pid, name, ispay);
	}

	public List<Bill> getBillList(Integer pid, String name, Integer ispay,
			Integer from, Integer pageSize) {
		return billMapper.getBillList(pid, name, ispay, from, pageSize);
	}

}
