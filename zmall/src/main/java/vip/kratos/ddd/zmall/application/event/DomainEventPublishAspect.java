package vip.kratos.ddd.zmall.application.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 事件发布切面(标注在应用服务的业务方法上，完成业务操作后发布事件)
 */
@Aspect
@Component
public class DomainEventPublishAspect {
    @After("@annotation(vip.kratos.ddd.zmall.application.common.DomainEventAware)")
    public void publishEvents(JoinPoint joinPoint) {
        System.out.println("=========================================");
//        logger.info("Trigger domain event publish process.");
//        taskExecutor.execute(() -> publisher.publish());
    }
}
