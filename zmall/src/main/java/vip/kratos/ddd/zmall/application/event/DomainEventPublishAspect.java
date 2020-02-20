package vip.kratos.ddd.zmall.application.event;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 事件发布切面(标注在应用服务的业务方法上，完成业务操作后发布事件)
 * <p>
 * `@Order(1)` 表示本切面在事务切面之前
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class DomainEventPublishAspect {

    private TaskExecutor taskExecutor;

    public DomainEventPublishAspect() {
        this.taskExecutor = buildTaskExecutor();
    }

    private TaskExecutor buildTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(500);
        executor.setRejectedExecutionHandler((r, e) -> log.debug("Domain event publish job rejected silently."));
        executor.setThreadNamePrefix("domain-event-publish-executor-");
        executor.initialize();
        return executor;
    }

    @After("@annotation(vip.kratos.ddd.zmall.application.common.DomainEventAware)")
    public void publishEvents(JoinPoint joinPoint) {
        // 发布领域事件到MQ
        log.info("Trigger domain event publish process using Spring AOP.");
        taskExecutor.execute(() -> {
            // publish to mq
        });
    }

}
