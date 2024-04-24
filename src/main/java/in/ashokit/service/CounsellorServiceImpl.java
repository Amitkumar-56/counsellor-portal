package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {
   @Autowired
   private CounsellorRepo crepo;

   public boolean saveCounsellor(Counsellor con) {
      Counsellor bym = this.crepo.findByEmail(con.getEmail());
      if (bym != null) {
         return false;
      } else {
         Counsellor sv = (Counsellor)this.crepo.save(con);
         return sv.getCid() != null;
      }
   }

   public Counsellor getCounsellor(String email, String pwd) {
      return this.crepo.findByEmailAndPwd(email, pwd);
   }
}
