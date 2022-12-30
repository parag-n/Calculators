package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;

public class MyServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		float p=Float.parseFloat(req.getParameter("principle"));
		float r=Float.parseFloat(req.getParameter("rate"));
		int n=Integer.parseInt(req.getParameter("years"));
		int c=Integer.parseInt(req.getParameter("compounds"));
		float amount=CompoundInt(p, r, n,c);
		RequestDispatcher rd=req.getRequestDispatcher("index.html");
		
		try {
			rd.include(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		out.println("<h2 style='color: #ffffff'><center>Total amount to be paid is: Rs."+amount+"</center></h2>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}

	public float CompoundInt(float p, float r, int n, int c) {
		float factor=1;
		float num=(n*12)/c;
		float compFact=(1+((r*c)/1200));
		
		for(int i=1;i<=num;i++) {
			factor*=compFact;
		}
		return p*factor;
	}
}