package coliseum.service;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.test.MockExternalContext;

public class BaseServiceImpl {

    @Autowired(required = false)
    private MockExternalContext mockExternalContext;

    @Autowired
    private FlowDefinitionRegistry flowDefinitionRegistry;

    @Autowired
    private FlowExecutor flowExecutor;

    public BaseService create(Class<BaseService> cls) {
        BaseService baseService = (BaseService) Proxy.newProxyInstance(BaseServiceImpl.class.getClassLoader(),
                new Class[] { cls }, new BaseServiceInvocationHandler(this.flowDefinitionRegistry, this.flowExecutor,
                        this.mockExternalContext));
        return baseService;
    }
}
