package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.model.ChatroomModel;
import tw.com.funbackend.model.GraffitiWallModel;
import tw.com.funbackend.model.MemberModel;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;
import tw.com.funbackend.persistence.gopartyon.User;

@Service
public class GraffitiWallServiceImpl implements GraffitiWallService {
	protected Logger logger = Logger.getLogger("service");

	@Autowired
	private GraffitiWallModel graffitiWallModel;

	@Autowired
	private MemberModel memberModel;
	
	@Override
	public int readGraffitiWallCountByCond(GraffitiWallCondition cond) {
		
		if(cond.getCategoryQ() != null && !"".equals(cond.getCategoryQ()))
		{
			User posterUser = memberModel.readUserByUserName(cond.getPosterQ());
			cond.setPosterId(posterUser.getId());
		}
		
		if(cond.getWallOwnerQ() != null && !"".equals(cond.getWallOwnerQ()))
		{
			User wallOwnerUser = memberModel.readUserByUserName(cond.getWallOwnerQ());		
			cond.setWallOwnerId(wallOwnerUser.getId());	
		}
		
		return graffitiWallModel.readGraffitiWallCountByCond(cond);
	}

	@Override
	public List<GraffitiWallItem> readGraffitiWallPageByCond(
			GraffitiWallCondition cond, int startIndex, int length) {	
		
		if(cond.getCategoryQ() != null && !"".equals(cond.getCategoryQ()))
		{
			User posterUser = memberModel.readUserByUserName(cond.getPosterQ());
			cond.setPosterId(posterUser.getId());
		}
		
		if(cond.getWallOwnerQ() != null && !"".equals(cond.getWallOwnerQ()))
		{
			User wallOwnerUser = memberModel.readUserByUserName(cond.getWallOwnerQ());		
			cond.setWallOwnerId(wallOwnerUser.getId());	
		}
		
		return graffitiWallModel.readGraffitiWallPageByCond(cond, startIndex, length);
	}

	@Override
	public List<GraffitiWallItem> readGraffitiWallPageByCondSort(
			GraffitiWallCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {

		if(cond.getCategoryQ() != null && !"".equals(cond.getCategoryQ()))
		{
			User posterUser = memberModel.readUserByUserName(cond.getPosterQ());
			cond.setPosterId(posterUser.getId());
		}
		
		if(cond.getWallOwnerQ() != null && !"".equals(cond.getWallOwnerQ()))
		{
			User wallOwnerUser = memberModel.readUserByUserName(cond.getWallOwnerQ());		
			cond.setWallOwnerId(wallOwnerUser.getId());	
		}
		
		return graffitiWallModel.readGraffitiWallPageByCondSort(cond, startIndex, length, sortColName, sortDir);
	}
	
	
	
}
