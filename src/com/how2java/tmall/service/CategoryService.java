package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.util.Page;

public interface CategoryService{
	
	public List list();

	public void save(Category category);
	
	public int total();

	public List<Category> listByPage(Page page);

	public void delete(Category category);

	public Category get(Class clazz, int id);

	public void update(Category category);
}
