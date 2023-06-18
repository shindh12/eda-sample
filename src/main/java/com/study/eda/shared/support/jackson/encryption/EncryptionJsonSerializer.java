package com.study.eda.shared.support.jackson.encryption;

import static org.apache.commons.lang3.StringUtils.*;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.study.eda.shared.support.encryption.Encryption;
import com.study.eda.shared.support.encryption.EncryptionHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EncryptionJsonSerializer extends JsonSerializer<Object> {
	private final Encryption encryption;
	private final Charset charset;

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		try (final var writer = new StringWriter()) {
			try (final var generator = gen.getCodec().getFactory().createGenerator(writer)) {
				serializers.defaultSerializeValue(value, generator);
			}
			final var text = removeStart(removeEnd(writer.toString(), "\""), "\"");
			final var bytes = text.getBytes(charset);
			final var encrypted = encrypted(bytes, encryption);
			gen.writeString(new String(encrypted, charset));
		}
	}

	private byte[] encrypted(final byte[] bytes, final Encryption encryption) {
		return switch (encryption.algorithm()) {
			default -> EncryptionHelper.INSTANCE.encodeBase64(bytes);
		};
	}
}
