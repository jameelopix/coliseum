package coliseum.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.webflow.context.ExternalContext;
import org.springframework.webflow.context.servlet.DefaultFlowUrlHandler;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
//import org.springframework.webflow.execution.RequestContextHolder;
import org.springframework.webflow.executor.FlowExecutionResult;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;
import org.springframework.webflow.test.MockExternalContext;

public class BaseServiceInvocationHandler implements InvocationHandler {

	private FlowDefinitionRegistry flowDefinitionRegistry;

	private FlowExecutor flowExecutor;

	private MockExternalContext mockExternalContext;

	private DefaultFlowUrlHandler defaultFlowUrlHandler = new DefaultFlowUrlHandler();

	private static String flowsuffix = "_flow";

	public BaseServiceInvocationHandler(FlowDefinitionRegistry flowDefinitionRegistry, FlowExecutor flowExecutor,
			MockExternalContext mockExternalContext) {
		this.flowDefinitionRegistry = flowDefinitionRegistry;
		this.flowExecutor = flowExecutor;
		this.mockExternalContext = mockExternalContext;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		System.out.println("BaseServiceInvocationHandler.invoke():" + methodName);
		if ("hashCode".equals(methodName)) {
			return this.hashCode();
		}
		if ("toString".equals(methodName)) {
			return this.toString();
		}
		if ("equals".equals(methodName)) {
			return this.equals(args[0]);
		}
		FlowDescriptor flowDescriptor = method.getAnnotation(FlowDescriptor.class);
		ExternalContext context = mockExternalContext;
		if (context == null) {
			context = this.getContext();
		}

		MutableAttributeMap<Object> inputMap = new LocalAttributeMap<>();
		inputMap.put(flowDescriptor.request(), args[0]);
		FlowExecutionResult flowExecutionResult = flowExecutor.launchExecution(flowDescriptor.flow() + flowsuffix,
				inputMap, context);

		Object object = flowExecutionResult.getOutcome().getOutput().get(flowDescriptor.response());

		return object;
	}

	private ExternalContext getContext() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();

		HttpServletRequest request = servletRequestAttributes.getRequest();
//		DefaultFlowUrlHandler defaultFlowUrlHandler = new DefaultFlowUrlHandler();
//		defaultFlowUrlHandler.
//		String url = request.getRequestURL().toString();
		HttpServletResponse response = servletRequestAttributes.getResponse();
		ServletContext servletContext = request.getServletContext();
		ServletExternalContext context = new MvcExternalContext(servletContext, request, response,
				defaultFlowUrlHandler);
		context.setAjaxRequest(true);
		return context;
	}

	@Override
	public String toString() {
		return "BaseServiceInvocationHandler";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultFlowUrlHandler == null) ? 0 : defaultFlowUrlHandler.hashCode());
		result = prime * result + ((flowDefinitionRegistry == null) ? 0 : flowDefinitionRegistry.hashCode());
		result = prime * result + ((flowExecutor == null) ? 0 : flowExecutor.hashCode());
		result = prime * result + ((mockExternalContext == null) ? 0 : mockExternalContext.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseServiceInvocationHandler other = (BaseServiceInvocationHandler) obj;
		if (defaultFlowUrlHandler == null) {
			if (other.defaultFlowUrlHandler != null)
				return false;
		} else if (!defaultFlowUrlHandler.equals(other.defaultFlowUrlHandler))
			return false;
		if (flowDefinitionRegistry == null) {
			if (other.flowDefinitionRegistry != null)
				return false;
		} else if (!flowDefinitionRegistry.equals(other.flowDefinitionRegistry))
			return false;
		if (flowExecutor == null) {
			if (other.flowExecutor != null)
				return false;
		} else if (!flowExecutor.equals(other.flowExecutor))
			return false;
		if (mockExternalContext == null) {
			if (other.mockExternalContext != null)
				return false;
		} else if (!mockExternalContext.equals(other.mockExternalContext))
			return false;
		return true;
	}

//	private ExternalContext getContext() {
//		RequestContextHolder.getRequestAttributes().
//		ExternalContext externalContext = RequestContextHolder.getRequestContext().getExternalContext();
//		return externalContext;
//	}

//	HttpServletRequest request,
//	HttpServletResponse response
//	protected ServletExternalContext createServletExternalContext() {
//	}

}
