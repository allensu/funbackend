package tw.com.funbackend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.funbackend.persistence.gopartyon.User;

import tw.com.funbackend.form.querycond.MemberDataQueryCondition;
import tw.com.funbackend.form.tableschema.BlockUserRankTableSchema;
import tw.com.funbackend.model.FileModel;
import tw.com.funbackend.model.MemberModel;

@Service
public class MemberServiceImpl implements MemberService {
	protected Logger logger = Logger.getLogger("service");
	
	@Autowired
	private MemberModel memberModel;

	@Autowired
	FileModel fileModel;
	
	@Override
	public List<User> readUserAll() {
				
		return memberModel.readUserAll();
	}

	@Override
	public User readUser(String id) {
		
		return memberModel.readUser(id);
	}

	@Override
	public void updateUser(User user) {
		
		memberModel.updateUser(user);
	}

	@Override
	public int readUserCount() { 
	
		return memberModel.readUserCount();
	}

	@Override
	public List<User> readUserPage(int startIndex, int length) {

		return memberModel.readUserPage(startIndex, length);
	}

	@Override
	public int readUserCountByCond(MemberDataQueryCondition cond) {

		return memberModel.readUserCountByCond(cond);
	}

	@Override
	public List<User> readUserPageByCond(MemberDataQueryCondition cond,
			int startIndex, int length) {

		return memberModel.readUserPageByCond(cond, startIndex, length);
	}

	@Override
	public List<User> readUserPageByCondSort(MemberDataQueryCondition cond,
			int startIndex, int length, String sortColName, int sortDir) {
				
		return memberModel.readUserPageByCondSort(cond, startIndex, length, sortColName, sortDir);
	}
	
	@Override
	public boolean deletePhotoFromAlbum(User user, String fileName) {
		
		boolean result = false;
		
		try {
			// delete file from album			
			fileModel.deleteFile(fileName);
			
			// delete file from user
			memberModel.deletePhotoFromAlbum(user, fileName);
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		
		return result;
	}
	
	@Override
	public void deletePhotoFromPic(User user, String fileName) {
		memberModel.deletePhotoFromPic(user, fileName);
		
	}

	@Override
	public void deleteBatchPhotoFromAlbum(User user, List<String> fileNames) {
		
		for(String currFileName : fileNames)
		{
			memberModel.deletePhotoFromAlbum(user, currFileName);
		}
	}

	@Override
	public boolean addPhotoToAlbum(User user, String fileName, byte[] fileBytes) {

		boolean result = false;
		
		try {
			// save file to album
			fileModel.createByByte(fileName, fileBytes);
			
			// update user photo data
			memberModel.addPhotoToAlbum(user, fileName);
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	@Override
	public User readUserByUserName(String userName) {
		
		return memberModel.readUserByUserName(userName);		
	}

	@Override
	public List<Map<String, String>> readUserPhotosByUserName(String userName) {
		
		User user = readUserByUserName(userName);
		return user.getPhotos();
	}

	@Override
	public List<BlockUserRankTableSchema> getBlockUserRank() {
		
		List<BlockUserRankTableSchema> result = new ArrayList<BlockUserRankTableSchema>();
		List<User> userList = new ArrayList<User>();		
		//Map<String, BlockUserRankTableSchema> userBlockCount = new HashMap<String, BlockUserRankTableSchema>();
		Map<String, Integer> userBlockCount = new HashMap<String, Integer>();
		ValueComparator bvc = new ValueComparator(userBlockCount);
		TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
		
		try {
			userList = memberModel.readValidUser(false, false);
			
			for(User currUser : userList)
			{
				if(currUser.getBlockUsers() != null)
				{
					for(Map<String, Object> currMap : currUser.getBlockUsers())
					{
						if(userBlockCount.containsKey(String.valueOf(currMap.get("userName"))))
						{
							int count = userBlockCount.get(String.valueOf(currMap.get("userName")));
							count = count + 1;
							userBlockCount.put(String.valueOf(currMap.get("userName")), count);					
						}
						else
						{
							userBlockCount.put(String.valueOf(currMap.get("userName")), 1);
						}														
					}
				}
			}
			
			 sorted_map.putAll(userBlockCount);
			 
			 int rankNum = 0;
			 for(String currUserName : sorted_map.keySet())
			 {
				 BlockUserRankTableSchema data = new BlockUserRankTableSchema();
				 data.setUserName(currUserName);
				 data.setVotes(userBlockCount.get(currUserName));
				 data.setRankNum(++rankNum);
				 result.add(data);
			 }
						
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
				
		return result;
	}
	
	class ValueComparator implements Comparator<String> {

	    Map<String, Integer> base;
	    public ValueComparator(Map<String, Integer> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}
}
