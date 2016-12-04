package com.personalinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.personalinfo.dao.DiaryDao;
import com.personalinfo.dto.Diary;


public class DiaryServlet extends HttpServlet{
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
    	String D_id = request.getParameter("D_id");
    	String D_time = request.getParameter("D_time");
    	String D_weather = request.getParameter("D_weather");
    	String D_week = request.getParameter("D_week");
    	String D_event = request.getParameter("D_event");   	
    	String Uid = request.getParameter("Uid");
   	    int intid = 0,uid=0;
    	
    	
    	int opt = 0; //�������   1,add  2,update  3,delete
    	int f = 0;   //�жϲ���
    
    	if("add".equals(action)){
    		opt = 1;
    	}else if("update".equals(action)){
    		opt = 2;
    	}else if("delete".equals(action)){
    		opt = 3;
    	}
    	Diary  user =  null;
    	DiaryDao   dao = new DiaryDao(); //�������ݿ��������
    	
    	switch(opt){
    	case  1:
    		if((!Uid.equals(null))||(!Uid.equals(""))){
        		uid = Integer.parseInt(Uid);
        	}
    		  user =  new Diary();//ʵ����bean����    	

    		  user.setD_time(D_time);
    		  user.setD_weather(D_weather);
    		  user.setD_week(D_week);
    		  user.setD_event(D_event);
    		  user.setUid(uid);
    		  
    		  f = dao.add(user);
    		  if(f>0){
    			  msg ="��ӳɹ�!";
    		  }else{
    			  msg ="���ʧ��!";
    		  }
    		  break;
    	case  2:
    		if(D_id!=null||D_id!=""){
        		intid = Integer.parseInt(D_id);
        	}     	
        	  user =  new Diary();//ʵ����bean����    	
        	  user.setD_id(intid);
    		  user.setD_time(D_time);
    		  user.setD_weather(D_weather);
    		  user.setD_week(D_week);
    		  user.setD_event(D_event);
        	  
    		  f = dao.update(user);
		      if(f>0){
			     msg ="�޸ĳɹ�!";
		      }else{
			     msg ="�޸�ʧ��!";
		      } 
		      break;
    	case  3:
    		if(D_id!=null||D_id!=""){
        		intid = Integer.parseInt(D_id);
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
    	out.println("<script language=\"javascript\">alert(\" "+msg+" \");self.location='D_list.jsp'; </script>"); //�������  
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws
    ServletException,IOException{
    	this.doGet(request, response);
    }
}
