package com.kkarlberg.conan.protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.apache.cxf.jaxrs.utils.HttpUtils;

@Produces("application/javascript")
class JsonpProvider extends JSONProvider<Object> {

    @Override
    public void writeTo(Object obj, Class<?> cls, Type genericType, Annotation[] anns, 
            MediaType m, MultivaluedMap<String, Object> headers, OutputStream os) throws IOException {
        String prefix = getContext().getHttpServletRequest().getParameter("_jsonp");
        boolean hasPrefix = !StringUtils.isEmpty(prefix);
        if(hasPrefix) {
            os.write(prefix.getBytes(HttpUtils.getSetEncoding(m, headers, "UTF-8")));
            os.write('(');
        }
        super.writeTo(obj, cls, genericType, anns, m, headers, os);
        if(hasPrefix) {
            os.write(')');
        }
    }
}