package com.amx.dataservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.amx.dataservice.dao.UserDao;
import com.amx.dataservice.enums.UpdateCodeEnum;
import com.amx.dataservice.model.bo.UserBo;
import com.amx.dataservice.model.domain.UserDo;
import com.amx.dataservice.model.dto.UpdateResponseDto;
import com.amx.dataservice.service.UserService;
import com.amx.dataservice.util.BeanUtil;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UpdateResponseDto save(UserBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		/* 检查用户是否已经存在 */
		UserDo probe_nick = new UserDo();
		probe_nick.setNickName(bo.getNickName());
		Example<UserDo> example_nick = Example.of(probe_nick );
		if(probe_nick.getNickName() != null){
			List<UserDo> findAll = userDao.findAll(example_nick);
			if(findAll != null && findAll.size() != 0){
				response.setCode(UpdateCodeEnum.EXIST.getCode());
				response.setMsg("用户名已被注册");
				
				return response;
			}
		}

		/* 检查手机是否存在 */
		UserDo probe_phone = new UserDo();
		probe_phone.setPhone(bo.getPhone());
		Example<UserDo> example_phone = Example.of(probe_phone );
		if(probe_phone.getPhone() != null){
			List<UserDo> findAll = userDao.findAll(example_phone);
			if(findAll != null && findAll.size() != 0){
				response.setCode(UpdateCodeEnum.EXIST.getCode());
				response.setMsg("手机号已被注册");
				
				return response;
			}
		}
		
		/* 检查是否已从第三方注册过 */
		UserDo probe_openId = new UserDo();
		probe_openId.setOpenId(bo.getOpenId());
		probe_openId.setLoginType(bo.getLoginType());
		Example<UserDo> example_openId = Example.of(probe_openId );
		if(probe_openId.getOpenId() != null && probe_openId.getLoginType() != null){
			List<UserDo> findAll = userDao.findAll(example_openId);
			if(findAll != null && findAll.size() != 0){
				response.setCode(UpdateCodeEnum.EXIST.getCode());
				response.setMsg("微博或QQ已注册");
				
				return response;
			}
		}
		
		UserDo entity = new UserDo();
		/*关键属性*/
		BeanUtil.copyNotNullProperties(bo, entity);
		
		/*设置非空属性*/
		entity.setIsEnable(true);
		entity.setIsDelete(false);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		
		if(userDao.saveAndFlush(entity ) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}
	
	@Override
	public UpdateResponseDto update(UserBo bo){
		UpdateResponseDto response = new UpdateResponseDto(); 
		
		UserDo entity = userDao.findOne(bo.getId());
		
		/*关键属性*/
		BeanUtil.copyNotNullProperties(bo, entity);

		// 修改时间
		entity.setModifyTime(new Date());

		if(userDao.save(entity ) != null){
			response.setCode(UpdateCodeEnum.SUCCESS.getCode());
		}else{
			response.setCode(UpdateCodeEnum.FAIL.getCode());
		}
		
		return response;
	}

	@Override
	public UserBo findOne(long id) {
		return new UserBo(userDao.findOne(id));
	}

	@Override
	public Object findOneByExample(UserBo exampleBo) {
		
		UserDo probe = new UserDo();
		BeanUtil.copyNotNullProperties(exampleBo, probe);
		Example<UserDo> example = Example.of(probe );
		
		return new UserBo(userDao.findOne(example));
	}
	
}
