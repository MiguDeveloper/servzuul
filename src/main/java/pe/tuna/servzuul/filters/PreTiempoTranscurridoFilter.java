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
public class PreTiempoTranscurridoFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

    @Override
    public String filterType() {
        return TipoFiltro.pre.name();
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    // con este metodo validamos si vamos o no ejecutar el filtro
    // aqui podemos hacer validaciones por ejemplo para cuando queramos condicionar
    // la ejecucion del filtro para este caso sera ejecuta en cada request
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // Aqui ponemos el codigo que queremos ejecutar
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));

        Long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);

        return null;
    }
}
