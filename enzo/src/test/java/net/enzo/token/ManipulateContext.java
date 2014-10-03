package net.enzo.token;

import java.util.HashMap;
import java.util.Map;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpm.client.BPMServiceClientFactory;

import org.junit.Test;

public class ManipulateContext {

	ITaskQueryService taskQueryService;

	public IWorkflowContext getWorkflowContext(String username,
			String password, String url) throws WorkflowException {
		return getTaskQueryService(url).authenticate(username,
				password.toCharArray(), "jazn.com");
	}

	public ITaskQueryService getTaskQueryService(String url) {

		return getIWorkflowServiceClient(url).getTaskQueryService();
	}

	public IWorkflowServiceClient getIWorkflowServiceClient(String url) {

		return getBPMServiceClientFactory(url).getWorkflowServiceClient();
	}

	public BPMServiceClientFactory getBPMServiceClientFactory(String url) {
		Map<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String> properties = new HashMap<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String>();

		properties
				.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.CLIENT_TYPE,
						WorkflowServiceClientFactory.REMOTE_CLIENT);
		properties
				.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_PROVIDER_URL,
						url);
		properties
				.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_INITIAL_CONTEXT_FACTORY,
						"weblogic.jndi.WLInitialContextFactory");
		return BPMServiceClientFactory.getInstance(properties, null, null);
	}

	public void openDestroyWorkflowContext(String user, String passwd,
			String url) throws WorkflowException {

		IWorkflowContext workflowContext  = getWorkflowContext(user, passwd, url);

		getTaskQueryService(url).destroyWorkflowContext(workflowContext);

	}

	private String user = "angelica";
	private String passwd = "password1";

	@Test
	public void standalone() throws WorkflowException {

		openDestroyWorkflowContext(user, passwd, "t3://10.11.6.133:10001");

	}

	@Test
	public void clusterNode1() throws WorkflowException {
		openDestroyWorkflowContext(user, passwd, "t3://10.11.6.133:8001");
	}
	
	@Test
	public void clusterNode2() throws WorkflowException {
		openDestroyWorkflowContext(user, passwd, "t3://10.11.6.141:8001");
	}

}
