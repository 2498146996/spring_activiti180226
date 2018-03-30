package com.zqy.activitiManager.demo1.ctl;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqy.test.ModuleController;

/**
 * @Description 工作流controller
 * @author zhangqingyan@dhcc.com.cn
 * @date 2018年3月30日
 */
@Controller
@RequestMapping("com/zqy/activitiManager/demo1/ctl/ActivitiManagerCTL/")
public class ActivitiManagerCTL {
	private Logger logger = LoggerFactory.getLogger(ModuleController.class);

	@Autowired
	private RepositoryService repositoryService;
	
	/**
	 * @Description 查询model list
	 * @author zhangqingyan@dhcc.com.cn
	 * @date 2018年3月30日
	 * @return List<Model>
	 */
	@RequestMapping("qryActReModel")
	@ResponseBody
	public List<Model> qryActReModel() {
		List<Model> modelList = repositoryService.createModelQuery()
							.orderByCreateTime()
							.desc()
							.list();
		return modelList;
	}
	
	@RequestMapping("saveActReModel")
	public void saveActReModel(@RequestParam("name") String name) {
		Model model = repositoryService.newModel();
		model.setName(name);
		repositoryService.saveModel(model);
	}
	
	@RequestMapping("removeActReModel")
	public void removeActReModel(@RequestParam("modelId") String modelId) {
		repositoryService.deleteModel(modelId);
	}
}
