package com.javahunter.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("HR");
    }
}
