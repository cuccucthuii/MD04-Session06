package ra.patientservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/welcome")
@RefreshScope
public class WelcomeController {

    private final Environment environment;

    public WelcomeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping
    public String welcome() {
        return environment.getProperty("app.welcome");
    }
}