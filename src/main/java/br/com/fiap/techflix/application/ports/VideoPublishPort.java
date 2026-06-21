package br.com.fiap.techflix.application.ports;

import java.util.List;

public interface VideoPublishPort {

    String fileId();

    String title();

    String description();

    List<String> categoryNames();
}
