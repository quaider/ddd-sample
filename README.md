# ddd-sample

现有购物车聚合

聚合根：`Cart`，实体：`CartItem`，值对象：`Product`

对应的PO分别为：`CartPO`、`CartItemPO`、`ProductPO`

1、每次添加到购物车时，通过repo查询CartPO和CartItemPO重建Cart时，都查询了所有购物项

2、由于每次操作都通过聚合根进行，因此都需要重建Cart聚合根，这会导致每次查询CartPO、CartItemPO列表