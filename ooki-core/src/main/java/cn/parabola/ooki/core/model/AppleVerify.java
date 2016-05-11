package cn.parabola.ooki.core.model;

/**
 * @title 苹果服务器校验完之后返回校验的结果 以josn格式
 * 
 * @author wangshaojun
 * 
 * @version 0.00 2014-09-04 add
 * 
 * {
"receipt":{"original_purchase_date_pst":"2014-09-03 04:15:37 America/Los_Angeles",
 "unique_identifier":"2bd43915180707573c1147debd238196b8453419", 
 "original_transaction_id":"490000064611728", 
 "bvrs":"2.0.0", "app_item_id":"863030035",
  "transaction_id":"490000064611728", 
  "quantity":"1", 
  "unique_vendor_identifier":"15C06E74-9E0E-45D4-8634-B555E852DCA4", 
  "product_id":"fksg_30rmb_330gold",
   "item_id":"879431320",
    "version_external_identifier":"657842679",
     "bid":"com.ea.product.fangkuaisanguo", 
     "purchase_date_ms":"1409742937957",
      "purchase_date":"2014-09-03 11:15:37 Etc/GMT",
       "purchase_date_pst":"2014-09-03 04:15:37 America/Los_Angeles", 
       "original_purchase_date":"2014-09-03 11:15:37 Etc/GMT",
        "original_purchase_date_ms":"1409742937957"}, "status":0}
 */
public class AppleVerify {

	private int status;// 返回状态 0为成功 其它为编码
	private String bid;// bid
	private int appid;// 应用商品id
	private String productId;// 产品id
	private String orderId;// 订单id
	private String uniqueIdentifier;
	private String originalTransactionId;
	private String bvrs;
	private String quantity;
	private String uniqueYendorIdentifier;
	private int itemId;
	private int versionExternalIdentifier;
	private long purchaseDateMs;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getOriginalTransactionId() {
		return originalTransactionId;
	}

	public void setOriginalTransactionId(String originalTransactionId) {
		this.originalTransactionId = originalTransactionId;
	}

	public String getBvrs() {
		return bvrs;
	}

	public void setBvrs(String bvrs) {
		this.bvrs = bvrs;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUniqueYendorIdentifier() {
		return uniqueYendorIdentifier;
	}

	public void setUniqueYendorIdentifier(String uniqueYendorIdentifier) {
		this.uniqueYendorIdentifier = uniqueYendorIdentifier;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getVersionExternalIdentifier() {
		return versionExternalIdentifier;
	}

	public void setVersionExternalIdentifier(int versionExternalIdentifier) {
		this.versionExternalIdentifier = versionExternalIdentifier;
	}

	public long getPurchaseDateMs() {
		return purchaseDateMs;
	}

	public void setPurchaseDateMs(long purchaseDateMs) {
		this.purchaseDateMs = purchaseDateMs;
	}

}
