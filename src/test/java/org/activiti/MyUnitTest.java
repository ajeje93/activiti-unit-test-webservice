package org.activiti;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class MyUnitTest {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment(resources = { "org/activiti/test/my-process.bpmn20.xml" })
	public void test() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Alfresco");
		variableMap.put("contactperson", "Tom Baeyens");
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("customer",
				variableMap);
		assertNotNull(processInstance);
		Object responseValue = activitiRule.getRuntimeService().getVariable(processInstance.getProcessInstanceId(),
				"webserviceResponse");
		assertEquals("Highlands 343", responseValue);
	}

}
