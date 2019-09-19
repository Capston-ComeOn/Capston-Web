//package com.spring.capstone.backend.web.argumentresolver;
//
//import com.spring.capstone.backend.domain.accounts.CurrentAccount;
//import com.spring.capstone.backend.service.dto.LoggedInAccount;
//import com.spring.capstone.backend.service.exception.InvalidAccountException;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Optional;
//
//@Component
//public class CurrentAccountArgumentResolver implements HandlerMethodArgumentResolver {
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(CurrentAccount.class);
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        HttpSession httpSession = ((HttpServletRequest) webRequest.getNativeRequest()).getSession();
//        return Optional.ofNullable((LoggedInAccount) httpSession.getAttribute(LoggedInAccount.SESSION_USER))
//                .orElseThrow(() -> new InvalidAccountException("로그인 후 이용할 수 있습니다."));
//    }
//}
//
