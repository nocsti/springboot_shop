package com.tfotu.shop.domin;

import com.tfotu.shop.Utils;
import com.tfotu.shop.entity.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Aspect
@Component      //把切面类加入到IOC容器中
public class HttpAspect {

    @Before("execution(public * com.tfotu.shop.control.UserControl.*(..))")
    public void userLoginCheck() throws IOException {
        System.out.println("userLoginCheck");
    }
}
