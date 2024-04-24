package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	   Counsellor findByEmailAndPwd(String email, String pwd);

	   Counsellor findByEmail(String email);
	}
	    
