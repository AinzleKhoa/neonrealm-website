/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.customtags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom JSP tag handler for generating a pagination component. This tag
 * dynamically generates pagination links based on the current page and total
 * pages.
 *
 * @author Le Anh Khoa - CE190449
 */
public class PaginationHandler extends TagSupport {

    private String url;  // URL for pagination links
    private int currentPage;  // Current page number
    private int totalPages;  // Total number of pages

    /**
     * Gets the URL for pagination links.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for pagination links.
     *
     * @param url the URL
     */
    public void setUrl(String url) {
        this.url = (url == null) ? "" : url; // Prevent NullPointerException
    }

    /**
     * Gets the current page number.
     *
     * @return the current page number
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets the current page number.
     *
     * @param currentPage the current page number
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets the total number of pages.
     *
     * @return the total number of pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets the total number of pages.
     *
     * @param totalPages the total number of pages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Generates the pagination HTML and writes it to the page.
     *
     * @return SKIP_BODY to skip the body of the tag
     * @throws JspException if an error occurs during the tag processing
     */
    @Override
    public int doStartTag() throws JspException {
        StringBuilder pagination = new StringBuilder();

        try {
            String pageParam = url.endsWith("?") || url.endsWith("&") ? "" : (url.contains("?") ? "&" : "?");
            pagination.append("<ul class='paginator__wrap'>");

            // Previous Button
            if (currentPage > 1) {
                pagination.append(String.format("<li class='paginator__item'><a href='%s%spage=1'>&laquo;</a></li>", url, pageParam));
                pagination.append(String.format(
                        "<li class='paginator__item paginator__item--prev'><a href='%s%spage=%d'>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='244 400 100 256 244 112' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='120' y1='256' x2='412' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></a></li>", url, pageParam, currentPage - 1));
            } else {
                pagination.append("<li class='paginator__item paginator__last--disabled'><a href='javascript:void(0)'>&laquo;</a></li>");
                pagination.append(
                        "<li class='paginator__item paginator__item--prev disabled'><span>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='244 400 100 256 244 112' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='120' y1='256' x2='412' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></span></li>");
            }

            // Determine range of pages to show
            int range = 3; // Show 3 pages before and after the current page
            int startPage = Math.max(1, currentPage - range); // Ensures it doesn't go below 1
            int endPage = Math.min(totalPages, currentPage + range); // Ensures it doesn't exceed totalPages

            // Always ensure first few pages are visible when close to the start
            if (currentPage <= range) {
                startPage = 1;
                endPage = Math.min(totalPages, 2 * range + 1);
            }

            // Always ensure last few pages are visible when close to the end
            if (currentPage >= totalPages - range) {
                startPage = Math.max(1, totalPages - 2 * range);
                endPage = totalPages;
            }

            // Generate the page numbers
            for (int i = startPage; i <= endPage; i++) {
                if (i == currentPage) {
                    pagination.append(String.format("<li class='paginator__item paginator__item--active'><a href='javascript:void(0)'>%d</a></li>", i));
                } else {
                    pagination.append(String.format("<li class='paginator__item'><a href='%s%spage=%d'>%d</a></li>", url, pageParam, i, i));
                }
            }

            // Next Button
            if (currentPage < totalPages) {
                pagination.append(String.format(
                        "<li class='paginator__item paginator__item--next'><a href='%s%spage=%d'>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='268 112 412 256 268 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='392' y1='256' x2='100' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></a></li>", url, pageParam, currentPage + 1));
                pagination.append(String.format("<li class='paginator__item'><a href='%s%spage=%d'>&raquo;</a></li>", url, pageParam, totalPages));

            } else {
                pagination.append(
                        "<li class='paginator__item paginator__item--next disabled'><span>"
                        + "<svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>"
                        + "<polyline points='268 112 412 256 268 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "<line x1='392' y1='256' x2='100' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/>"
                        + "</svg></span></li>");
                pagination.append("<li class='paginator__item paginator__last--disabled'><a href='javascript:void(0)'>&raquo;</a></li>");
            }

            pagination.append("</ul>");

            pageContext.getOut().write(pagination.toString());
        } catch (IOException e) {
            throw new JspException("Error in PaginationHandler", e);
        }

        return SKIP_BODY;
    }
}
