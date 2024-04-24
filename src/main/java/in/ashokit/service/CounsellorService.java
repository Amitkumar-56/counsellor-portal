package in.ashokit.service;

import in.ashokit.entity.Counsellor;

public interface CounsellorService {
	   boolean saveCounsellor(Counsellor con);

	   Counsellor getCounsellor(String email, String pwd);
	}
	    
