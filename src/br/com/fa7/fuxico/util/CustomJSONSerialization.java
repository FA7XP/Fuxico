package br.com.fa7.fuxico.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;

@Component
public class CustomJSONSerialization extends XStreamJSONSerialization {

	public CustomJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer, XStreamBuilder builder) {
		super(response, extractor, initializer, builder);
	}

	@Override
	public XStream getXStream() {
		XStream xstream = super.getXStream();
		xstream.aliasSystemAttribute(null, "class"); 

		xstream.registerConverter(new SingleValueConverter() {

			public String toString(Object value) {
				return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(value);
			}

			public boolean canConvert(Class clazz) {
				return Date.class.isAssignableFrom(clazz);
			}

			public Object fromString(String value) {
				return null; 
			}
		});
		return xstream;
	}
}
