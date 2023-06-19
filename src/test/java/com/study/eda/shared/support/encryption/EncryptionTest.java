package com.study.eda.shared.support.encryption;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.eda.shared.support.jackson.config.EncryptionConfiguration;

import lombok.Getter;
import lombok.Setter;

@JsonTest
@ContextConfiguration(classes = EncryptionConfiguration.class)
public class EncryptionTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void encryption() throws IOException {
		// given
		final var text = "test";
		final var value = FooDTO.from(text);
		final var expected = """
			{"text":"dGVzdA=="}
			""";

		// when
		final var actual = objectMapper.writeValueAsString(value);

		// then
		assertThat(actual).isEqualToIgnoringWhitespace(expected);
	}

	@Test
	void decryption() throws IOException {
		// given
		final var json = """
			{"text":"dGVzdA=="}
			""";
		final var expected = "test";

		// when
		final var dto = objectMapper.readValue(json, FooDTO.class);

		// then
		assertThat(dto.getText()).isEqualTo(expected);
	}

	@Getter
	@Setter
	static class FooDTO {
		@Encryption
		private String text;

		public static FooDTO from(String text) {
			final var dto = new FooDTO();
			dto.setText(text);
			return dto;
		}
	}
}
