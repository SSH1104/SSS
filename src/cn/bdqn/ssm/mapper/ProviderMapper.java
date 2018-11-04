package cn.bdqn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.ssm.pojo.Provider;


public interface ProviderMapper {

	//查找总记录数
	public int getCount(@Param("code")String code,@Param("name")String name);
	
	//分页列表
	public List<Provider> getProviderList(@Param("code")String code,@Param("name")String name
									,@Param("from")Integer from,@Param("pageSize")Integer pageSize);
	
	//查找列表不分页
	public List<Provider> getProList();
	
	//新增
	public int insert(Provider pro);
	
	//根据ID获取信息
	public Provider getProviderById(@Param("id")String id); 
	
	//修改
	public int modify(Provider pro);
}
