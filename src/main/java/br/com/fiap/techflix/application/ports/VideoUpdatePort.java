package br.com.fiap.techflix.application.ports;

import java.util.List;

public interface VideoUpdatePort {

    String title();

    String description();

    List<String> categoryNames();
}
