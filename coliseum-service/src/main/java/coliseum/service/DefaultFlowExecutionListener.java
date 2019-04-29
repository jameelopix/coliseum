package coliseum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.execution.EnterStateVetoException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.FlowExecutionListener;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

public class DefaultFlowExecutionListener implements FlowExecutionListener {

    @Override
    public void requestSubmitted(RequestContext context) {

    }

    @Override
    public void requestProcessed(RequestContext context) {

    }

    @Override
    public void sessionCreating(RequestContext context, FlowDefinition definition) {

    }

    @Override
    public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap<?> input) {

    }

    @Override
    public void sessionStarted(RequestContext context, FlowSession session) {

    }

    @Override
    public void eventSignaled(RequestContext context, Event event) {
//		System.out.println("DefaultFlowExecutionListener.eventSignaled():" + event.getId() + ":" + event.getSource());
        Object result = event.getAttributes().get("result");
        if (result != null && result instanceof ErrorDTO) {
            ErrorDTO errorDTO = (ErrorDTO) result;
            List<ErrorDTO> errorDTOs = null;
            MutableAttributeMap<Object> flowScope = context.getFlowScope();
            Object obj = flowScope.get("errorDTOs");
            if (obj == null) {
                errorDTOs = new ArrayList<>();
            } else {
                errorDTOs = (List<ErrorDTO>) obj;
            }
            errorDTOs.add(errorDTO);
            flowScope.put("errorDTOs", errorDTOs);
        }
    }

    @Override
    public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
        System.out.println(transition.getId());
        System.out.println(transition.getTargetStateId());

//		Flow flow = (Flow) context.getFlowExecutionContext().getDefinition();
//		Transition transition2 = (Transition) transition;
//		System.out.println("TransitionDefinition:" + transition2.getTargetStateResolver());
//		for (String ids : flow.getStateIds()) {
//			State state = (State) flow.getState(ids);
//			if (state instanceof ActionState) {
//				ActionState actionState = (ActionState) state;
//				AnnotatedAction annotatedAction = (AnnotatedAction) actionState.getActionList().get(0);
//				EvaluateAction evaluateAction = (EvaluateAction) annotatedAction.getTargetAction();
//				System.out.println("METHOD:" + evaluateAction);
//			}
//			System.out.println("state:" + state.getId());
//			System.out.println("state:" + state.toString());
//		}

//		System.out.println("DefaultFlowExecutionListener.transitionExecuting():" + transition.getId() + ":"
//				+ transition.getTargetStateId() + transition.getAttributes() + transition);
    }

    @Override
    public void stateEntering(RequestContext context, StateDefinition state) throws EnterStateVetoException {

    }

    @Override
    public void stateEntered(RequestContext context, StateDefinition previousState, StateDefinition state) {

    }

    @Override
    public void viewRendering(RequestContext context, View view, StateDefinition viewState) {

    }

    @Override
    public void viewRendered(RequestContext context, View view, StateDefinition viewState) {

    }

    @Override
    public void paused(RequestContext context) {

    }

    @Override
    public void resuming(RequestContext context) {

    }

    @Override
    public void sessionEnding(RequestContext context, FlowSession session, String outcome,
            MutableAttributeMap<?> output) {

    }

    @Override
    public void sessionEnded(RequestContext context, FlowSession session, String outcome, AttributeMap<?> output) {

    }

    @Override
    public void exceptionThrown(RequestContext context, FlowExecutionException exception) {
        System.out.println("DefaultFlowExecutionListener.exceptionThrown()");
        if (exception != null) {
            exception.printStackTrace();
        }

        List<ErrorDTO> errorDTOs = new ArrayList<>();
        MutableAttributeMap<Object> flowScope = context.getFlowScope();
        Object obj = flowScope.get("errorDTOs");
        if (obj != null) {
            errorDTOs = (List<ErrorDTO>) obj;
        }
        throw new BusinessException(errorDTOs);
    }

//	@Override
//	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		System.out.println("DynamicInvocationHandler.invoke()");
//		System.out.println("PROXY:" + proxy.getClass().getName());
//		FlowDescriptor flowDescriptor = method.getAnnotation(FlowDescriptor.class);
//		System.out.println(flowDescriptor.flow());
//		System.out.println("Method:" + method.getName());
//		if (args != null) {
//			for (Object object : args) {
//				System.out.println("args:" + object);
//			}
//		}
//		return null;
//	}
}
