package coliseum.service;

import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.RequestContext;

public class ValidationFinalizer implements Action {

	@Override
	public Event execute(RequestContext context) throws Exception {
		System.out.println("ValidationFinalizer.execute()");
		Object obj = context.getFlowScope().get("errorDTOs");
		if (obj != null) {
			throw new FlowExecutionException(null, null, null);
		} else {
			return success();
		}
	}

	private Event success() {
		Event event = new Event("", "success");
		return event;
	}
}
