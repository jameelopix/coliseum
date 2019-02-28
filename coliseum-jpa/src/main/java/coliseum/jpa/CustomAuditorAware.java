package coliseum.jpa;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class CustomAuditorAware implements AuditorAware<String> {

	public Optional<String> getCurrentAuditor() {
		Optional<String> optional = Optional.of("Jameel");
		return optional;
	}

}
