package com.how2java.tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;


@Namespace("/")
@ParentPackage("basicstruts")  
@Results(
		{
			/*分类管理*/
			@Result(name="listCategory", location="/admin/listCategory.jsp"),
			@Result(name="listCategoryPage", type = "redirect", location="/admin_category_list"),
			@Result(name="editCategory", location="/admin/editCategory.jsp"),
		})
public class CategoryAction {
	@Autowired 
	CategoryService categoryService;

	List<Category> categorys;
	
	Category category;
	
	File img;

	Page page;
	
	
	@Action("admin_category_list")
	public String list() {
		
		if(page==null)
			page = new Page();
		int total = categoryService.total();
		page.setTotal(total);
		categorys = categoryService.listByPage(page);
		return "listCategory";
	}
	
	@Action("admin_category_add")
	public String add() {
		categoryService.save(category);
		File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,category.getId()+".jpg");
		try {
			FileUtils.copyFile(img, file);
	        BufferedImage img = ImageUtil.change2jpg(file);
	        ImageIO.write(img, "jpg", file);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "listCategoryPage";
	}	
	
	@Action("admin_category_delete")
	public String delete() {
		categoryService.delete(category);
		return "listCategoryPage";
	}	
	
	
	@Action("admin_category_edit")
	public String edit() {
		int id = category.getId();
		category = categoryService.get(Category.class,id);
		return "editCategory";
	}	

	@Action("admin_category_update")
	public String update() {
		categoryService.update(category);
		if(null!=img){
			File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
			File file = new File(imageFolder,category.getId()+".jpg");
			try {
				FileUtils.copyFile(img, file);
		        BufferedImage img = ImageUtil.change2jpg(file);
		        ImageIO.write(img, "jpg", file);
			} catch (IOException e) {
				
				e.printStackTrace();
			}			
		}

		
		return "listCategoryPage";
	}	
	
	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

}
