package com.personalinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.personalinfo.dao.FinanceDao;
import com.personalinfo.dto.Finance;


public class FinanceServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config)throws ServletException{
    	
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws
    ServletException,IOException{
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
         
    	PrintWriter  out = response.getWriter();
    	String   msg = "";
    	String action = request.getParameter("action"); //ҳ����������
    	String F_id = request.getParameter("F_id");	
    	String F_time = request.getParameter("F_time");
    	String F_ievent = request.getParameter("F_ievent");
    	String F_pevent = request.getParameter("F_pevent");
    	String F_income = request.getParameter("F_income");
    	String F_pay = request.getParameter("F_pay");
    	String F_remamoney = request.getParameter("F_remamoney");
    	String Uid = request.getParameter("Uid");
    	int intid = 0,uid=0;
    	
    	int opt = 0; //�������   1,add  2,update  3,delete
    	int f = 0; //�жϲ���
    
    	if("add".equals(action)){
    		opt = 1;
    	}else if("update".equals(action)){
    		opt = 2;
    	}else if("delete".equals(action)){
    		opt = 3;
    	}
    	Finance  user = null;
    	FinanceDao   dao = new FinanceDao(); //�������ݿ��������
    	
    	switch(opt){
    	case  1:
    		if((!Uid.equals(null))||(!Uid.equals(""))){
    		  uid = Integer.parseInt(Uid);
    	    } 
    		user =  new Finance();//ʵ����bean���� 
    		user.setF_time(F_time);
    		user.setF_ievent(F_ievent);
    		user.setF_pevent(F_pevent);
    		user.setF_income(F_income);
    		user.setF_pay(F_pay);
    		user.setF_remamoney(F_remamoney);
    		user.setUid(uid);
    		
    		f = dao.add(user);
    		  if(f>0){
    			  msg ="��ӳɹ�!";
    		  }else{
    			  msg ="���ʧ��!";
    		  }
    		  break;
    	case  2:
    		if(F_id!=null||F_id!=""){
        		intid = Integer.parseInt(F_id);
        	}
    		user =  new Finance();//ʵ����bean����
    		user.setF_id(intid);
    		user.setF_time(F_time);
    		user.setF_ievent(F_ievent);
    		user.setF_pevent(F_pevent);
    		user.setF_income(F_income);
    		user.setF_pay(F_pay);
    		user.setF_remamoney(F_remamoney);
    		
    		f = dao.update(user);
		      if(f>0){
			     msg ="�޸ĳɹ�!";
		      }else{
			     msg ="�޸�ʧ��!";
		      } 
		      break;
    	case  3:
    		if(F_id!=null||F_id!=""){
        		intid = Integer.parseInt(F_id);
        	}
    		f = dao.delete(intid);
		     if(f>0){
			     msg ="ɾ���ɹ�!";
		      }else{
			     msg ="ɾ��ʧ��!";
		      }  
		      break;
        default :msg = "�������㣬����ʧ��!"; break;
    	}
    	out.println("<script language=\"javascript\"> alert(\" "+msg+" \");self.location='F_list.jsp'; </script>"); //�������  
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws
    ServletException,IOException{
    	this.doGet(request, response);
    }
}
