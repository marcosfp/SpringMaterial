package des.springprueba.security;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import des.springprueba.entity.Profesor;
import des.springprueba.service.ProfesorService;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  
	
	@Autowired
	private ProfesorService profesorService;
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {
    	
        HttpSession session = request.getSession();
        
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        
        Profesor authUser = profesorService.findByUsername(userDetails.getUsername()) ; 
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("nombre", authUser.getNombreProfesor());
        session.setAttribute("idUsuario", authUser.getIdProfesor());
        
        boolean isUser = false;
        boolean isAdmin = false;
        
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("profesor")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("administrador")) {
                isAdmin = true;
                break;
            }
        }
        
        if (isUser) {
        	session.setAttribute("rol","profesor" );
        }else {
        	session.setAttribute("rol","admin" );
        }
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
  
    }
    
    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        boolean isProfesor = false;
        boolean isAdmin = false;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROL_PROFESOR")) {
            	isProfesor = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROL_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isProfesor) {
            return "/profesor/myprofile";
        } else if (isAdmin) {
            return "/index";
        } else {
            throw new IllegalStateException();
        }
    }
    
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    

}
