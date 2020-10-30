package pe.tuna.servzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.tuna.servzuul.utils.TipoFiltro;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

    @Override
    public String filterType() {
        return TipoFiltro.post.name();
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        log.info("Enrando en post filter");

        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoCierre = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoCierre - tiempoInicio;

        log.info(String.format("Tiempo transcurrido en seg. %s", tiempoTranscurrido.doubleValue()/1000.00));
        log.info(String.format("Tiempo transcurrido en msg. %s", tiempoTranscurrido.doubleValue()));
        return null;
    }
}
