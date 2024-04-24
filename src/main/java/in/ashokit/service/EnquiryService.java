package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Enquiry;

public interface EnquiryService {
	   Dashboard getdashboardInfo(Integer cid);

	   boolean addEnquiry(Enquiry enq, Integer cid);

	   List<Enquiry> getEnquiries(Enquiry e, Integer cid);

	   Enquiry getEnquiry(Integer eid);
	}
