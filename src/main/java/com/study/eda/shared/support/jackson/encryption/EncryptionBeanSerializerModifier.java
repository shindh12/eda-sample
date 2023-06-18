package com.study.eda.shared.support.jackson.encryption;

import static java.util.Objects.*;

import java.nio.charset.Charset;
import java.util.List;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.study.eda.shared.support.encryption.Encryption;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncryptionBeanSerializerModifier extends BeanSerializerModifier {
	private final Charset charset;

	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
		List<BeanPropertyWriter> beanProperties) {
		for (int i = 0; i < beanProperties.size(); i++) {
			final var writer = beanProperties.get(i);
			final var annotation = writer.findAnnotation(Encryption.class);
			if (nonNull(annotation)) {
				beanProperties.set(i, new EncryptionBeanPropertyWriter(writer, annotation, charset));
			}
		}
		return super.changeProperties(config, beanDesc, beanProperties);
	}

	static class EncryptionBeanPropertyWriter extends BeanPropertyWriter {
		public EncryptionBeanPropertyWriter(final BeanPropertyWriter base, final Encryption encryption,
			final Charset charset) {
			super(base);
			_serializer = new EncryptionJsonSerializer(encryption, charset);
			_nullSerializer = _serializer;
		}
	}
}
