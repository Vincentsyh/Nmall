package com.how2java.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.dao.impl.DAOImpl;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.Page;

@Service
public class CategoryServiceImpl  implements CategoryService {

	@Autowired DAOImpl dao;
	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc);
	}
	@Override
	public int total() {
		String hql = "select count(*) from Category ";
		List<Long> l= dao.find(hql);
		if(l.isEmpty())
			return 0;
		Long result= l.get(0);
		return result.intValue();
	}
	@Override
	public List<Category> listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc,page.getStart(),page.getCount());
	}
	@Override
	public void save(Category category) {
		dao.save(category);
	}
	@Override
	public void delete(Category category) {
		dao.delete(category);
		
	}
	@Override
	public Category get(Class clazz, int id) {
		return (Category) dao.get(clazz, id);
	}
	@Override
	public void update(Category category) {
		dao.update(category);
	}
}
