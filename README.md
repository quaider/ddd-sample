# ddd-sample

现有购物车聚合

聚合根：`Cart`，实体：`CartItem`，值对象：`Product`

对应的PO分别为：`CartPO`、`CartItemPO`、`ProductPO`

1、每次添加到购物车时，通过repo查询CartPO和CartItemPO重建Cart时，都查询了所有购物项

2、由于每次操作都通过聚合根进行，因此都需要重建Cart聚合根，这会导致每次查询CartPO、CartItemPO列表

## 设计观点收集

> DDD中其实并没有PO或者BO的概念，相反引入这些对象会增加编码复杂度
>
> 其实PO只是ORM才有的概念，他的生命周期也应该交给ORM框架去管理，PO也不应该暴露给DAO以上的层次，业务没必要知道有PO这个概念；
> > 说实在，我设计系统的时候还真没有PO这个概念，完全是ORM做关系映射没办法下的才引入的东西。
> 
> >正因为PO是数据表驱动设计的产物，我们采取Evans DDD等对象建模，那么就没有PO这个概念了，有的都是实体对象，ProductPic可以算一个实体对象，但是它是从属于Product的，是Product的关联子对象，被包含在product中，他们之间关系是对象的1:N关联关系。
 