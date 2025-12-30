package com.numankaraaslan.paging;

public class SearchData
{
	private String name = ""; // name contains
	private Integer minId; // id >= minId
	private Integer maxId; // id <= maxId
	private String sort = "idDesc"; // sort field or direction
	private Integer pageSize = 10;

	public SearchData()
	{
	}

	@Override
	public String toString()
	{
		return (name != null && !name.equals("") ? "&name=" + name : "") + (minId != null ? "&minId=" + minId : "") + (maxId != null ? "&maxId=" + maxId : "") + (sort != null && !sort.equals("") ? "&sort=" + sort : "") + (pageSize != null ? "&pageSize=" + pageSize : "");
	}

	// getters and setters

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getMinId()
	{
		return minId;
	}

	public void setMinId(Integer minId)
	{
		this.minId = minId;
	}

	public Integer getMaxId()
	{
		return maxId;
	}

	public void setMaxId(Integer maxId)
	{
		this.maxId = maxId;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}
}