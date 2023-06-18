package com.study.eda.shared.support.jackson.encryption;

import static java.util.Objects.*;

import java.io.IOException;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.study.eda.shared.support.encryption.Encryption;
import com.study.eda.shared.support.encryption.EncryptionHelper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EncryptionJsonDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {
	private final JavaType type;
	private final Encryption encryption;
	private final Charset charset;

	@Override
	public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		final var text = p.getText();
		final var decrypted = decrypted(text.getBytes(charset), encryption);
		return ctxt.readTreeAsValue(TextNode.valueOf(new String(decrypted, charset)), type);
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
		if (isNull(property)) {
			return this;
		}
		final var annotation = property.getAnnotation(Encryption.class);
		if (isNull(annotation)) {
			if (type.equals(property.getType())) {
				return this;
			}
			return new EncryptionJsonDeserializer(property.getType(), encryption, charset);
		}
		if (type.isPrimitive() && encryption.equals(annotation)) {
			return this;
		}
		return new EncryptionJsonDeserializer(property.getType(), annotation, charset);
	}

	private byte[] decrypted(final byte[] bytes, final Encryption encryption) {
		return switch (encryption.algorithm()) {
			default -> EncryptionHelper.INSTANCE.decodeBase64(bytes);
		};
	}
}
