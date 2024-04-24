package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
   @Autowired
   private CounsellorService cs;
   @Autowired
   private EnquiryService es;

   @GetMapping({"/logout"})
   public String logout(Model m, HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      session.invalidate();
      return "redirect:/";
   }

   @GetMapping({"/register"})
   public String register(Model m) {
      m.addAttribute("counsellor", new Counsellor());
      return "index";
   }

   @PostMapping({"/register"})
   public String handleRegister(Counsellor c, Model m) {
      //boolean saveCon = this.cs.saveCounsellor(c);
	   boolean saveCon = this.cs.saveCounsellor(c);
      if (saveCon) {
         m.addAttribute("smsg", "Data Saved Successfully..!");
      } else {
         m.addAttribute("emsg", "Failed to save..!");
      }

      m.addAttribute("counsellor", new Counsellor());
      return "index";
   }

   @GetMapping({"/"})
   public String login(Model m) {
      m.addAttribute("counsellor", new Counsellor());
      return "login";
   }

   @PostMapping({"/login"})
   public String handleLogin(Model m, Counsellor couns, HttpServletRequest req) {
      Counsellor c = this.cs.getCounsellor(couns.getEmail(), couns.getPwd());
      if (c == null) {
         m.addAttribute("emsg", "Invalid credentials..!");
         return "login";
      } else {
         HttpSession session = req.getSession(true);
         session.setAttribute("cid", c.getCid());
         Dashboard dinfo = this.es.getdashboardInfo(c.getCid());
         m.addAttribute("dashboard", dinfo);
         return "dashboard";
      }
   }

   @GetMapping({"/dashboard"})
   public String displayDashboard(Model m, HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Integer cid = (Integer)session.getAttribute("cid");
      Dashboard dinfo = this.es.getdashboardInfo(cid);
      m.addAttribute("dashboard", dinfo);
      return "dashboard";
   }
}
