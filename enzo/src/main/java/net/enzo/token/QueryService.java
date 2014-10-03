package net.enzo.token;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.IWorkflowConstants;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Ordering;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpm.client.BPMServiceClientFactory;
import oracle.bpm.client.common.BPMServiceClientException;
import oracle.bpm.services.common.exception.BPMException;
import oracle.tip.pc.services.identity.config.BPMConfigException;

public class QueryService {

	public static void main(String[] args) throws BPMConfigException,
			WorkflowException, BPMServiceClientException, BPMException {
		new QueryService().run();
	}

	public void run() throws BPMConfigException, WorkflowException,
			BPMServiceClientException, BPMException {
		// User whose task list needs to be queried
		String userId = "angelica";
		// Password for the user
		String password = "password1";

		// You can use keyword to specify sql % around the keyword like:
		// %keyword% on the following
		// attributes: task title, identification key, all textAttributes in
		// task, task number (only
		// if the keyword is a number)

		String keyword = null;
		String nullParam = null;

		// Get workflow service client
		// IWorkflowServiceClient wfSvcClient = WorkflowServiceClientFactory
		// .getWorkflowServiceClient(WorkflowServiceClientFactory.SOAP_CLIENT);
		// // Get the workflow context
		String url = "t3://10.11.6.133,10.11.6.141:8001";

		IWorkflowServiceClient wfSvcClient = getWorkflowServiceClient(url);

		IWorkflowContext wfCtx = getWorkflowContext(userId, password,
				wfSvcClient);

		ITaskQueryService querySvc = wfSvcClient.getTaskQueryService();

//		String id = "1a5467a2-c179-4962-b97c-dbb0d7b0dfba";

		
		String id = "968a77a0-279f-4f14-84ff-fe667ad81eaf";
		
		Task t = getTaskDetailsById(wfCtx, querySvc, id);

		System.out.println("*********SINGLE TASK*********");

		printTaskDetails(t);

		List<Task> tasksList = getTaskByPredicate(wfCtx, querySvc, keyword,
				nullParam);

		System.out.println("*********LIST TASK*********");

		if (tasksList != null) { // There are tasks
			System.out.println("Total number of tasks: " + tasksList.size());
			System.out.println("Tasks List: ");
			Task task = null;
			for (int i = 0; i < tasksList.size(); i++) {
				task = (Task) tasksList.get(i);
				printTaskDetails(task);
				// Retrive any Optional Info specified
				// Use task service, to perform operations on the task
			}
		}

		getBPMServiceClientFactory(url).getBPMUserAuthenticationService()
				.destroyBPMContext((IBPMContext) wfCtx);

	}

	private void printTaskDetails(Task task) {
		System.out.println("Task Number: "
				+ task.getSystemAttributes().getTaskNumber());
		System.out
				.println("Task Id: " + task.getSystemAttributes().getTaskId());
		System.out.println("Title: " + task.getTitle());
		System.out.println("Priority: " + task.getPriority());
		System.out.println("State: " + task.getSystemAttributes().getState());
		System.out.println();

	}

	private IWorkflowContext getWorkflowContext(String userId, String password,
			IWorkflowServiceClient wfSvcClient) throws WorkflowException {
		IWorkflowContext wfCtx = wfSvcClient.getTaskQueryService()
				.authenticate(userId, password.toCharArray(), "jazn.com");

		// Admin can authenticate on behalf of another user
		// IWorkflowContext adminCtx =
		// wfSvcClient.getTaskQueryService().authenticate(adminUserId, pwd,
		// oracle.tip.pc.services.identity.config.ISConfiguration.getDefaultRealmName(),
		// userId);
		return wfCtx;
	}

	private IWorkflowServiceClient getWorkflowServiceClient(String url) {
		IWorkflowServiceClient wfSvcClient = new QueryService()
				.getBPMServiceClientFactory(url).getWorkflowServiceClient();
		return wfSvcClient;
	}

	private Task getTaskDetailsById(IWorkflowContext wfCtx,
			ITaskQueryService querySvc, String id) throws WorkflowException {
		return querySvc.getTaskDetailsById(wfCtx, id);

	}

	private BPMServiceClientFactory getBPMServiceClientFactory(String url) {
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

	private List<Task> getTaskByPredicate(IWorkflowContext wfCtx,
			ITaskQueryService querySvc, String keyword, String nullParam)
			throws WorkflowException {

		// Build the predicate
		// Predicate statePredicate = new Predicate(
		// TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_NEQ,
		// IWorkflowConstants.TASK_ACTION_DISPLAY_NAME);
		// statePredicate.addClause(Predicate.AND,
		// TableConstants.WFTASK_NUMBERATTRIBUTE1_COLUMN,
		// Predicate.OP_IS_NULL, nullParam);
		// Predicate datePredicate = new Predicate(
		// TableConstants.WFTASK_ENDDATE_COLUMN, Predicate.OP_ON,
		// new Date());
		// Predicate predicate = new Predicate(statePredicate, Predicate.AND,
		// datePredicate);

		Predicate statePredicate = new Predicate(
				TableConstants.WFTASK_STATE_COLUMN, Predicate.OP_NEQ,
				IWorkflowConstants.TASK_STATE_ASSIGNED);

		Predicate predicate = statePredicate;

		// Create the ordering
		Ordering ordering = new Ordering(TableConstants.WFTASK_TITLE_COLUMN,
				true, true);
		ordering.addClause(TableConstants.WFTASK_PRIORITY_COLUMN, true, true);

		// List of display columns
		// For those columns that are not specified here, the queried Task
		// object will not hold any value.
		// For example: If TITLE is not specified, task.getTitle() will return
		// null
		// For the list of most comonly used columns, check the table below
		// Note: TASKID is fetched by default. So there is no need to explicitly
		// specity it.
		List<String> queryColumns = new ArrayList<String>();
		queryColumns.add("TASKNUMBER");
		queryColumns.add("TITLE");
		queryColumns.add("PRIORITY");
		queryColumns.add("STATE");
		queryColumns.add("ENDDATE");
		queryColumns.add("NUMBERATTRIBUTE1");
		queryColumns.add("TEXTATTRIBUTE1");

		// List of optional info
		// Any optionalInfo specified can be fetched from the Task object
		// For example: if you have specified "CustomActions", you can retrieve
		// it using task.getSystemAttributes().getCustomActions();
		// "Actions" (All Actions) -
		// task.getSystemAttributes().getSystemActions()
		// "GroupActions" (Only group Actions: Actions that can be permoded by
		// the user as a member of a group)
		// - task.getSystemAttributes().getSystemActions()
		// "ShortHistory" - task.getSystemAttributes().getShortHistory()
		List<String> optionalInfo = new ArrayList<String>();
		optionalInfo.add("Actions");
		// optionalInfo.add("GroupActions");
		// optionalInfo.add("CustomActions");
		// optionalInfo.add("ShortHistory");
		// The following is reserved for future use.
		// If you need them, please use getTaskDetailsById (or)
		// getTaskDetailsByNumber,
		// which will fetch all information related to a task, which includes
		// these
		// optionalInfo.add("Attachments");
		// optionalInfo.add("Comments");
		// optionalInfo.add("Payload");

		return querySvc.queryTasks(wfCtx, queryColumns, optionalInfo,
				ITaskQueryService.ASSIGNMENT_FILTER_MY_AND_GROUP, keyword,
				predicate, ordering, 0, 0); // No Paging

		// How to use paging:
		// 1. If you need to dynamically calculate paging size (or) to
		// display/find
		// out the number of pages, the user has to scroll (Like page X of Y)
		// Call queryTasks to find out the number of tasks it returns. Using
		// this
		// calculate your paging size (The number of taks you want in a page)
		// Call queryTasks successively varing the startRow and endRow params.
		// For example: If the total number of tasks is 30 and your want a
		// paging size
		// of 10, you can call with (startRow, endRow): (1, 10) (11, 20) (21,
		// 30)
		// 2. If you have fixed paging size, just keep calling queryTasks
		// successively with
		// the paging size (If your paging size is 10, you can call with
		// (startRow, endRow):
		// (1, 10) (11, 20) (21, 30) (31, 40)..... until the number of tasks
		// returned is
		// less than your paging size (or) there are no more tasks returned
	}
}
