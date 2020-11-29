package by.grodno.vika.librarywebapp.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "jpaprops")
@Data
public class HibernateProperties {
	private Map<String, String> jpaProperties;

	public Map<String, String> getJpaProperties() {
		return jpaProperties;
	}

	public void setJpaProperties(Map<String, String> jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

}