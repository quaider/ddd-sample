# ddd-sample

## 好文收集

### 领域事件相关

- [在微服务中使用领域事件1](https://www.cnblogs.com/davenkin/p/6612656.html)

    介绍了领域事件发布的几种实现方案和可落地的建议

- [在微服务中使用领域事件2](https://insights.thoughtworks.cn/use-domain-events-in-microservices/)

    介绍了如何创建领域事件、发布领域事件、事件的流转及业务操作和事件发布的原子性方案

- [后端开发实践系列——事件驱动架构(EDA)编码实践](https://www.jianshu.com/p/b9549a4373b2)
    
    描述了领域事件在DDD中如何发布、存储、事件与业务操作的原子性等实现方案

## 设计观点收集

> DDD中其实并没有PO或者BO的概念，相反引入这些对象会增加编码复杂度
>
> 其实PO只是ORM才有的概念，他的生命周期也应该交给ORM框架去管理，PO也不应该暴露给DAO以上的层次，业务没必要知道有PO这个概念；
> > 说实在，我设计系统的时候还真没有PO这个概念，完全是ORM做关系映射没办法下的才引入的东西。
> 
> >正因为PO是数据表驱动设计的产物，我们采取Evans DDD等对象建模，那么就没有PO这个概念了，有的都是实体对象，ProductPic可以算一个实体对象，但是它是从属于Product的，是Product的关联子对象，被包含在product中，他们之间关系是对象的1:N关联关系。
 