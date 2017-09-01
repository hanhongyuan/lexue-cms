package com.lexue.base.domain;

import java.util.List;

/**
 * pageInfo的实体
 * <P>
 * 主要用于分页
 * 
 */
public class PageInfo<T> extends com.github.pagehelper.PageInfo<T> {

	private static final long serialVersionUID = 4194072178786196665L;

	private String funcName = "page";

	public PageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        super(list, 8);
    }
	/**
	 *  <div class="page">${page}</div>
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		if (isIsFirstPage()) // 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		else
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + getPrePage() + "," + getPageSize()
					+ "');\">&#171; 上一页</a></li>\n");

		int start = 1, end = getPages();
		if (getPages() > 9) {
			start = getPageNum() - 4;
			end = getPageNum() + 4;
			if (start < 1) {
				end = end - start + 1;
				start = 1;
			}
		}
		if (start <= 3)
			start = 1;
		else {
			sb.append(
					"<li><a href=\"javascript:\" onclick=\"" + funcName + "(1," + getPageSize() + ");\">1</a></li>\n");
			sb.append("<li class=\"active\"><a href=\"javascript:\">...</a></li>\n");
		}
		if (end >= getPages() - 2)
			end = getPages();
		for (int i = start; i <= end; i++) {
			if (i == getPageNum()) {
				sb.append("<li class=\"active\"><a href=\"javascript:\">" + i + "</a></li>\n");
			} else {
				sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + "," + getPageSize() + ");\">"
						+ i + "</a></li>\n");
			}
		}

		if (end < getPages() - 2) {
			sb.append("<li class=\"active\"><a href=\"javascript:\">...</a></li>\n");
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + getPages() + "," + getPageSize()
					+ ");\">" + getPages() + "</a></li>\n");
		}
		if (isIsLastPage()) 
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		 else 
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + getNextPage() + ");\">"
					+ "下一页 &#187;</a></li>\n");

		sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前 ");
		sb.append("<input type=\"text\" value=\"" + getPageNum()
				+ "\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName + "(this.value," + getPageSize() + "');\" onclick=\"this.select();\"/> / ");
		sb.append("<input type=\"text\" value=\"" + getPageSize()
				+ "\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName + "(" + getPageNum() + ",this.value);\" onclick=\"this.select();\"/> 条，");
		sb.append("共 " + getTotal() + " 条" + "</a></li>\n");
		sb.insert(0, "<ul>\n").append("</ul>\n");
		sb.append("<div style=\"clear:both;\"></div>");
		return sb.toString();
	}

}
