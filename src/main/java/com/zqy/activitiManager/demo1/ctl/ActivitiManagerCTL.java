package com.zqy.activitiManager.demo1.ctl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
	@ResponseBody
	public Model saveActReModel(@RequestParam("name") String name, @RequestParam("key") String key, 
			@RequestParam("description") String description) throws UnsupportedEncodingException {
		Model model = repositoryService.newModel();
		ObjectMapper objectMapper = new ObjectMapper();  
		ObjectNode modelObjectNode = objectMapper.createObjectNode();  
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);  
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);  
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);  
		
		model.setMetaInfo(modelObjectNode.toString());  
		model.setName(name);  
		model.setKey(key);  
		repositoryService.saveModel(model);
		
		ObjectNode editorNode = objectMapper.createObjectNode();  
		editorNode.put("id", "canvas");  
		editorNode.put("resourceId", "canvas");  
		ObjectNode stencilSetNode = objectMapper.createObjectNode();  
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");  
		editorNode.set("stencilset", stencilSetNode);  
		repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8")); 
		
		return model;
	}
	
	@RequestMapping("removeActReModel")
	public void removeActReModel(@RequestParam("modelId") String modelId) {
		repositoryService.deleteModel(modelId);
	}
	
	@RequestMapping("deployActReModel")
	public void deployActReModel(@RequestParam("modelId") String modelId) throws IOException {
		Model modelData = repositoryService.getModel(modelId);
		ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bpmnBytes = null;
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        String processName = modelData.getName() + ".bpmn20.xml";
        DeploymentBuilder db = repositoryService.createDeployment().name(modelData.getName());
        Deployment deployment = db.addString(processName, new String(bpmnBytes,"utf-8")).deploy();
        
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
		logger.debug(deployment.getId());
	}
}
