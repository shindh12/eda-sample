package com.study.eda.shared.support.jackson.encryption;

import static java.util.Objects.*;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.study.eda.shared.support.encryption.Encryption;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncryptionBeanDeserializerModifier extends BeanDeserializerModifier {
	private final Charset charset;

	@Override
	public BeanDeserializerBuilder updateBuilder(DeserializationConfig config, BeanDescription beanDesc,
		BeanDeserializerBuilder builder) {
		builder.getProperties().forEachRemaining(property -> {
			final var annotation = property.getMember().getAnnotation(Encryption.class);
			if (nonNull(annotation)) {
				builder.addOrReplaceProperty(
					property.withValueDeserializer(new EncryptionJsonDeserializer(property.getType(),
						annotation,
						charset)), true);
			}
		});
		return builder;
	}
}
