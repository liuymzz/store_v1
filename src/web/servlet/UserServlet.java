package web.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import constants.Constant;
import convertor.MyDateConvertor;
import entities.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.UUIDUtils;

/**
 * 用户Servlet
 */
public class UserServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 跳转至注册页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String register(HttpServletRequest request, HttpServletResponse response) {

		return "/jsp/register.jsp";
	}

	
	/**
	 * 实现用户注册逻辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String registerService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// 设置request编码方式
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String picCode = (String) request.getSession().getAttribute("picCode");
		if (picCode.equals(request.getSession().getAttribute("picCode"))) {
			User user = new User();

			// 为用户实体设置uuid
			user.setUid(UUIDUtils.getId());

			// 为用户实体设置邮箱激活需要用到的激活码
			user.setCode(UUIDUtils.getCode());

			try {
				//注册一个自定义转换器
				ConvertUtils.register(new MyDateConvertor(), Date.class);
				BeanUtils.populate(user, request.getParameterMap());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			UserService userService = new UserServiceImpl();

			userService.register(user,request);

			// 注册成功跳转至msg.jsp页面并显示提示信息
			request.setAttribute("msg", "注册成功,请登陆注册时填写的邮箱激活");
		}else{
			request.setAttribute("msg", "请输入正确的验证码");
			return "/jsp/register.jsp";
		}
		
		
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 负责邮件激活的方法
	 * @param request
	 * @param response
	 * @return
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//获取激活链接中的激活码
		String code = request.getParameter("code");
		
		//通过激活码激活账户逻辑,激活成功返回user对象,如果user对象为空则说明激活失败
		UserService userService = new UserServiceImpl();
		User user = userService.active(code);
		
		if(user == null){
			//为空说明激活失败,给予提示
			request.setAttribute("msg", "激活失败,根据激活码找不到指定的账户,请重新注册账户");
		}else{
			//否则激活成功
			request.setAttribute("msg", "激活成功,您现在可以登录了");
		}
		
		//最后跳转至msg.jsp页面
		return "/jsp/msg.jsp";
	}
	
	
	/**
	 * 跳转至登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return "/jsp/login.jsp";
	}
	
	
	/**
	 * 登录逻辑实现
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String loginService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String picCode = (String) request.getSession().getAttribute("picCode");
		//先判断验证码是否输入正确
		if (picCode.equals(request.getSession().getAttribute("picCode"))) {
			UserService userService = new UserServiceImpl();
			User user = userService.login(username,password);
			
			//如果没有查询到匹配的用户则重新跳转至登陆页面并给出提示
			if (user == null) {
				request.setAttribute("msg", "用户账号密码不匹配");
				return "/jsp/login.jsp";
			}
			
			//继续判断用户是否激活
			if (Constant.USER_IS_ACTIVE != user.getState()) {
				request.setAttribute("msg", "用户未激活,请先前往注册邮箱激活");
				return "/jsp/login.jsp";
			}
			
			//最终将用户数据放置域对象中并跳转至首页
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/");
			return null;
		}else{
			request.setAttribute("msg", "请输入正确的验证码");
		}
		
		return "/jsp/login.jsp";
	}

	
	/**
	 * 登出逻辑
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath()+"/");
		return null;
	}

	
	
	public String picCode(HttpServletRequest request,HttpServletResponse response){
		
		BufferedImage bi=new BufferedImage(92,22,BufferedImage.TYPE_INT_BGR);
        Graphics g=bi.getGraphics();
        Color c=new Color(250,150,255);
        g.setColor(c);
        g.fillRect(0,0,92,22);
        //验证码字符集合
        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random r=new Random();
        int len=ch.length;
        int index;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<6;++i){
            index=r.nextInt(len);
            //设置验证码字符随机颜色
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
            //画出对应随机的验证码字符
            g.drawString(ch[index]+"",(i*15)+3,18);
            sb.append(ch[index]);
        }
        //把验证码字符串放入Session
        request.getSession().setAttribute("picCode", sb.toString());
        //在HttpServletResponse中写入验证码图片信息
        try {
			ImageIO.write(bi, "JPG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
