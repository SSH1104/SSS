package cn.bdqn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.ssm.pojo.Bill;



public interface BillMapper {

	//查找总记录数
	public int getCount(@Param("pid")Integer pid,@Param("name")String name,@Param("ispay")Integer ispay);
	
	//分页列表
	public List<Bill> getBillList(@Param("pid")Integer pid,@Param("name")String name,@Param("ispay")Integer ispay
									,@Param("from")Integer from,@Param("pageSize")Integer pageSize);
}
