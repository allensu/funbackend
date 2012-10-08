package tw.com.funbackend.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.form.querycond.GraffitiWallCondition;
import tw.com.funbackend.model.ChatroomModel;
import tw.com.funbackend.model.GraffitiWallModel;
import tw.com.funbackend.persistence.gopartyon.GraffitiWallItem;

@Service
public class GraffitiWallServiceImpl implements GraffitiWallService {
	protected Logger logger = Logger.getLogger("service");

	@Autowired
	private GraffitiWallModel graffitiWallModel;

	@Override
	public int readGraffitiWallCountByCond(GraffitiWallCondition cond) {
		
		return graffitiWallModel.readGraffitiWallCountByCond(cond);
	}

	@Override
	public List<GraffitiWallItem> readGraffitiWallPageByCond(
			GraffitiWallCondition cond, int startIndex, int length) {
		
		return graffitiWallModel.readGraffitiWallPageByCond(cond, startIndex, length);
	}

	@Override
	public List<GraffitiWallItem> readGraffitiWallPageByCondSort(
			GraffitiWallCondition cond, int startIndex, int length,
			String sortColName, int sortDir) {
		
		return graffitiWallModel.readGraffitiWallPageByCondSort(cond, startIndex, length, sortColName, sortDir);
	}
	
	
	
}
