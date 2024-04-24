package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {
   @Autowired
   private EnquiryRepo erepo;
   @Autowired
   private CounsellorRepo crepo;

   public Dashboard getdashboardInfo(Integer cid) {
      Long tenq = this.erepo.getEnquiries(cid);
      Long oenq = this.erepo.getEnquiry(cid, "New");
      Long lenq = this.erepo.getEnquiry(cid, "Lost");
      Long eenq = this.erepo.getEnquiry(cid, "Enrolled");
      Dashboard d = new Dashboard();
      d.settEnq(tenq);
      d.setoEnq(oenq);
      d.setlEnq(lenq);
      d.seteEnq(eenq);
      return d;
   }

   public boolean addEnquiry(Enquiry enq, Integer cid) {
      Counsellor c = (Counsellor)this.crepo.findById(cid).orElseThrow();
      enq.setCid(c);
      Enquiry save = (Enquiry)this.erepo.save(enq);
      return save != null;
   }

   public List<Enquiry> getEnquiries(Enquiry e, Integer cid) {
      Counsellor cr = new Counsellor();
      cr.setCid(cid);
      Enquiry searchenq = new Enquiry();
      searchenq.setCid(cr);
      if (e.getClassmode() != null && !"".equals(e.getClassmode())) {
         searchenq.setClassmode(e.getClassmode());
      }

      if (e.getCourse() != null && !"".equals(e.getCourse())) {
         searchenq.setCourse(e.getCourse());
      }

      if (e.getStatus() != null && !"".equals(e.getStatus())) {
         searchenq.setStatus(e.getStatus());
      }

      Example<Enquiry> of = Example.of(searchenq);
      return (List<Enquiry>) this.erepo.findAll(of);
   }

   public Enquiry getEnquiry(Integer eid) {
      return (Enquiry)this.erepo.findById(eid).orElseThrow();
   }
}
