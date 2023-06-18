package com.study.eda.shared.support.encryption;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;

import com.study.eda.shared.support.jackson.config.EncryptionConfiguration;

@JsonTest
@ContextConfiguration(classes = EncryptionConfiguration.class)
public class EncryptionTest {

	@Autowired
	private JacksonTester<FooDTO> jacksonTester;

	@Test
	void encryption() throws IOException {
		// given
		final var text = "test";
		final var value = new FooDTO(text);
		final var encrypted = EncryptionHelper.INSTANCE.encodeBase64(text.getBytes());
		final var encryptedString = new String(encrypted);

		// when
		final var json = jacksonTester.write(value);

		// then
		assertThat(json).extractingJsonPathStringValue("$.text").isEqualTo(encryptedString);
	}

	record FooDTO(@Encryption String text) {
	}
}
