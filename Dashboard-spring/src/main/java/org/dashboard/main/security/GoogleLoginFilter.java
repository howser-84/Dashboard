package org.dashboard.main.security;

import org.dashboard.main.data.User;
import org.dashboard.main.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class GoogleLoginFilter extends GenericFilterBean {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void doFilter(final ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest){
            final HttpServletRequest req = (HttpServletRequest) servletRequest;
            if (req.getHeader("Authorization") != null && req.getHeader("Authorization").startsWith("Bearer ")){
                String token = req.getHeader("Authorization").replaceFirst("Bearer ", "");
                String userId = GoogleValidator.getUserID(token);
                if (userId == null){
                    ((HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                User user = userDAO.findByUsername(userId);
                String basicAuthExpr = userId + ":" + user.getPassword();
                /*HttpServletRequest modifiedReq = new HttpServletRequestWrapper(req){
                    @Override
                    public String getHeader(String name) {
                        if (name.equals("Authentication")){
                            return "Basic " + Base64.encodeBase64String(basicAuthExpr.getBytes());
                        }
                        final String value = req.getParameter(name);
                        if (value != null) {
                            return value;
                        }
                        return super.getHeader(name);
                    }
                };*/
                MyServletRequestWrapper modifiedReq = new MyServletRequestWrapper(req);
                modifiedReq.addHeader("Authorization","Basic " + Base64.getEncoder().encodeToString(basicAuthExpr.getBytes()));
                filterChain.doFilter(modifiedReq, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public class MyServletRequestWrapper extends HttpServletRequestWrapper{

        private Map headerMap;

        public void addHeader(String name, String value){
            headerMap.put(name, new String(value));
        }

        public MyServletRequestWrapper(HttpServletRequest request){
            super(request);
            headerMap = new HashMap();
        }

        public Enumeration getHeaderNames(){
            HttpServletRequest request = (HttpServletRequest)getRequest();
            List list = new ArrayList();
            for( Enumeration e = request.getHeaderNames() ;  e.hasMoreElements() ; )
                list.add(e.nextElement().toString());
            for(Iterator i = headerMap.keySet().iterator(); i.hasNext() ; ){
                list.add(i.next());
            }
            return Collections.enumeration(list);
        }
        public String getHeader(String name){
            Object value;
            if((value = headerMap.get(""+name)) != null)
                return value.toString();
            else
                return ((HttpServletRequest)getRequest()).getHeader(name);
        }
    }
}
