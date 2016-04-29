package com.zjjf.analysis.common.constants;

public class Pager {

	public static final int MAX_PAGE_INDEX = 10; // 页脚显示多少页
	public static final int PAGE_SIZE = 20; // 每页的记录数

	private int offset;
	private int size;
	private int length;
	private String url;
	private String pageHeader;

	public Pager(int offset, int size, int length, String url, String pageHeader) {
		
		this.offset = offset;
		this.size = size;
		this.length = length;
		this.url = url;
		this.pageHeader = pageHeader;
	}

	public String getCurPageUrl(String url, int offset) {
		String pref; // 前缀
		if (url.indexOf("?") > -1) {
			// 如果url中已经包含了其他的参数,就把offset参数接在后面
			pref = "&";
		} else {
			pref = "?";
		}
		return url + pref + "offset=" + offset;
	}

	/**
	 * 返回分页导航条
	 * 
	 * @param offset
	 *            int 起始记录的位置
	 * @param size
	 *            int 总记录数
	 * @param length
	 *            int 步长
	 * @param url
	 *            String .do的url
	 * @param pageHeader
	 *            String 导航条的前缀文字提示
	 * @return String
	 */
	public String getPageNavigation() throws Exception {
		String pageNavigation = ""; // 最终返回的分页导航条
		// 记录数超过一页,需要分页
		if (size > length) {
			String pref; // 前缀
			if (url.indexOf("?") > -1) {
				// 如果url中已经包含了其他的参数,就把offset参数接在后面
				pref = "&";
			} else {
				pref = "?";
			}
			// 如果导航条包含header
			if (pageHeader != null && pageHeader.length() > 0) {
				pageNavigation = pageHeader;
			}
			pageNavigation += "<span>总数:" + size + "</span>";
			// 如果不是第一页,导航条将包含“<<”(第一页)和“<”(前一页)
			if (offset > 0) {
				pageNavigation += "<a href='" + url + pref + "offset=0'><span>首页</span></a>\n" + "<a href='" + url + pref + "offset="
						+ (offset - length) + "'><span>上一页</span></a>\n";
			} else {
				pageNavigation += "<span class=\"nolink\">首页</span>\n" + "<span class=\"nolink\">上一页</span>\n";
			}
			// 导航条中,排头的那一页的offset值
			int startOffset;
			// 位于导航条中间的那一页的offset值(半径)
			int radius = MAX_PAGE_INDEX / 2 * length;
			int pagecount = this.pageCount();
			// 如果当前的offset值小于半径
			if (offset < radius || pagecount <= MAX_PAGE_INDEX) {
				// 那么第一页排头
				startOffset = 0;
			} else if (offset < size - radius) {
				startOffset = offset - radius;
			} else {
				// startOffset = (size / length - MAX_PAGE_INDEX) *
				// length;
				startOffset = (pagecount - MAX_PAGE_INDEX) * length;
			}
			for (int i = startOffset; i < size && i < startOffset + MAX_PAGE_INDEX * length; i += length) {
				if (i == offset) {
					// 当前页号,加粗显示
					// pageNavigation += "<b>" + (i / length + 1) + "</b>\n";
					pageNavigation += "<span class=\"current\">" + (i / length + 1) + "</span>\n";
				} else {
					// 其他页号,包含超链接
					pageNavigation += "<a href='" + url + pref + "offset=" + i + "'><span>" + (i / length + 1) + "</span></a>\n";
				}
			}
			// 如果不是最后一页,导航条将包含“>”(下一页)和“>>”(最后一页)
			if (offset < size - length) {
				pageNavigation += "<a href='" + url + pref + "offset=" + (offset + length) + "'><span>下一页</span></a>\n" + "<a href='" + url + pref
						+ "offset=" + lastPageOffset() + "'><span>尾页</span></a>\n";
			} else {
				pageNavigation += "<span class=\"nolink\">下一页</span>\n" + "<span class=\"nolink\">尾页</span>\n";
			}
			return pageNavigation;
		}
		// 记录不超过一页,不需要分页
		else {
			return "";
		}
	}

	/**
	 * 返回分页后的总页数
	 * 
	 * @param size
	 *            int 总记录数
	 * @param length
	 *            int 每页的记录数
	 * @return int
	 */
	public int pageCount() {
		int pagecount = 0;
		if (size % length == 0) {
			pagecount = size / length;
		} else {
			pagecount = size / length + 1;
		}
		return pagecount;
	}

	/**
	 * 返回最后一页的记录数
	 * 
	 * @param size
	 *            int 总记录数
	 * @param length
	 *            int 每页的记录数
	 * @return int
	 */
	public int lastPageSize() {
		int lastpagesize = 0;
		if (size % length == 0) {
			lastpagesize = length;
		} else {
			lastpagesize = size % length;
		}
		return lastpagesize;
	}

	/**
	 * 返回最后一页的起始记录位置
	 * 
	 * @param size
	 *            int 总记录数
	 * @param length
	 *            int 每页的记录数
	 * @return int
	 */
	public int lastPageOffset() {
		return size - lastPageSize();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}
}
