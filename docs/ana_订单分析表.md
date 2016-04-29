##ana_sp_orderInfo

###ana_数据  `ana_sp_orderInfo`

字段  | 类型  |  comment | 数据来源表 | 获取方式
------------- | ------------- | ------------- | ------------- | -------------
`id`  |  `int(11)`  | `id` | `auto_increment`  | `auto_increment`
`org_infoId` |  `varchar(32)`   | `原spOrderInfo主键` | ``  | ``
`orderNo`  | `varchar(32)`  | `订单编号`  | ``  | ``
`chirdOrderNo`  | `varchar(32)`  | `子订单编号`  | ``  | ``
`pId`  | `varchar(32)`   | `父订单id`  | ``  | `` 
`orderTime` |  `datetime`   | `增添订单时间` | ``  | ``
`goodsPrice`  | `decimal(10,2)`  | `商品总额`  | ``  | ``
`orderPrice` | `decimal(10,2)`   | `订单总额`  | ``  | ``
`zmaoli`  | `decimal(10,2)`   | `总毛利`  | ``  | ``
`zfee`  | `decimal(10,2)`  | `总费用金额`  | `` | ``
`rebate`  | `decimal(10,2)`  | `促销返钱`  | `` | ``
`kfId`  | `varchar(32)`   | `客服Id`  | ``  | ``
`userId`  | `varchar(32)`   |`小店老板ID号`  | ``  | ``
`storeId`  | `varchar(32)`   | `小店老板所属商铺id`  | ``  | ``
`status`  | `int(4)`   | `订单状态 1:已下单 2：已派单  3:已提单 4:已打印 5:已送达`  | ``   | ``
`supportmetho`  | `int(2)`  | `支付方式1、快钱支付2、货到付款 3、支付宝支付 4、微信支付`  | ``  | ``
`ordertype`  | `int(2)`   |`订单配送方式：0送货上门，1当面付，2店面自提`  | ``  | ``
`supplierId`  | `int(8)`    | `一级批发商编号`  | ``  | ``
`supportStatus`  | `int(2)`   | `支付状态：0-未支付，1-支付`  | ``  | ``
`coupon`  | `varchar(32)`   | `优惠券金额`  | `SpVoucher.id`  | `` 
`createTime` | `int(11)`   | `新增时间`  | `new Date().int`  | `new Date().int`
`updateTime`  | `timestamp`   | `修改时间`  | `current_timestamp`  | `current_timestamp`

##### `ana_sp_orderInfo`的sql

select `t1.id` as `org_infoId`, `t1.orderId` as `orderNo`, `t2.orderId` as `chirdOrderNo`, `t1.pId`, `t1.addTime` as `orderTime`, `t1.goodsPrice`, `t1.orderPrice`, `t1.zmaoli`, `t1.zfee`, `t1.rebate`, `t1.kfId`, `t1.userId`, `t1.storeId`, `t1.status`, `t1.supportmetho`, `t1.ordertype`, `t1.supplierId`, `t1.supportStatus`, `t1.coupon` from `SpOrderInfo` `t1` left join `SpOrderInfo` `t2` on `t1.id = t2.pid` where `t2.level = 2`


###ana_数据  `ana_sp_orderDetail`
字段  | 类型  |  comment | 数据来源表 | 获取方式
------------- | ------------- | ------------- | ------------- | -------------
`id`  |  `int(11)`  | `id` | `auto_increment`  | `auto_increment`
`infoId` |  `int(11)`   | `ana_sp_orderInfo主键` | ``  | ``
`org_detailId` |  `varchar(32)`   | `原spOrderdetail主键` | ``  | ``
`orderNo`  | `varchar(32)`  | `订单编号`  | ``  | ``
`chirdOrderNo`  | `varchar(32)`  | `子订单编号`  | ``  | ``
`itemId` | `varchar(32)`   | `商品id`  | ``  | ``
`quantity`  | `int(6)`   | `购买数量`  | ``  | ``
`price`  | `decimal(10,2)`   |`商品单价`  | ``  | ``
`plantMemPrice`  | `decimal(10,2)`   | `平台会员报价`  | ``  | ``
`storeId`  | `int(11)`   | `所属店铺`  | ``   | ``
`addTime`  | `datetime`  | `增加时间`  | ``  | ``
`maoli`  | `decimal(10,2)`   |`毛利`  | ``  | ``
`fee`  | `decimal(10,2)`    | `费用（公司需要出的钱）`  | ``  | ``
`plantDisPrice`  | `decimal(10,2)`   | `批发商进货价（原财务报价）`  | ``  | ``
`spGroupId`  | `int(11)`  | `批发商组id`  | `` | ``
`spId`  | `varchar(32)`  | `批发商id`  | `` | ``
`youHui`  | `decimal(10,2)`  | `优惠信息(买多少送多少)`  | `` | ``
`restrict`  | `int(6)`   | `数量限制（每人限买多少件）`  | ``  | ``
`createTime` | `int(11)`   | `新增时间`  | `new Date().int`  | `new Date().int`
`updateTime`  | `timestamp`   | `修改时间`  | `current_timestamp`  | `current_timestamp`

##### `ana_sp_orderDetail`的sql

select `id`, `orderId` as `orderNo`, `orderId2` as `chirdOrderNo` `itemId`, `quantity`, `price`, `plantMemPrice`, `storeId`, `addTime` as `orderTime`, `maoli`, `fee`, `plantDisPrice`, `spGroupId`, `spId`, `youHui`, `restrict` from `SpOrderDetail` where `orderId2 = #{chirdOrderNo}`